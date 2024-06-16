package model;

import java.sql.Date;
import java.util.List;
import org.hibernate.Session;


public class RepositorioHibernatePedidosImpl implements IRepositorioPedido {

	Session session = HibernateUtil.getSessionFactory().openSession();

	public long count() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		String counthql = "SELECT COUNT(*)from Pedidos";
		long contador = (long) session.createSQLQuery(counthql).uniqueResult();

		session.close();

		return contador;
	}

	public void deleteAll() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.delete(Pedidos.class);
		session.getTransaction().commit();

		session.close();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		List<Pedidos> lista = session.createQuery("FROM Pedidos").list();

		session.close();

		return lista;
	}

	@Override

	public void deleteById(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Pedidos pedidosdelid = session.load(Pedidos.class, id);
		if (pedidosdelid != null) {
			session.delete(pedidosdelid);
		} else {
			System.out.println("NO SE PUEDE BORRAR, NO EXISTEN PEDIDOS CON ESE ID...");
		}

		session.getTransaction().commit();

		session.close();
	}

	@Override

	public boolean existsById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean idexiste = false;
		session.beginTransaction();

		if (session.get(Pedidos.class, id) != null) {
			idexiste = true;
		} else {
			idexiste = false;
			System.out.println("NO HAY PEDIDOS CON ESE ID");
		}
		session.close();

		return idexiste;
	}

	@Override

	public Pedidos getById(Long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Pedidos pedidoretid = session.byId(Pedidos.class).load(id);

		session.close();

		return pedidoretid;
	}

	@Override

	public <S extends Pedidos> S save(S entity) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(entity);

		session.getTransaction().commit();

		session.close();

		return entity;
	}

	@Override

	public List<Pedidos> findByFecha(Date Fecha) {

		return null;
	}

}
