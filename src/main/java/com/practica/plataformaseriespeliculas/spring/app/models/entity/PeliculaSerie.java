package com.practica.plataformaseriespeliculas.spring.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotEmpty(message = "Tiene que contener una imagen")
	private String imagen;

	@NotEmpty(message = "Ingrese un titulo")
	@Size(max = 40)
	private String titulo;

	@NotNull(message = "Tiene que ingresar una calificacion")
	@Min(1)
	@Max(5)
	private int calificacion = 1;
    
	@NotEmpty
	@Size(max = 1500)
	private String sinopsis;
	
	@NotEmpty
	@Size(max = 120)
	private String descripcion;
	
	@NotEmpty
	private String tipo;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	@JoinTable(name = "rel_peliserie_pers", joinColumns = @JoinColumn(columnDefinition = "FK_pelicula_serie", nullable = false), inverseJoinColumns = @JoinColumn(columnDefinition = "FK_personaje", nullable = false))
	@ManyToMany( fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
	@JsonIgnore
	private List<Personaje> personajes;
    
	public PeliculaSerie() {
		prePersist();
	}
	
	
	public PeliculaSerie(String tipo) {
		this.tipo = tipo.toUpperCase();
		prePersist();
		this.personajes = new ArrayList<Personaje>();
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	
	}
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public void eliminarPersonaje(Personaje personajeBuscado) {
	         this.personajes.remove(personajeBuscado);        
	}
	
	
	public void setPersonaje(Personaje personaje) {
		this.personajes.add(personaje);
	}
	
	public Personaje getPersonaje(Long id) {
		
		Personaje personajeObtener = new Personaje();
		
		for (Personaje personaje : personajes) {
			if (personaje.getId() == id) {
				personajeObtener = personaje;
				break;
			}
		}
		return personajeObtener;
	}
	
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}


	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int i) {
		this.calificacion = i;
	}

	public String getTipo() {
	   return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}



	private static final long serialVersionUID = 1L;

}
