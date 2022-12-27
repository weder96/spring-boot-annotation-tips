package com.wsousa.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/library/v1")
public class LibraryController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/all")
	public HashMap<String, Object> list() {
		doStuff("teste logs");

		List autores = manager.createQuery("select a from Author a").getResultList();
		
		HashMap<String, Object> resultado = new HashMap<>();
		resultado.put("Authores", autores.toString());
		
		List categorias = manager.createQuery("select c from Category c").getResultList();
		resultado.put("Categories", categorias.toString());
		
		List cupons = manager.createQuery("select c from Coupon c").getResultList();
		resultado.put("cupons", cupons.toString());
		
		return resultado;
	}

	public void doStuff(final String value) {
		//The 5 W's of Application Logging spring boot
		LOGGER.trace("doStuff needed more information - {}", value);
		LOGGER.debug("doStuff needed to debug - {}", value);
		LOGGER.info("doStuff took input - {}", value);
		LOGGER.warn("doStuff needed to warn - {}", value);
		LOGGER.error("doStuff encountered an error with value - {}", value);
	}

}
