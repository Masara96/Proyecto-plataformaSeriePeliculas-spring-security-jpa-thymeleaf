package com.practica.plataformaseriespeliculas.spring.app;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
//import com.practica.plataformaseriespeliculas.spring.app.service.IUpdateService;
//import com.practica.plataformaseriespeliculas.spring.app.service.ServiceMail;

@SpringBootApplication
@ComponentScan
public class Application implements CommandLineRunner {

//	@Autowired
//	private IUpdateService updateService;
//     
//	@Autowired
//	private ServiceMail serviceMail;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IServicePeliculaSerie serviceDao;

	@Autowired
	private IServicePersonaje servicePersonajeDao;

//	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		updateService.deleteAll();
//		updateService.init();

		//Datos de inicio
		
		PeliculaSerie pelicula = serviceDao.findPeliculaSerieById((long) 1);
		pelicula.setImagen("spiderman.jpeg");
		pelicula.setDescripcion("Llega el momento de que les hablemos de otro de los principales héroes del Universo de Marvel Cómics… Spider-Man.");
		pelicula.setSinopsis("El estudiante de secundaria y niño prodigio Peter Parker se sumergió en su pasión por la ciencia para evitar las burlas y amenazas de sus compañeros de clase y tropezó con un mundo más allá de lo que imaginaba. Mientras visitaba una exhibición pública de nuevos avances en la manipulación de la radiación y la genética, Parker sintió la mordedura de una araña doméstica común expuesta a un rayo de partículas y se sintió inmediatamente enfermo por ello, sin darse cuenta de cuánto cambiaría su vida en las próximas horas.");
		serviceDao.savePeliculaSerie(pelicula);

		pelicula = serviceDao.findPeliculaSerieById((long) 2);
		pelicula.setImagen("batman.jpg");
		pelicula.setDescripcion("Llega el momento de que les hablemos de otro de los principales héroes del Universo de Marvel Cómics… Spider-Man.");
		pelicula.setSinopsis("El estudiante de secundaria y niño prodigio Peter Parker se sumergió en su pasión por la ciencia para evitar las burlas y amenazas de sus compañeros de clase y tropezó con un mundo más allá de lo que imaginaba. Mientras visitaba una exhibición pública de nuevos avances en la manipulación de la radiación y la genética, Parker sintió la mordedura de una araña doméstica común expuesta a un rayo de partículas y se sintió inmediatamente enfermo por ello, sin darse cuenta de cuánto cambiaría su vida en las próximas horas.");
		serviceDao.savePeliculaSerie(pelicula);

		pelicula = serviceDao.findPeliculaSerieById((long) 3);
		pelicula.setImagen("flash.jpg");
		pelicula.setDescripcion("Llega el momento de que les hablemos de otro de los principales héroes del Universo de Marvel Cómics… Spider-Man.");
		pelicula.setSinopsis("El estudiante de secundaria y niño prodigio Peter Parker se sumergió en su pasión por la ciencia para evitar las burlas y amenazas de sus compañeros de clase y tropezó con un mundo más allá de lo que imaginaba. Mientras visitaba una exhibición pública de nuevos avances en la manipulación de la radiación y la genética, Parker sintió la mordedura de una araña doméstica común expuesta a un rayo de partículas y se sintió inmediatamente enfermo por ello, sin darse cuenta de cuánto cambiaría su vida en las próximas horas.");
		serviceDao.savePeliculaSerie(pelicula);

		pelicula = serviceDao.findPeliculaSerieById((long) 4);
		pelicula.setDescripcion("Llega el momento de que les hablemos de otro de los principales héroes del Universo de Marvel Cómics… Spider-Man.");
		pelicula.setSinopsis("El estudiante de secundaria y niño prodigio Peter Parker se sumergió en su pasión por la ciencia para evitar las burlas y amenazas de sus compañeros de clase y tropezó con un mundo más allá de lo que imaginaba. Mientras visitaba una exhibición pública de nuevos avances en la manipulación de la radiación y la genética, Parker sintió la mordedura de una araña doméstica común expuesta a un rayo de partículas y se sintió inmediatamente enfermo por ello, sin darse cuenta de cuánto cambiaría su vida en las próximas horas.");
		pelicula.setImagen("litenran.jpg");
		serviceDao.savePeliculaSerie(pelicula);

		Personaje personaje = servicePersonajeDao.findPersonajesById((long) 1);
		personaje.setImagen("PeterParker.jpg");
		personaje.setHistoria("En el camino a casa, el adolescente inconscientemente evitó un automóvil descarriado dando un salto increíble a la pared de un edificio cercano, encontrándose milagrosamente capaz de adherirse a él con sus manos y pies. Dándose cuenta rápidamente de que de alguna manera había adquirido las habilidades de una araña, comenzó a probar sus poderes recién descubiertos de los que se maravilló. ");
		servicePersonajeDao.savePersonaje(personaje);

		personaje = servicePersonajeDao.findPersonajesById((long) 2);
		personaje.setImagen("MaryJane.jpg");
		personaje.setHistoria("En el camino a casa, el adolescente inconscientemente evitó un automóvil descarriado dando un salto increíble a la pared de un edificio cercano, encontrándose milagrosamente capaz de adherirse a él con sus manos y pies. Dándose cuenta rápidamente de que de alguna manera había adquirido las habilidades de una araña, comenzó a probar sus poderes recién descubiertos de los que se maravilló. ");
		servicePersonajeDao.savePersonaje(personaje);

		personaje = servicePersonajeDao.findPersonajesById((long) 3);
		personaje.setHistoria("En el camino a casa, el adolescente inconscientemente evitó un automóvil descarriado dando un salto increíble a la pared de un edificio cercano, encontrándose milagrosamente capaz de adherirse a él con sus manos y pies. Dándose cuenta rápidamente de que de alguna manera había adquirido las habilidades de una araña, comenzó a probar sus poderes recién descubiertos de los que se maravilló. ");
		personaje.setImagen("Harold_Lyman.png");
		servicePersonajeDao.savePersonaje(personaje);

		String password = "12345";

		System.out.println("Password 1 : " + encoder.encode(password).toString());
		System.out.println("Password 2 : " + encoder.encode(password).toString());
		

		

	}

}
