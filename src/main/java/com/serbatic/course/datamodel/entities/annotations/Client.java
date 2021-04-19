package com.serbatic.course.datamodel.entities.annotations;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CLIENTE")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "ID")
	private int clientId;
	
	@Column(name = "NIF", nullable = false)
    private String nif;
	
	@Column(name = "NOMBRE", nullable = false)
  private String nombre;

  @Column(name = "PRIMER_APELLIDO", nullable = false)
  private String primerApellido;

  @Column(name = "SEGUNDO_APELLIDO", nullable = false)
  private String segundoApellido;
  
  @Column(name = "DIRECCION")
  private String direccion;
  
  @Column(name = "TELEFONO")
  private String telefono;
  
  @Column(name = "EMAIL")
  private String email;
  
  @Transient
  private List<Statement> statements;
  
	public Client() {
		
	}

	public Client(String nif, String nombre, String primerApellido, String segundoApellido,
			String direccion, String telefono, String email) {
		//super();
		//this.clientId = clientId;
		this.nif = nif;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", nif=" + nif + ", nombre=" + nombre + ", primerApellido="
				+ primerApellido + ", segundoApellido=" + segundoApellido + ", direccion=" + direccion + ", telefono="
				+ telefono + ", email=" + email + "]";
	}
	
}
