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

	@GetMapping(value = "/details/{id}")
	public DetalheSiteLivroResponse detalhe(@PathVariable("id") Long id) {

		Book searchBook = Optional.ofNullable(manager.find(Book.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		DetalheSiteLivroResponse detailsBookResponse = new DetalheSiteLivroResponse(searchBook);
		return detailsBookResponse;
	}

}
