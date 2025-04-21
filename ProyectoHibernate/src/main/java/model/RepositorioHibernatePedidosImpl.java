package model;

import java.sql.Date;
import java.util.List;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepositorioHibernatePedidosImpl implements IRepositorioPedido {
	
	private static final Logger logger = LoggerFactory.getLogger(RepositorioHibernatePedidosImpl.class);

	// Contar el total de pedidos	
	public long count() {		
		 return TransactionExecutor.executeTransaction(session -> {
            String hql = "SELECT COUNT(*) FROM Pedidos";
            Long count = session.createQuery(hql, Long.class).getSingleResult();
            logger.info("Total de pedidos: {}", count);
            return count;
        });	    
	}

	// Borrar todos de pedidos
	public void deleteAll() {
        TransactionExecutor.executeTransaction(session -> {
            List<Pedidos> pedidos = session.createQuery("FROM Pedidos", Pedidos.class).list();
            pedidos.forEach(session::remove);
            logger.info("Todos los pedidos han sido eliminados correctamente");
            return null;
        });
    }

	// Obtener todos los pedidos
	public List<Pedidos> findAll() {
		 return TransactionExecutor.executeTransaction(session -> {
	            List<Pedidos> lista = session.createQuery("FROM Pedidos", Pedidos.class).list();
	            if (lista.isEmpty()) {
	                logger.info("No hay pedidos en la base de datos.");
	            } else {
	                for (Pedidos p : lista) {
	                    logger.info("Pedido Nº {} - Fecha: {} - Cliente ID: {}",
	                        p.getNumPedido(), p.getFecha(), p.getCliente().getIdCliente());
	                }
	            }
	            return lista;
	        });
	}

	// Método para borrar un pedido por su ID
	@Override
	public void deleteById(Long id) {
		 TransactionExecutor.executeTransaction(session -> {
	            Pedidos pedido = session.get(Pedidos.class, id);
	            if (pedido != null) {
	                session.remove(pedido);
	                logger.info("Pedido con ID {} eliminado correctamente.", id);
	            } else {
	                logger.warn("No se encontró pedido con ID {} para eliminar.", id);
	            }
	            return null;
	        });
	}
	
	// Verifica si un pedido existe por ID
	@Override
	public boolean existsById(Long id) {
		return TransactionExecutor.executeTransaction(session -> {
            boolean exists = session.get(Pedidos.class, id) != null;
            if (exists) {
                logger.info("El pedido con ID {} existe.", id);
            } else {
                logger.warn("El pedido con ID {} no existe.", id);
            }
            return exists;
        });
	}
	
	// Recupera un pedido por ID
	@Override
	public Pedidos getById(Long id) {
		return TransactionExecutor.executeTransaction(session -> {
            Pedidos pedido = session.get(Pedidos.class, id);
            if (pedido != null) {
                logger.info("Pedido encontrado con ID {}: {}", id, pedido);
            } else {
                logger.warn("No se encontró pedido con ID {}.", id);
            }
            return pedido;
        });
	}
	
	// Guarda un pedido en la base de datos
	@Override
	public <S extends Pedidos> S save(S entity) {
		return TransactionExecutor.executeTransaction(session -> {
            session.persist(entity);
            logger.info("Pedido guardado con Nº: {}", entity.getNumPedido());
            return entity;
        });
	}

	 // Busca pedidos por una fecha específica
	 @Override
	public List<Pedidos> findByFecha(Date fecha) {
		 return TransactionExecutor.executeTransaction(session -> {
            String hql = "FROM Pedidos p WHERE DATE(p.fecha) = :fecha";
            Query<Pedidos> query = session.createQuery(hql, Pedidos.class);
            query.setParameter("fecha", fecha);

            List<Pedidos> result = query.list();
            logger.info("Consulta ejecutada correctamente. Se encontraron {} pedidos para la fecha {}", result.size(), fecha);

            if (result.isEmpty()) {
                logger.info("No se encontraron pedidos para la fecha {}", fecha);
            } else {
                for (Pedidos p : result) {
                    logger.info("Pedido Nº {} - Fecha: {} - Cliente ID: {}", p.getNumPedido(), p.getFecha(), p.getCliente().getIdCliente());
                }
            }
            return result;
        });
	}
}