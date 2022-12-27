package com.wsousa.demo.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wsousa.demo.domain.Book;
import com.wsousa.demo.response.DetalheSiteLivroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetailsBookController {

	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/produtos/{id}")
	public DetalheSiteLivroResponse detalhe(@PathVariable("id") Long id) {

		// 1
		Book livroBuscado = Optional.ofNullable(manager.find(Book.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// 1
		DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(
				livroBuscado);
		return detalheSiteLivroResponse;
	}

}
