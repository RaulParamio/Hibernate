package repository;

import java.sql.Date;
import java.util.List;

import model.Pedidos;

public interface IRepositorioPedido extends IRepositorio<Pedidos,Long>{

	List<Pedidos> findByFecha(Date Fecha);
}
