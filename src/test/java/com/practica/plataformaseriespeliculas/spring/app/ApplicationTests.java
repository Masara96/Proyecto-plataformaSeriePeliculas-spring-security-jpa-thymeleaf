package com.practica.plataformaseriespeliculas.spring.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.practica.plataformaseriespeliculas.spring.app.models.entity.PeliculaSerie;
import com.practica.plataformaseriespeliculas.spring.app.service.IServicePeliculaSerie;


@RunWith(SpringRunner.class)
@DataJpaTest
class ApplicationTests {

	@Autowired
	private IServicePeliculaSerie serviceDao;

	private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

	@Test
	void agregarCaracteristica() {

		PeliculaSerie pelicula = serviceDao.findPeliculaSerieById((long) 1);

		pelicula.setSinopsis("Mientras tanto, la responsabilidad de Kirby había recaido en el desarrollo de "
				+ "la historia y los poderes del superhéroe"
				+ ". Simon también reconoció ");

		log.info("Pelicula :" + pelicula.getTitulo());

		serviceDao.savePeliculaSerie(pelicula);

	}

	@Test
	void guardarPelicula() {

		PeliculaSerie pelicula = new PeliculaSerie();
 
		pelicula.setTitulo("CReacion");
		pelicula.setCalificacion(4);
		pelicula.setSinopsis("adasda");
		pelicula.setTipo("PELICULA");
		pelicula.setDescripcion("asdasd");;
		
		
		log.info("Entroo :" + pelicula.getTitulo());

		serviceDao.savePeliculaSerie(pelicula);
	}

}
