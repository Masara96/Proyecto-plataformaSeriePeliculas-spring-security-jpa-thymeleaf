package com.practica.plataformaseriespeliculas.spring.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Usuario;
@Repository
public interface IUsuario extends CrudRepository<Usuario, Long> {
          
	public Usuario findByUsername(String username);
	
	public Usuario findByEmail(String email);
	
}
