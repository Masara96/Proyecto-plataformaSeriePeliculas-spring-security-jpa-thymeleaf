package com.practica.plataformaseriespeliculas.spring.app.models.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "message")
public class Mensaje {
     
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;

	@NotEmpty
	@Size(min = 4,message = "Tiene que tener un tamaño mayor a 4 el nombre.")
	private String nombre;
	
	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})",message = "Tiene que usar numeros del 0 al 9.")
	@Size(min = 10,max = 10,message = "Tiene que contener 10 numeros.")
	private String telefono;
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(max = 2500)
	private String consulta;

	public Mensaje() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
    
	
	

}
