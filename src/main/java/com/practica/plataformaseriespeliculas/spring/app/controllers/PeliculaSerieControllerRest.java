package com.practica.plataformaseriespeliculas.spring.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePeliculaSerie;

@RestController
@RequestMapping("/api")
public class PeliculaSerieControllerRest {

	@Autowired
	private IServicePeliculaSerie serviceDao;
	
	@GetMapping("/peliculas")
	public List<PeliculaSerie> listPeliculas(){
		return serviceDao.findPeliculaAll();
	}
	
}
