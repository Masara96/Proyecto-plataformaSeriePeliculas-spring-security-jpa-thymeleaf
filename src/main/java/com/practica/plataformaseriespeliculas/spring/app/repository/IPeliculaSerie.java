package com.practica.plataformaseriespeliculas.spring.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;

@Repository
public interface IPeliculaSerie extends JpaRepository<PeliculaSerie, Long> {
       
	@Query("Select p from PeliculaSerie p where p.tipo like 'PELICULA'")
	public List<PeliculaSerie> findByTipoPelicula();
	
	@Query("Select p from PeliculaSerie p where p.tipo like 'SERIE'")
	public List<PeliculaSerie> findByTipoSerie();
	
	@Query("Select p from PeliculaSerie p where p.tipo like 'PELICULA'")
	public Page<PeliculaSerie> findPeliculaAll(Pageable pageable);
	
	@Query("Select p from PeliculaSerie p where p.tipo like 'SERIE'")
	public Page<PeliculaSerie> findSerieAll(Pageable pageable);
}
