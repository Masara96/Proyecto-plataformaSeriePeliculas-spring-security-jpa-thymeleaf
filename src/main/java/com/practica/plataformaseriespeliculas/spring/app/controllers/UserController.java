package com.practica.plataformaseriespeliculas.spring.app.controllers;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Role;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Usuario;
import com.practica.plataformaseriespeliculas.spring.app.service.IServiceUsuario;

@Controller
@RequestMapping("/account")
@SessionAttributes("usuario")
public class UserController {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IServiceUsuario usuarioDao;

//	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/form")
	public String usuarioCrear(Model model) {

		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);

		return "form";
	}

	@PostMapping("/form")
	public String usuarioRegistrar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors() || usuarioDao.findbyUsernameUsuario(usuario.getUsername()) || usuarioDao.findbyEmailUsuario(usuario.getEmail()) ) {
			model.addAttribute("error", "ERROR: Ingrese de vuelta los datos!");

			if (usuarioDao.findbyUsernameUsuario(usuario.getUsername())) {
				model.addAttribute("errorUsername", "El username ya se encuentra registrado!");
			}
			if (usuarioDao.findbyEmailUsuario(usuario.getEmail())) {
				model.addAttribute("errorEmail", "El mail ya se encuentra registrado!");
			}
			return "form";
		}

		String password = encoder.encode(usuario.getPassword());
		
		usuario.setPassword(password);
		
		Role role = new Role();
		role.setAuthority("ROLE_USER");
		
		usuario.setRol(role);
		
		usuarioDao.usuarioSave(usuario);
		model.addAttribute("success", "Se ha registrado correctamente");
		status.setComplete();
		return "login";
	}

}
