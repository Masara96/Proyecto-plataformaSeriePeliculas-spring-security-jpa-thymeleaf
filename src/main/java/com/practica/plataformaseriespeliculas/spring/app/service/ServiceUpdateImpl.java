package com.practica.plataformaseriespeliculas.spring.app.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServiceUpdateImpl implements IUpdateService {
     
	private final static Logger log = LoggerFactory.getLogger(ServiceUpdateImpl.class);
	
	private final static String UPLOAD = "upload";
	
	@Override
	public Resource load(String filename) throws MalformedURLException {
		
		Path pathImg = getPath(filename);
		log.info("pathFoto: " + pathImg);
		
		Resource recurso = new UrlResource(pathImg.toUri());
		
		if (!recurso.exists() || !recurso.isReadable()) {
		  throw new RuntimeException("ERROR : no se puede cargar la imagen");
		}
		
		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		Path rootPath = getPath(uniqueFilename);
		log.info("rootPath: " + rootPath);
		
		Files.copy(file.getInputStream(),rootPath);
	
		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {
		
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		
		 if(archivo.exists() && archivo.canRead()) {
			 if(archivo.delete()) {
				 return true;
			 }
		 }
		
		return false;
	}

	@Override
	public void deleteAll() {
	   FileSystemUtils.deleteRecursively(Paths.get(UPLOAD).toFile());
	}

	@Override
	public void init() throws IOException {
		Files.createDirectories(Paths.get(UPLOAD));
		
	}
	
	public Path getPath(String filename) {
		return Paths.get(UPLOAD).resolve(filename).toAbsolutePath();
	}
	

}
