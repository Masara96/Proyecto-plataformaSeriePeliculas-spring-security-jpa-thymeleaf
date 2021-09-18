package com.practica.plataformaseriespeliculas.spring.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;


public interface IPersonaje extends JpaRepository<Personaje, Long> {
    
	
	public List<Personaje> findByNombreLikeIgnoreCase(String term); 
	
	
}
