package com.wsousa.demo.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.wsousa.demo.domain.State;
import com.wsousa.demo.request.StateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {
	
	@Autowired
	private EntityManager manager;

	@PostMapping(value = "/state")
	@Transactional
	public String cria(@RequestBody @Valid StateRequest request) {
		State newState = request.toModel(manager);
		manager.persist(newState);
		
		return newState.toString();
	}

}
