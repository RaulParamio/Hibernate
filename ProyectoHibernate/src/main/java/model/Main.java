package model;

import jakarta.persistence.*;

public class Main {

	public static void main(String[] args) {
		
		RepositorioHibernateClienteImpl repohcli = new RepositorioHibernateClienteImpl();		
		RepositorioHibernatePedidosImpl repohped = new RepositorioHibernatePedidosImpl();
		
		
		Cliente prueba = new Cliente("123456788", "Gonzalo", "Ramirez", "gonzalo@gmail.com", "Av La Onu", "Cartagena",
		"Murcia");
		
		Pedidos pedprueba = new Pedidos(4L, prueba, "21/6/2021");

		

		//System.out.println(repohcli.count());

		//repohcli.deleteAll();
		
		//repohcli.save(prueba);

		//repohcli.deleteById(17L);

		//repohcli.existsById(16L);
		
		//repohcli.findAll();

		//repohcli.getByDni("123456787");

		//repohcli.getById(16L);

		//repohcli.getMapAll();
		
		
		
		
			
		//System.out.println(repohped.count());
		
		//repohped.save(pedprueba);

		//repohped.deleteAll();

		//repohped.deleteById(1L);

		//System.out.println(repohped.existsById(1L));

		//System.out.println(repohped.findAll());

		//System.out.println(repohped.getById(1L));

	}

}
