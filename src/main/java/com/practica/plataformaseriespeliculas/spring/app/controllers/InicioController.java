package com.practica.plataformaseriespeliculas.spring.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePeliculaSerie;

@Controller
public class InicioController {
    
	@Autowired
	private IServicePeliculaSerie servicioDao;
	
	@GetMapping(value = "/")
	public String inicio(Model model) {
		
		List<PeliculaSerie> peliculas = servicioDao.findPeliculaAll(); 
		
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("title", "Inicio de pagina");
		return "inicio/index";
	}
	
}
