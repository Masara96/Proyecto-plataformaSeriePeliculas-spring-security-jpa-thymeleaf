package com.practica.plataformaseriespeliculas.spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Usuario;
import com.practica.plataformaseriespeliculas.spring.app.repository.IUsuario;

@Service
public class ServiceUsuario implements IServiceUsuario {

	@Autowired
	private IUsuario usuarioDao;
	
	@Override
	@Transactional
	public void usuarioSave(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
    
	@Override
	@Transactional
	public void deleteUsuario(Long id) {
		usuarioDao.delete(findUsuarioById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean findbyUsernameUsuario(String username) {
		return (usuarioDao.findByUsername(username) == null)? false:true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean findbyEmailUsuario(String email) {
		return (usuarioDao.findByEmail(email) == null)? false:true;
	}

	
	
}
