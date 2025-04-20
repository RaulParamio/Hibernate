package model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor  
@NoArgsConstructor   
@Entity
@Table(name = "Pedidos")
@Data
public class Pedidos {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numPedido")
	private Long numPedido;

	@ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

	@Column(name = "fecha", updatable = false)
	@CreationTimestamp
	private LocalDateTime fecha;


	public Pedidos(Cliente cliente) {
		super();
		this.cliente = cliente;
	}


	
	
	
	
	
	
	
	
}