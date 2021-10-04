package com.practica.plataformaseriespeliculas.spring.app.service;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Usuario;

public interface IServiceUsuario {
       
	public void usuarioSave(Usuario usuario);
	
	public void deleteUsuario(Long id);
	 
	public Usuario findUsuarioById(Long id);
	
	public boolean findbyUsernameUsuario(String username);
	
	public boolean findbyEmailUsuario(String email);
}
