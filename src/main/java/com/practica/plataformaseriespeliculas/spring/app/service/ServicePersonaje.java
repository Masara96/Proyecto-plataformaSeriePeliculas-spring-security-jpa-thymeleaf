package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;
import com.practica.plataformaseriespeliculas.spring.app.repository.IPersonaje;

@Service
public class ServicePersonaje implements IServicePersonaje {
     
	@Autowired
	private IPersonaje personajeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findByNombre(String term) {
		return personajeDao.findByNombre(term);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findPersonajesAll() {
		return personajeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Personaje findPersonajesById(Long id) {
		return personajeDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void savePersonaje(Personaje personaje) {
		personajeDao.save(personaje);
	}

	@Override
	@Transactional
	public void deletePersonaje(Long id) {
		personajeDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Personaje> findAll(Pageable pageable) {
		return personajeDao.findAll(pageable);
	}


}
