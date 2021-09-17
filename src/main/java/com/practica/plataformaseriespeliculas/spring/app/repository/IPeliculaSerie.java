package com.practica.plataformaseriespeliculas.spring.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;


public interface IPeliculaSerie extends JpaRepository<PeliculaSerie, Long> {
       
	@Query("Select p from PeliculaSerie p where p.tipo like 'PELICULA'")
	public List<PeliculaSerie> findByTipoPelicula();
	
	@Query("Select p from PeliculaSerie p where p.tipo like 'SERIE'")
	public List<PeliculaSerie> findByTipoSerie();
}
