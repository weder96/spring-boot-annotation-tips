package com.wsousa.demo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.wsousa.demo.domain.Category;
import com.wsousa.demo.domain.Book;
import com.wsousa.demo.request.BookRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wsousa.demo.domain.Author;

@RestController
public class BookController {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/livros")
	@Transactional
	//1
	public String cria(@RequestBody @Valid BookRequest request) {
		//1
		Book novoLivro = request.toModel(
				id -> manager.find(Author.class, id),
				id -> manager.find(Category.class, id)
				);
		manager.persist(novoLivro);
		return novoLivro.toString();
	}

}
