package com.practica.plataformaseriespeliculas.spring.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;

import com.practica.plataformaseriespeliculas.spring.app.service.IService;


@Controller
@SessionAttributes("pelicula")
public class PeliculaController {

	private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

	@Autowired
	private IService serviceDao;
	
	private final static String PELICULA = "pelicula"; 

	@GetMapping(value = "/peliculas")
	public String listarPeliculas(Model model) {
		
		List<PeliculaSerie> peliculas = serviceDao.findPeliculaAll();

		log.info("Cantidad : " + peliculas.size());

		model.addAttribute("title", "Listado de Peliculas");
		model.addAttribute("peliculas", peliculas);
		return "pelicula-serie/listar";
	}

	@GetMapping("/pelicula/form")
	public String crear(Model model) {
        
		PeliculaSerie pelicula = new PeliculaSerie(PELICULA);
		
		
		log.info("Fecha : " + pelicula.getCreateAt());
		log.info("Nombre pelicula : " + pelicula.getTitulo() );
		log.info("Tipo : " + pelicula.getTipo() );
		
		model.addAttribute("pelicula",pelicula);
		model.addAttribute("title", "Crear Pelicula");
		model.addAttribute("boton","Crear Pelicula");
		
		
		return "pelicula-serie/form";
	}

    
	@PostMapping("/pelicula/form")
	public String guardar(@Valid PeliculaSerie pelicula, BindingResult result, Model model,RedirectAttributes flash,
			@RequestParam("file")  MultipartFile imagen , SessionStatus status ) {
		
		log.info("Nombre pelicula : " + pelicula.getTitulo());
		log.info("Nombre calificacion : " + pelicula.getCalificacion());
		log.info("Tipo : " + pelicula.getTipo());
		log.info("Fecha : " + pelicula.getCreateAt());
		log.info("Errores : " + result.getErrorCount());
		
		
		
		if(result.hasErrors()) {
             
			Map< String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err-> {
				errores.put(err.getField(), "El campo".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			
			model.addAttribute("errores", errores);
			model.addAttribute("title", "Crear Pelicula");
			model.addAttribute("boton","Crear Pelicula");
			return "pelicula-serie/form";
		}
		
		status.setComplete();
		return "redirect:/peliculas";
	}
	

}
