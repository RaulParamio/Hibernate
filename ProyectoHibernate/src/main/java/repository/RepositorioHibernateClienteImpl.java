package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.Cliente;
import transaction.TransactionExecutor;


public class RepositorioHibernateClienteImpl implements RepositorioHibernateCliente {

	private static final Logger logger = LoggerFactory.getLogger(RepositorioHibernateClienteImpl.class);
	
	// Contar el total de clientes
	public long count() {
		return TransactionExecutor.executeTransaction(session -> {
		String counthql = "SELECT COUNT(*) FROM Cliente";
		long contador = (long) session.createQuery(counthql, Long.class).uniqueResult();
		logger.info("El numero total de clientes es: {}", contador);
		return contador;
		});	  
	}

	// Borra un cliente por su ID
	public void deleteById(Long id) {
		TransactionExecutor.executeTransaction(session -> {		
	        Cliente cliente = session.get(Cliente.class, id);
	        if (cliente != null) {
	            session.remove(cliente);
	            logger.info("Cliente con ID {} eliminado correctamente.", id);
	        } else {
	        	logger.warn("No se puede eliminar: no existe un cliente con ID {}", id);
	        }
	        return null;
		});  
	}

	// Borra todos los clientes
	public void deleteAll() {
		TransactionExecutor.executeTransaction(session -> {
		    List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
		    for (Cliente cliente : clientes) {
		        session.remove(cliente);
		        logger.info("Todos los clientes han sido eliminados con exito");
		    }
		   return null;
		});
	}

	// Verifica si un cliente existe por ID
	public boolean existsById(Long id) {
		return TransactionExecutor.executeTransaction(session -> {
		boolean idexiste = session.get(Cliente.class, id) != null;
		if (idexiste) {
			logger.info("El cliente con ID: {} , EXISTE", id );
		} else { 
			logger.info("El cliente con ID: {} , NO EXISTE", id );
		}
		return idexiste;
		});
	}


	// Recupera lista de clientes
	public List<Cliente> findAll() {
		return TransactionExecutor.executeTransaction(session -> {
		List<Cliente> lista = session.createQuery("FROM Cliente", Cliente.class).list();		
		 if (lista.isEmpty()) {
		        logger.info("No hay clientes en la base de datos.");
		 } else {
			 logger.info("Listado de clientes: ");
			 for(Cliente c:lista) {
				 logger.info("\n"+c.toString());
			 }
		 }
		 return lista;
		});
	}

	// Recupera un cliente por ID
	@Override
	public Cliente getById(Long id) {
		return TransactionExecutor.executeTransaction(session -> {	
		Cliente cliente = session.get(Cliente.class, id);
		if (cliente != null) {
            logger.info("Cliente encontrado con ID {}: {}", id, cliente);
        } else {
            logger.warn("No se encontró ningún cliente con ID {}", id);
        }
		return cliente;
		});
	}

	// Guarda un cliente en la base de datos
	@Override
	public <S extends Cliente> S save(S entity) {
		return TransactionExecutor.executeTransaction(session -> {
		session.persist(entity);	
		logger.info("Entidad guardada correctamente con ID: {}", entity.getIdCliente());
		return entity;
		});
	}

	 // Recupera todos los clientes y los almacena en un Map con su dni como clave
	@Override	
	public Map<String, Cliente> getMapAll() {
		return TransactionExecutor.executeTransaction(session -> {
		Map<String, Cliente> mapaClientes = new HashMap<>();
		List<Cliente> listaclientes = session.createQuery("FROM Cliente", Cliente.class).list();
		for (Cliente cliente : listaclientes) {
			mapaClientes.put(cliente.getDni(), cliente);
		}		
		logger.info("Clientes en la base de datos:");
	    for (Cliente cliente : mapaClientes.values()) {
	        logger.info("\n" + cliente);
	    }		
		return mapaClientes;
		});
	}

	// Recupera un cliente por dni
	@Override
	public Cliente getByDni(String dni) {
		return TransactionExecutor.executeTransaction(session -> {
		Cliente cliente = session.createQuery("FROM Cliente WHERE dni = :dni", Cliente.class).setParameter("dni", dni).uniqueResult();
		if(cliente != null) {
		logger.info("El cliente con dni:{} es: {}" ,dni, cliente);
		}else {
			logger.info("El cliente con dni:{} NO EXISTE" ,dni);
		}     
		return cliente;
		});
	}

}