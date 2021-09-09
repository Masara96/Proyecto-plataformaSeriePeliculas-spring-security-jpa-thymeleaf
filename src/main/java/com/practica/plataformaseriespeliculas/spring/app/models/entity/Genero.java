package com.practica.plataformaseriespeliculas.spring.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "generos")
public class Genero implements Serializable {
  
	@Id
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String imagen;
	
	
	
	private static final long serialVersionUID = 1L;
	
}
