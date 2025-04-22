package transaction;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HibernateUtil;

public class TransactionExecutor {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionExecutor.class);
	
	 /** Método para gestionar transacciones con Hibernate
	  * Se encarga de abrir la sesion, iniciar la transaccion, ejecutar la operacion,
	  * y luego de hacer commit o rollback segun el resultado 
	  * 
	  */
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
