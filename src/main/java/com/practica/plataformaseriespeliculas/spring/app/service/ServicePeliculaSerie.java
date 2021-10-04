package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;

import com.practica.plataformaseriespeliculas.spring.app.repository.IPeliculaSerie;

@Service
public class ServicePeliculaSerie implements IServicePeliculaSerie {

	@Autowired
	private IPeliculaSerie peliculaSerieDoa;

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
