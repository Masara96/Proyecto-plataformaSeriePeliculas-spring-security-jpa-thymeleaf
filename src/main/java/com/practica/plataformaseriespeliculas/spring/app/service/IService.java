package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;

public interface IService {

	public List<Personaje> findPersonajesAll();

	public Personaje findPersonajesById(Long id);
	
	public Page<Personaje> findAll(Pageable pageable);

	public void savePersonaje(Personaje personaje);

	public void deletePersonaje(Long id);
	
	//--------------------------------------------------------------
	
	public List<PeliculaSerie> findPeliculasSerieAll();

	public PeliculaSerie findPeliculaSerieById(Long id);

	public void savePeliculaSerie(PeliculaSerie peliculaSerie);

	public void deletePeliculaSerie(Long id);
	
	public Page<PeliculaSerie> findPeliculaSerieAll(Pageable pageable);
	
	//-----------------------------------------------------------------
	
	public List<PeliculaSerie> findPeliculaAll();

}
