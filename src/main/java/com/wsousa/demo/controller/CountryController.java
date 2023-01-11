package com.wsousa.demo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.wsousa.demo.request.CountryRequest;
import com.wsousa.demo.domain.Country;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/country")
	@Transactional
	public String criaPais(@RequestBody @Valid CountryRequest request) {
		Country novoCountry = new Country(request.getNome());
		manager.persist(novoCountry);
		return novoCountry.toString();
	}

}
