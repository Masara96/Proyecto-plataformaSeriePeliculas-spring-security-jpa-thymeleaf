package com.practica.plataformaseriespeliculas.spring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practica.plataformaseriespeliculas.spring.app.service.IUpdateService;

@SpringBootApplication
public class Application implements CommandLineRunner {
   
	@Autowired
	private IUpdateService updateService;
	

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//updateService.deleteAll();
		updateService.init();
		
	
	}

}
