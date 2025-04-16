package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedidos")
public class Pedidos {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numPedido")
	private Long numPedido;

	@ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

	@Column(name = "fecha")
	private String fecha;

	public Pedidos(Long numPedido, Cliente cliente, String fecha) {
		super();
		this.numPedido = numPedido;
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public Pedidos(Long idCliente, String fecha, EntityManager em) {
		super();
		
		this.fecha = fecha;
        this.cliente = em.find(Cliente.class, idCliente);
	}

	public Pedidos() {
		super();
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Pedidos [numPedido=" + numPedido + ", cliente=" + cliente + ", fecha=" + fecha + "]";
	}
	
	
	
	
	
	
	
	
}