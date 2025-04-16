package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.NoResultException;


public class RepositorioHibernateClienteImpl implements RepositorioHibernateCliente {

	Session session = HibernateUtil.getSessionFactory().openSession();
	private static final Logger logger = LoggerFactory.getLogger(RepositorioHibernatePedidosImpl.class);

	public long count() {

		session.beginTransaction();

		String counthql = "SELECT COUNT(*)from Cliente";
		long contador = (long) session.createQuery(counthql, Long.class).uniqueResult();

		session.close();
		
		System.out.print("\n"+"El numero total de clientes es: ");

		return contador;

	}

	public void deleteById(Long id) {

		session.beginTransaction();

		try {
	        Cliente clientedelid = session.get(Cliente.class, id);

	        if (clientedelid != null) {
	            session.remove(clientedelid);
	            logger.info("Cliente con ID {} eliminado correctamente.", id);
	        } else {
	        	logger.warn("No se puede eliminar: no existe un cliente con ID {}", id);
	        }

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        logger.error("Fallo al eliminar cliente con ID {}: {}", id, e.getMessage(), e);
	    } finally {
	        session.close();
	    }
	}

	
	public void deleteAll() {
		
		try {
		session.beginTransaction();

		    List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
		    for (Cliente cliente : clientes) {
		        session.remove(cliente);
		        logger.info("Todos los clientes han sido eliminados con exito");
		    }
		    session.getTransaction().commit();
		}catch(Exception e) {
			logger.error("No se han podido eliminar todos los clientes", e);
		}
		 finally {   
		    session.close();
		 }
	}

	public boolean existsById(Long id) {

		boolean idexiste = false;
		try {
		session.beginTransaction();

		if (session.get(Cliente.class, id) != null) {
			idexiste = true;
			logger.info("El cliente con ID: {} , EXISTE",id );
		} else {
			idexiste = false;
			logger.info("El cliente con ID: {} , NO EXISTE",id );
		}
		}catch(Exception e) {
			logger.error("Error al realizar la comprobacion del cliente con ID: {}", id, e);
		}
		finally {
			session.close();
		}
		return idexiste;
	}


	public List<Cliente> findAll() {

		session.beginTransaction();

		List<Cliente> lista = session.createQuery("FROM Cliente", Cliente.class).list();
		
		 if (lista.isEmpty()) {
		        logger.info("No hay clientes en la base de datos.");
		        }
		 else {
			 logger.info("Listado de clientes: ");
			 for(Cliente c:lista) {
				 logger.info("\n"+c.toString());
			 }
		 }

		session.close();

		return lista;

	}

	@Override

	public Cliente getById(Long id) {

		Cliente clienteretid=null;
		
		try {
		session.beginTransaction();
		clienteretid = session.get(Cliente.class, id);
		if (clienteretid != null) {
            logger.info("Cliente encontrado con ID {}: {}", id, clienteretid);
        } else {
            logger.warn("No se encontró ningún cliente con ID {}", id);
        }
		}catch(Exception e) {
			logger.error("ERROR al obtener cliente por ID: {}",id ,e);
		}
		finally {
		session.close();
		}
		return clienteretid;

	}

	@Override

	public <S extends Cliente> S save(S entity) {
		session.beginTransaction();

		session.persist(entity);
		session.flush();

		session.getTransaction().commit();

		session.close();
		
		logger.info("Entidad guardada correctamente con ID: {}", entity.getIdCliente());

		return entity;

	}

	@Override

	public Map<String, Cliente> getMapAll() {
		Map<String, Cliente> mapacli = new HashMap<>();
		
		try {
		session.beginTransaction();

		List<Cliente> listacli = session.createQuery("FROM Cliente", Cliente.class).list();
		for (Cliente clientegma : listacli) {
			mapacli.put(clientegma.getDni(), clientegma);
		}
		}catch(Exception e) {
			logger.error("Error al obtener los clientes: {}", e.getMessage());	
		}
		finally {
		session.close();
		}
		
		logger.info("Clientes en la base de datos:");
	    for (Cliente cliente : mapacli.values()) {
	        logger.info("\n" + cliente);
	    }
		
		return mapacli;

	}

	@Override

	public Cliente getByDni(String dni) {
		Cliente cliente = new Cliente();
	try{
		session.beginTransaction();
		cliente = session.createQuery("FROM Cliente WHERE dni = :dni", Cliente.class).setParameter("dni", dni).uniqueResult();
		if(cliente!=null) {
		logger.info("El cliente con dni:{} es: {}" ,dni, cliente);
		}else {
			logger.info("El cliente con dni:{} NO EXISTE" ,dni);
		}
	     
	}catch (Exception e) {
		 logger.error("Error al obtener clientes por DNI", e);
  
    }finally {
    	session.close();
    }

		return cliente;
	}

}