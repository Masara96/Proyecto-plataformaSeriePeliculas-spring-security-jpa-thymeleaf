package com.practica.plataformaseriespeliculas.spring.app.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePersonaje;
import com.practica.plataformaseriespeliculas.spring.app.service.IUpdateService;
import com.practica.plataformaseriespeliculas.spring.app.util.paginator.PageRender;

@Controller
@SessionAttributes("trabajo")
public class PeliculaSerieController {

	private static final Logger log = LoggerFactory.getLogger(PeliculaSerieController.class);

	@Autowired
	private IServicePeliculaSerie serviceDao;

	@Autowired
	private IServicePersonaje servicePersonajeDao;

	@Autowired
	private IUpdateService updateService;

	@GetMapping(value = "/pelicula/mosaico")
	public String mosaicoPelicula(Model model, RedirectAttributes flash) {

		List<PeliculaSerie> peliculaSerie = null;

		try {
			peliculaSerie = serviceDao.findPeliculaAll();
		} catch (DataAccessException e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		if (peliculaSerie == null) {
			flash.addFlashAttribute("error", "No se encuenta registros de en la Base de Datos");
			return "redirect:/";
		}

		model.addAttribute("title", "Listado de Peliculas");
		model.addAttribute("trabajo", peliculaSerie);
		model.addAttribute("tipo", "PELICULA"); // Se puede cambiar

		return "pelicula-serie/mosaico";
	}

	@GetMapping(value = "/serie/mosaico")
	public String mosaicoSerie(Model model, RedirectAttributes flash) {

		List<PeliculaSerie> peliculaSerie = null;

		try {
			peliculaSerie = serviceDao.findSerieAll();
		} catch (DataAccessException e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		if (peliculaSerie == null) {
			flash.addFlashAttribute("error", "No se encuenta registros de en la Base de Datos");
			return "redirect:/";
		}

		model.addAttribute("title", "Listado de Series");
		model.addAttribute("trabajo", peliculaSerie);
		model.addAttribute("tipo", "SERIE");

		return "pelicula-serie/mosaico";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/peliculas")
	public String listarPeliculas(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			RedirectAttributes flash) {

		List<PeliculaSerie> peliSerie = null;
		Pageable pageRequest = PageRequest.of(page, 4);
		;
		Page<PeliculaSerie> peliculas = null;

		try {
			peliSerie = serviceDao.findPeliculaAll();
			peliculas = serviceDao.findPeliculaAll(pageRequest);
		} catch (DataAccessException e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		PageRender<PeliculaSerie> pageRender = new PageRender<PeliculaSerie>("peliculas", peliculas);
		model.addAttribute("title", "Listado de Peliculas");
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("page", pageRender);

		model.addAttribute("sizePelicula", peliSerie);
		return "pelicula-serie/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/series")
	public String listarSerie(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			RedirectAttributes flash) {

		List<PeliculaSerie> peliSerie = null;
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<PeliculaSerie> series = null;

		try {
			peliSerie = serviceDao.findSerieAll();
			series = serviceDao.findSerieAll(pageRequest);
		} catch (DataAccessException e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		PageRender<PeliculaSerie> pageRender = new PageRender<PeliculaSerie>("series", series);

		model.addAttribute("title", "Listado de Serie");
		model.addAttribute("series", series);
		model.addAttribute("page", pageRender);

		model.addAttribute("sizeSerie", peliSerie);

		return "pelicula-serie/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/cargar-personaje/{term}", produces = { "application/json" })
	public @ResponseBody List<Personaje> cargarPeliculaSerie(@PathVariable String term) {
		return servicePersonajeDao.findByNombre(term);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/pelicula/form")
	public String crearPelicula(Model model) {

		PeliculaSerie pelicula = new PeliculaSerie("PELICULA");
		model.addAttribute("trabajo", pelicula);
		model.addAttribute("title", "Crear Pelicula");

		return "pelicula-serie/form";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/serie/form")
	public String crearSerie(Model model) {

		PeliculaSerie serie = new PeliculaSerie("SERIE");
		model.addAttribute("trabajo", serie);
		model.addAttribute("title", "Crear Serie");

		return "pelicula-serie/form";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/pelicula-serie/form")
	public String guardar(@Valid @ModelAttribute("trabajo") PeliculaSerie trabajo, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId, RedirectAttributes flash,
			@RequestParam("file") MultipartFile imagen, SessionStatus status) {

		if (result.hasErrors()) {
			if (trabajo.getTipo().equalsIgnoreCase("PELICULA")) {
				model.addAttribute("title", "Crear Pelicula");
			} else {
				model.addAttribute("title", "Crear Serie");
			}
			return "pelicula-serie/form";
		}

//		if(itemId == null || itemId.length == 0 ) {
//			
//			if (trabajo.getTipo().equalsIgnoreCase("PELICULA")) {
//				model.addAttribute("title", "Crear Pelicula");
//				model.addAttribute("boton", "Crear Pelicula");
//			} else {
//				model.addAttribute("title", "Crear Serie");
//				model.addAttribute("boton", "Crear Serie");
//			}
//			model.addAttribute("error","ERROR: La pelicula No puede no tener personajes!" );
//			return "pelicula-serie/form";
//		}

		if (!imagen.isEmpty()) {

			if (trabajo.getId() != null && trabajo.getId() > 0 && trabajo.getImagen() != null) {
				updateService.delete(trabajo.getImagen());
			}
			
	

			String uniqueFilename = null;

			try {
				uniqueFilename = updateService.copy(imagen);
			} catch (IOException e) {
				log.error(e.getLocalizedMessage());
			}

			flash.addFlashAttribute("info", "Se ha subido la imagen con exito '" + uniqueFilename + "' correctamente.");
			
			trabajo.setImagen(uniqueFilename);
		}

		if (itemId != null) {
			for (int i = 0; i < itemId.length; i++) {
				Personaje personaje = servicePersonajeDao.findPersonajesById(itemId[i]);
				trabajo.setPersonaje(personaje);
			}
		}

		serviceDao.savePeliculaSerie(trabajo);
		flash.addFlashAttribute("success", "Se ha guardado el registro correctamente!");
		status.setComplete();
         
		
		
		if (trabajo.getTipo().equalsIgnoreCase("PELICULA")) {
			return "redirect:/peliculas";
		} else
			return "redirect:/series";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/pelicula-serie/editar/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {

		PeliculaSerie peliculaSerie = null;

		try {
			peliculaSerie = serviceDao.findPeliculaSerieById(id);
		} catch (DataAccessException e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		if (peliculaSerie == null) {
			flash.addFlashAttribute("error", "ERROR: No se ha encontrado en la Base de Datos");
			return "redirect:/";
		}

		if (peliculaSerie.getTipo().equalsIgnoreCase("PELICULA")) {
			model.addAttribute("title", "Modificar Pelicula");
		} else
			model.addAttribute("title", "Modificar Serie");

		model.addAttribute("boton", "Modificar");
		model.addAttribute("trabajo", peliculaSerie);
		return "pelicula-serie/form";
	}

	@Secured("ROLE_USER")
	@GetMapping(value = "/pelicula-serie/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {

		PeliculaSerie peliculaSerie = null;

		try {
			peliculaSerie = serviceDao.findPeliculaSerieById(id);
		} catch (DataAccessException e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		if (peliculaSerie == null) {
			flash.addFlashAttribute("error", "ERROR: No se ha encontrado en la Base de Datos");
			return "redirect:/";
		}

		if (peliculaSerie.getTipo().equalsIgnoreCase("PELICULA")) {
			model.addAttribute("title", "Detalle Pelicula");
		} else
			model.addAttribute("title", "Detalle Serie");

		model.addAttribute("trabajo", peliculaSerie);
		return "pelicula-serie/ver";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/pelicula-serie/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {

		PeliculaSerie peliculaSerie = null;

		try {
			peliculaSerie = serviceDao.findPeliculaSerieById(id);
		} catch (Exception e) {
			flash.addFlashAttribute("error", "ERROR : " + e.getMessage());
			return "redirect:/";
		}

		if (peliculaSerie == null) {
			flash.addFlashAttribute("error", "No se encuenta el registro en la BD");
			return "redirect:/";
		}

		
		serviceDao.deletePeliculaSerie(id);

//		if (peliculaSerie.getImagen() != null) {
//			if (updateService.delete(peliculaSerie.getImagen())) {
//				log.info("Se ha eliminado la imagen '" + peliculaSerie.getImagen() + "' correctamente");
//			}
//		}

		if (peliculaSerie.getTipo().equalsIgnoreCase("PELICULA")) {
			flash.addFlashAttribute("success", "Se ha eliminado la pelicula correctamente");
			return "redirect:/peliculas";
		} else {
			flash.addFlashAttribute("success", "Se ha eliminado la serie correctamente");
			return "redirect:/series";
		}

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/pelicula-serie/personaje/eliminar")
	@ResponseBody
	public Map<String, Object> eliminarPersonaje(String idpersonaje, String idPeliculaSerie,RedirectAttributes flash) {

		Map<String, Object> result = new HashMap<>();
		Personaje personaje = null;
		PeliculaSerie peliculaSerie = null;
		
		try {
			personaje = servicePersonajeDao.findPersonajesById(Long.parseLong(idpersonaje));
			peliculaSerie = serviceDao.findPeliculaSerieById(Long.parseLong(idPeliculaSerie));	
		} catch (DataAccessException e) {
			result.put("error", e.getMessage());
			return result;
		}
		
		if (peliculaSerie == null || personaje == null) {
			result.put("error", "No se ha podido recuperar");
			return result;
		}

		peliculaSerie.eliminarPersonaje(personaje);
		serviceDao.savePeliculaSerie(peliculaSerie);

		result.put("flag", true);

		return result;
	}

//	Map< String, String> errores = new HashMap<>();
//	result.getFieldErrors().forEach(err-> {
//		errores.put(err.getField(), "El campo".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
//	});

//	model.addAttribute("errores", errores);		

}
