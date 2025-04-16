package model;

import java.util.List;


import jakarta.persistence.*;


@Entity
@Table(name = "Cliente")
public class Cliente {
	
	
	
	
	public Cliente() {
		super();
	}
		

	public Cliente(String dni, String nombre, String apellidos, String email, String calle, String municipio,
			String provincia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.calle = calle;
		this.municipio = municipio;
		this.provincia = provincia;
	}

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
	
	
	
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", calle=" + calle + ", municipio=" + municipio + ", provincia=" + provincia + "]";
	}


	public long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
		
		
	
	
}

