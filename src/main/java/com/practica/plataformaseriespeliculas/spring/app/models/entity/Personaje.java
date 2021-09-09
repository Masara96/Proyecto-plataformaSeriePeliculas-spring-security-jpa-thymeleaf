package com.practica.plataformaseriespeliculas.spring.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String imagen;

	@NotEmpty(message = "- No debe ser vacio el nombre.")
	private String nombre;

	@NotEmpty(message = "- No debe ser vacia la edad.")
	private String edad;

	@NotNull(message = "- Debe contener un peso.")
	private Double peso;

	@NotEmpty(message = "- Debe tener una historia.")
	private String historia;

	@ManyToMany(mappedBy = "personajes")
	private List<PeliculaSerie> peliculaSerie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

	public List<PeliculaSerie> getPeliculaSerie() {
		return peliculaSerie;
	}

	public void setPeliculaSerie(List<PeliculaSerie> peliculaSerie) {
		this.peliculaSerie = peliculaSerie;
	}

	private static final long serialVersionUID = 1L;

}
