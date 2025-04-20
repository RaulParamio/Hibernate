package model;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionExecutor {
	private static final Logger logger = LoggerFactory.getLogger(TransactionExecutor.class);
	
	 // Método para gestionar transacciones
   public static <T> T executeTransaction(TransactionCallback<T> transaction) {
       Session session = HibernateUtil.getSessionFactory().openSession();
       T result = null;
       try {
           session.beginTransaction();
           result = transaction.execute(session); // Ejecuta la operación transaccional
           session.getTransaction().commit();
       } catch (Exception e) {
           logger.error("Error en transacción", e);
           if (session.getTransaction() != null) {
               session.getTransaction().rollback(); // Rollback si hay un error
           }
       } finally {
           session.close(); // Asegura que la sesión siempre se cierra
       }
       return result;
   }
}
