package com.wsousa.demo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.wsousa.demo.domain.Author;
import com.wsousa.demo.request.AuthorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorsController {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/authors")
	@Transactional
	public String cria(@RequestBody @Valid AuthorRequest request) {
		Author author = request.toModel();
		manager.persist(author);
		return author.toString();
	}

	
}
