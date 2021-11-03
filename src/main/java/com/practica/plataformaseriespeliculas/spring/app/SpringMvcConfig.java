package com.practica.plataformaseriespeliculas.spring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.practica.plataformaseriespeliculas.spring.app.auth.loginSuccessHandler;
import com.practica.plataformaseriespeliculas.spring.app.service.JpaUserDetailsService;

//Configuracion de rutas
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringMvcConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private loginSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
		  .authorizeRequests().antMatchers("/","/css/**","/js/**","/imagen/**","/","/pelicula/mosaico"
				  ,"/serie/mosaico","/personajes/mosaico","/account/**","/upload/**","/api/peliculas").permitAll()
		 .and().formLogin()
		 .successHandler(successHandler)
		 .loginPage("/login")
		 .permitAll()
		 .and().logout().permitAll()
		 .and()
		 .exceptionHandling().accessDeniedPage("/error_403");
	}
	
	@Autowired
	protected void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
	PasswordEncoder encoder = passwordEncoder();
//		
//	UserBuilder users = User.builder().passwordEncoder(password ->{ 
//	return encoder.encode(password);
//	});
	
	builder.userDetailsService(userDetailsService).passwordEncoder(encoder);
	
	}
	
	
}
