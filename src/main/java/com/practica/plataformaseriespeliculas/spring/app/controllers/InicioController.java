package com.practica.plataformaseriespeliculas.spring.app.controllers;


import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Mensaje;
import com.practica.plataformaseriespeliculas.spring.app.service.ServiceMail;



@Controller
public class InicioController {
	
	@Autowired
	private ServiceMail serviceMail;
	
	private static final Logger log = LoggerFactory.getLogger(InicioController.class);

    
	
    
	@GetMapping(value = "/")
	public String inicio2(Model model) {
		
		Mensaje mensaje = new Mensaje(); 
		model.addAttribute("mensaje",mensaje);
		
		return "inicio/index";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {
//		model.addAttribute("title", "Inicio de pagina");
		
		Mensaje mensaje = new Mensaje(); 
		model.addAttribute("mensaje",mensaje);
		return "contacto";
	}
	
	
	@PostMapping("/contacto")
	public String contactoEnviar(@Valid Mensaje mensaje, BindingResult result,RedirectAttributes flash) {
	    
		if(result.hasErrors()) {
//			Map< String, String> errores = new HashMap<>();
//			result.getFieldErrors().forEach(err-> {
//			errores.put(err.getField(), "El campo".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
//			});
//
//			model.addAttribute("errores", errores);	
			return "contacto";
		}
		
		log.warn("Para : " + mensaje.getEmail());
		log.warn("Nombre : " + mensaje.getNombre());
		
		String contenido = "Nombre completo: ".concat(mensaje.getNombre()).concat("\n")
				.concat("Telefono : ").concat(mensaje.getTelefono()).concat("\n")
		        .concat("\n" + "Mensaje : " + "\n" ).concat(mensaje.getConsulta());
		
		serviceMail.sendEmail("martinfsarandeses@gmail.com",mensaje.getEmail(),contenido);
		
		return "success_email";
	}
	
	
	

}






