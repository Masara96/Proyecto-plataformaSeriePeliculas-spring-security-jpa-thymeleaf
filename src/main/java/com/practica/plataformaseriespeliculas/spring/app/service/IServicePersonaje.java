package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;

public interface IServicePersonaje {
         
	public List<Personaje> findPersonajesAll();

	public Personaje findPersonajesById(Long id);
	
	public Page<Personaje> findAll(Pageable pageable);

	public void savePersonaje(Personaje personaje);

	public void deletePersonaje(Long id);
	
	public List<Personaje> findByNombre(String term);
	
}
