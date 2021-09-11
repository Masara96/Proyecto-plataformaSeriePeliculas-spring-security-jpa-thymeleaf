package com.practica.plataformaseriespeliculas.spring.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String imagen;

	@NotEmpty(message = "Ingrese un titulo")
	@Size(max = 40)
	private String titulo;

	@NotNull
	@Min(1)
	@Max(5)
	private Long calificacion;

	@NotEmpty
	private String tipo;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	@JoinTable(name = "rel_peliserie_pers", joinColumns = @JoinColumn(columnDefinition = "FK_pelicula_serie", nullable = false), inverseJoinColumns = @JoinColumn(columnDefinition = "FK_personaje", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Personaje> personajes;
    
	public PeliculaSerie() {
		prePersist();
	}
	
	
	public PeliculaSerie(String tipo) {
		this.tipo = tipo;
		prePersist();
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
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


	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public String getTipo() {
	   return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	private static final long serialVersionUID = 1L;

}
