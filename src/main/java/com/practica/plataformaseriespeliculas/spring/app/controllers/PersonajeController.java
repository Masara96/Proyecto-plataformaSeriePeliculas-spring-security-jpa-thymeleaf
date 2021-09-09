package com.practica.plataformaseriespeliculas.spring.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;
import com.practica.plataformaseriespeliculas.spring.app.service.IService;
import com.practica.plataformaseriespeliculas.spring.app.service.IUpdateService;
import com.practica.plataformaseriespeliculas.spring.app.util.paginator.PageRender;


@Controller
@SessionAttributes("personaje")
public class PersonajeController {

	@Autowired
	private IService serviceDao;

	@Autowired
	private IUpdateService updateService;
	
	private final static Logger log = LoggerFactory.getLogger(PersonajeController.class);
  
	
	@GetMapping( value = "/upload/{filename:.+}")
	public ResponseEntity<Resource> imagen(@PathVariable String filename){
	     
		Resource recurso = null;
		
		try {
			recurso = updateService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename()+"\"").body(recurso);
	}
	
	@GetMapping("/personajes")
	public String listar(@RequestParam(name = "page",defaultValue = "0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Personaje> personajes = serviceDao.findAll(pageRequest);

	    PageRender<Personaje> pageRender = new PageRender<Personaje>("personajes", personajes);
		
		model.addAttribute("titulo", "Lista Personajes");
		model.addAttribute("personajes", personajes);
		model.addAttribute("page",pageRender);
		return "personaje/listar";
	}

	@GetMapping("/personajes/form")
	public String crear(Model model) {
		Personaje personaje = new Personaje();
		model.addAttribute("personaje", personaje);
		model.addAttribute("titulo", "Creacion Personaje");
		model.addAttribute("boton", "Crear personaje");
		return "personaje/form";
	}

	@PostMapping("/personajes/form")
	public String guardar(@Valid Personaje personaje, BindingResult result, Model model,
			@RequestParam("file") MultipartFile imagen, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Creacion Personaje");
			return "personaje/form";
		}

		if (!imagen.isEmpty()) {
			if (personaje.getId() != null && personaje.getId() > 0 && personaje.getImagen() != null) {
				updateService.delete(personaje.getImagen());
			}

			String uniqueFilename = null;

			try {
				uniqueFilename = updateService.copy(imagen);
			} catch (IOException e) {
				System.out.println("ERROR : " + e.getMessage());
			}

			flash.addFlashAttribute("info", "Se ha subido la imagen '" + uniqueFilename + "' correctamente!");

			personaje.setImagen(uniqueFilename);
		}

		String mensajeFlash = (personaje.getId() != null) ? "El personaje ha sido editado correctamente"
				: "El personaje ha sido creado correctamente";
		flash.addFlashAttribute("success", mensajeFlash);

		serviceDao.savePersonaje(personaje);
		status.setComplete();
		return "redirect:/personajes";
	}

	@GetMapping("/personaje/editar/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flash) {

		Personaje personaje = serviceDao.findPersonajesById(id);

		if (personaje == null) {
			flash.addFlashAttribute("error", "No se ha encontrado el personaje!!");
			return "redirect:/personajes";
		}

		model.addAttribute("titulo", "Modificacion del personaje");
		model.addAttribute("boton", "Modificar personaje");
		model.addAttribute("personaje", personaje);

		return "personaje/form";

	}

	@GetMapping("/personaje/ver/{id}")
	public String ver(@PathVariable(name = "id") Long id,Model model,RedirectAttributes flash) {
        
		Personaje personaje = serviceDao.findPersonajesById(id);
		
		if(personaje == null) {
			flash.addFlashAttribute("danger", "No existe el personaje en la BD!!");
			return "redirect:/personajes";
		}
		
		model.addAttribute("titulo", "Detalle Personaje");
		model.addAttribute("personaje", personaje);
		return "personaje/ver";
	}

	@GetMapping("/personaje/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") Long id, RedirectAttributes flash) {

		Personaje personaje = serviceDao.findPersonajesById(id);

		if (personaje == null) {
			flash.addFlashAttribute("danger", "El personaje no existe en la BD");
			return "redirect:/personajes";
		}

		serviceDao.deletePersonaje(id);
        
		if(personaje.getImagen() != null) {
		   if (updateService.delete(personaje.getImagen())) {
               log.info("La imagen '" + personaje.getImagen()+ "' ha sido elimanda");
	       }
		}

		flash.addFlashAttribute("success", "El personaje ha sido eliminado con exito!!");
		return "redirect:/personajes";
	}

}