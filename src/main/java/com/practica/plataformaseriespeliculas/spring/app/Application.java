package com.practica.plataformaseriespeliculas.spring.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePersonaje;



@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner {
   
//	@Autowired
//	private IUpdateService updateService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private IServicePeliculaSerie serviceDao;
	
	@Autowired
	private IServicePersonaje servicePersonajeDao;

	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//updateService.deleteAll();
		//updateService.init();
		
		Personaje personaje = servicePersonajeDao.findPersonajesById((long) 1);
		personaje.setImagen("PeterParker.jpg");
		log.info("Personaje : " + personaje.getNombre());
		servicePersonajeDao.savePersonaje(personaje);
		

		Personaje personaje1 = servicePersonajeDao.findPersonajesById((long) 1);
		personaje1.setImagen("MaryJane.jpg");
		log.info("Personaje : " + personaje1.getNombre());
		servicePersonajeDao.savePersonaje(personaje1);
		
		Personaje personaje2 = servicePersonajeDao.findPersonajesById((long) 1);
		personaje2.setImagen("Harold_lyman.png");
		log.info("Personaje : " + personaje2.getNombre());
		servicePersonajeDao.savePersonaje(personaje2);
		
		
	    PeliculaSerie peliculaSerie = serviceDao.findPeliculaSerieById((long) 1);
	    peliculaSerie.setImagen("spiderman.jpeg");
	    log.info("Pelicula : " + peliculaSerie.getTitulo());
	    serviceDao.savePeliculaSerie(peliculaSerie);
	    
	    PeliculaSerie peliculaSerie1 = serviceDao.findPeliculaSerieById((long) 2);
	    peliculaSerie1.setImagen("descarga.jpg");
	    log.info("Pelicula : " + peliculaSerie1.getTitulo());
	    serviceDao.savePeliculaSerie(peliculaSerie1);
	    
	    
//	    PeliculaSerie peliculaSerie1 = serviceDao.findPeliculaSerieById((long) 2);
//	    peliculaSerie1.setImagen("descarga.jpg");
//	    serviceDao.savePeliculaSerie(peliculaSerie1);
	    

	    
		
		
		String password = "12345";
		
		System.out.println("Password 1 : "+ encoder.encode(password).toString() );
		System.out.println("Password 2 : "+ encoder.encode(password).toString() );
		
		
	}

}
