package model;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor 
@NoArgsConstructor   
@Entity
@Data
@ToString(exclude = "pedidos")
@Table(name = "Cliente")
public class Cliente {
	

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Pedidos> pedidos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")	
	private long idCliente;	
	
	@Column(name = "dni", unique=true)
	private String dni;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name = "apellidos")
	private String apellidos;
	
	@Column (name = "email")
	private String email;
	
	@Column ( name = "calle")
	private String calle;
	
	@Column (name = "municipio")
	private String municipio;
	
	@Column (name = "provincia")
	private String provincia;

	public Cliente( String dni, String nombre, String apellidos, String email, String calle,
			String municipio, String provincia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.calle = calle;
		this.municipio = municipio;
		this.provincia = provincia;
	}		
	
	
}

