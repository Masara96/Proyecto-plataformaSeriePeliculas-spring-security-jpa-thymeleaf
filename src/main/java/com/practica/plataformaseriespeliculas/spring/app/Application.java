package com.practica.plataformaseriespeliculas.spring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.practica.plataformaseriespeliculas.spring.app.service.IUpdateService;

@SpringBootApplication
public class Application implements CommandLineRunner {
   
	@Autowired
	private IUpdateService updateService;
	
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args)  {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		updateService.deleteAll();
		updateService.init();
		
		String password = "12345";
		
		System.out.println("Password 1 : "+ encoder.encode(password).toString() );
		System.out.println("Password 2 : "+ encoder.encode(password).toString() );
	}

}
