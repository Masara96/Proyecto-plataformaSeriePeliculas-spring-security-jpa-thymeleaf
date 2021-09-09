package com.practica.plataformaseriespeliculas.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.practica.plataformaseriespeliculas.spring.app.models.entity.Personaje;


public interface IPersonaje extends JpaRepository<Personaje, Long> {

}
