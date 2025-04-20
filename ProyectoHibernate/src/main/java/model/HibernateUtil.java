package model;

import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

import java.util.concurrent.TimeUnit;

import java.util.logging.Level;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.boot.Metadata;

import org.hibernate.boot.MetadataSources;

import org.hibernate.boot.registry.StandardServiceRegistry;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Environment;

import org.hibernate.engine.jdbc.internal.FormatStyle;

import org.hibernate.engine.jdbc.internal.Formatter;

import com.mysql.cj.jdbc.MysqlDataSource;

import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;

import net.ttddyy.dsproxy.listener.logging.SystemOutQueryLoggingListener;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static synchronized SessionFactory getSessionFactory() {

		deshabilitarMensajes();

		if (sessionFactory == null) {

			sessionFactory = buildSessionFactory();

		}

		return sessionFactory;

	}

	public static void closeSessionFactory() {

		getSessionFactory().close();

	}

	private static SessionFactory buildSessionFactory() {

		StandardServiceRegistry standardRegistry = null;

		try {

			StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();

			// @see
			// https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/cfg/Environment.html

//            Map<String,String> gestoresDeBD= new HashMap<>();

//            gestoresDeBD.put("H2", value)

//            String gestorUtilizado="MySQL";

			Map<String, Object> settings = new HashMap<String, Object>();

			settings.put(Environment.DATASOURCE, getDataSource());

			// original settings.put(Environment.HBM2DDL_AUTO, "create-drop"); // ojo

			settings.put(Environment.ORDER_UPDATES, true);

			settings.put(Environment.ORDER_INSERTS, true);

			settings.put(Environment.STATEMENT_BATCH_SIZE, 10);

			// para MySQL
			// https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/dialect/MySQLDialect.html

			// org.hibernate.dialect.MySQLDialect versi�n 5

			// original settings.put(Environment.DIALECT,
			// "org.hibernate.dialect.H2Dialect"); //ojo

			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

			settings.put(Environment.SHOW_SQL, false); // no mostrar mensajes sentencias SQL

			standardRegistryBuilder.applySettings(settings);

			standardRegistry = standardRegistryBuilder.build();

			// builds a session factory from the service registry

			MetadataSources metadataSources = new MetadataSources(standardRegistry);

			metadataSources.addAnnotatedClass(model.Cliente.class);
			metadataSources.addAnnotatedClass(model.Pedidos.class);
			

			Metadata metadata = metadataSources.getMetadataBuilder().build();

			return metadata.buildSessionFactory();

		} catch (Exception ex) {

			// Make sure you log the exception, as it might be swallowed

			System.err.println(

					"Initial SessionFactory creation failed." + ex.getMessage());

			// The registry would be destroyed by the SessionFactory, but we had

			// trouble

			// building the SessionFactory so destroy it manually.

			StandardServiceRegistryBuilder.destroy(standardRegistry);

			throw new ExceptionInInitializerError(ex.getCause());

		}

	}

	private static class PrettyQueryEntryCreator extends DefaultQueryLogEntryCreator {

		private Formatter formatter = FormatStyle.BASIC.getFormatter();

		@Override

		protected String formatQuery(String query) {

			return this.formatter.format(query);

		}

	}

	private static DataSource getDataSource() {

		// use pretty formatted query with multiline enabled

		PrettyQueryEntryCreator creator = new PrettyQueryEntryCreator();

		creator.setMultiline(true);

		SystemOutQueryLoggingListener listener = new SystemOutQueryLoggingListener();

		listener.setQueryLogEntryCreator(creator);

		// listener.setQueryLogEntryCreator(queryLogEntryCreator);

		// Create ProxyDataSource

		return ProxyDataSourceBuilder.create(getMySQLDataSource()) // ojo

				.name("ProxyDataSource")

				.countQuery()

				.multiline()

				.listener(listener)

				.logSlowQueryToSysOut(1, TimeUnit.MINUTES)

				.build();

	}

	private static DataSource getH2DataSource() {

		JdbcDataSource ds = new JdbcDataSource();

		ds.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

		ds.setUser("sa");

		ds.setPassword("");

		return ds;

	}

	private static DataSource getMySQLDataSource() {

		MysqlDataSource ds = new MysqlDataSource();

		ds.setURL("jdbc:mysql://localhost:3306/AccesoaDatos");

		ds.setUser("root");

		ds.setPassword("root");

		try {

			ds.setServerTimezone("Europe/Madrid");

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return ds;

	}

	public static String getEstadoObjeto(Object objeto, Session session) {

		String estado = "Desacoplado";

		String mensajeExcepcion = null;

		try {

			if (session.contains(objeto)) {

				estado = "Persistente";

			}

			else {

				estado = "Transitorio";

			}

		}

		catch (Exception e) {

			mensajeExcepcion = "Se ha producido una excepci�n: " + e.getMessage();

			// e.printStackTrace();

		}

		return "* Estado objeto. [" + estado + "]. Clase del objeto: " + objeto.getClass().getName() + "*"
				+ mensajeExcepcion;

	}

	public static void deshabilitarMensajes() {

//    	LogManager logManager = LogManager.getLogManager();

//        Logger logger = logManager.getLogger("");

//        logger.setLevel(Level.SEVERE); //could be Level.OFF

		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

	}

}