package model;

import java.sql.Date;

public class Main {

	public static void main(String[] args) {
		
		RepositorioHibernateClienteImpl repohcli = new RepositorioHibernateClienteImpl();		
		RepositorioHibernatePedidosImpl repohped = new RepositorioHibernatePedidosImpl();
		
		
		Cliente prueba = new Cliente("123456788", "Gonzalo", "Ramirez", "gonzalo@gmail.com", "Av La Onu", "Cartagena",
		"Murcia");
		
		//Cliente prueba_rec_cli = repohcli.getByDni("123456788");
		
		//Pedidos pedprueba = new Pedidos(prueba_rec_cli);
	

		

		//System.out.println(repohcli.count());

		//repohcli.deleteAll();
		
		//repohcli.save(prueba);

		//repohcli.deleteById(7L);

		//repohcli.existsById(16L);
		
		//repohcli.findAll();

		//repohcli.getByDni("123456788");

		//repohcli.getById(16L);

		//repohcli.getMapAll();
		
		
		
		
			
		//repohped.count();
		
		//repohped.save(pedprueba);

		//repohped.deleteAll();

		//repohped.deleteById(15L);

		//repohped.existsById(24L);

		//repohped.findAll();

		//repohped.getById(24L);
	
		//Date fecha = Date.valueOf("2025-04-19");
        //repohped.findByFecha(fecha);
      

		
		
		
	}
}