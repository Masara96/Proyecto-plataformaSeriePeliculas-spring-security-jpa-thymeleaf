package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;
import com.practica.plataformaseriespeliculas.spring.app.repository.IPeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.repository.IPersonaje;

@Service
public class ServiceGlobalImp implements IService {
   
	@Autowired
	private IPeliculaSerie peliculaSerieDoa;
	
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

	//<----------------------------------------------------------------------------------------------------------->

	@Override
	@Transactional(readOnly = true)
	public List<PeliculaSerie> findPeliculasSerieAll() {
		return peliculaSerieDoa.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PeliculaSerie findPeliculaSerieById(Long id) {
		return peliculaSerieDoa.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void savePeliculaSerie(PeliculaSerie peliculaSerie) {
		peliculaSerieDoa.save(peliculaSerie);
	}

	@Override
	@Transactional
	public void deletePeliculaSerie(Long id) {
		peliculaSerieDoa.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PeliculaSerie> findPeliculaAll(Pageable pageable) {
		return peliculaSerieDoa.findPeliculaAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PeliculaSerie> findPeliculaAll() {
		return peliculaSerieDoa.findByTipoPelicula();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PeliculaSerie> findSerieAll() {
		return peliculaSerieDoa.findByTipoSerie();
	}

	@Override
	public Page<PeliculaSerie> findSerieAll(Pageable pageable) {
		return peliculaSerieDoa.findSerieAll(pageable);
	}

	

	
	
	

}
