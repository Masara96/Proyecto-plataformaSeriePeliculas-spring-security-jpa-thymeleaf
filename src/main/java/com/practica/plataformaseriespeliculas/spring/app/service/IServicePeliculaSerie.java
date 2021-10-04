package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;


public interface IServicePeliculaSerie {

	
	public List<PeliculaSerie> findPeliculasSerieAll();

	public PeliculaSerie findPeliculaSerieById(Long id);

	public void savePeliculaSerie(PeliculaSerie peliculaSerie);

	public void deletePeliculaSerie(Long id);
	
	public Page<PeliculaSerie> findPeliculaAll(Pageable pageable);
	

	//-----------------------------------------------------------------
	
	public List<PeliculaSerie> findPeliculaAll();
	
	public List<PeliculaSerie> findSerieAll();
	
	public Page<PeliculaSerie> findSerieAll(Pageable pageable);


}
