package com.practica.plataformaseriespeliculas.spring.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.Role;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Usuario;
import com.practica.plataformaseriespeliculas.spring.app.repository.IUsuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService  {
     
	@Autowired
	private IUsuario usuarioDao;
	
	
	private static final Logger log = LoggerFactory.getLogger(JpaUserDetailsService.class);

	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			log.error("ERROR login: no existe el usuario '" + username + "'" );
			throw new UsernameNotFoundException("Username " + username + ", No existe en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role role : usuario.getRoles()) {
			log.info("ROLE : " + role.getAuthority());
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		
		if(authorities.isEmpty()) {
			log.error("ERROR login: no existe el usuario '" + username + "', No tiene asignados roles!" );
	 		throw new UsernameNotFoundException("Error login : El usuario '" + username + "', No tiene roles asignados!" );
		}
		
		return new User(username, usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
