package repository;

import java.util.Map;

import model.Cliente;

public interface RepositorioHibernateCliente extends IRepositorio<Cliente,Long>{

	Map<String,Cliente> getMapAll();
	
	Cliente getByDni(String dni);

}