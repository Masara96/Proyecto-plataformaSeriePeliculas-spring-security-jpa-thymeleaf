package com.practica.plataformaseriespeliculas.spring.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;


@Repository
public interface IPersonaje extends JpaRepository<Personaje, Long> {
    
	@Query("select p from Personaje p where p.nombre like %?1%")
	public List<Personaje> findByNombre(String term);
	
	public List<Personaje> findByNombreLikeIgnoreCase(String term); 
	
	
}
