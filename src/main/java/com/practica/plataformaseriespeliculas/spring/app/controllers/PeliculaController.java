package com.practica.plataformaseriespeliculas.spring.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Tipo;
import com.practica.plataformaseriespeliculas.spring.app.service.IService;
//import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes(names = "peliculaSerie")
public class PeliculaController {

	private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

	@Autowired
	private IService serviceDao;

	@GetMapping(value = "/peliculas")
	public String listarPeliculas(Model model) {
		List<PeliculaSerie> peliculas = serviceDao.findPeliculaAll();

		log.info("Cantidad : " + peliculas.size());

		model.addAttribute("titulo", "Listado de Peliculas");
		model.addAttribute("peliculas", peliculas);
		return "pelicula-serie/listar";
	}

	@GetMapping("/pelicula/form")
	public String crear(Model model) {
        
		PeliculaSerie pelicula = new PeliculaSerie(Tipo.PELICULA);
		
		model.addAttribute("titulo", "Crear Pelicula");
		model.addAttribute("boton","Crear Pelicula");
		model.addAttribute("pelicula",pelicula);
		
		return "pelicula-serie/form";
	}



}
