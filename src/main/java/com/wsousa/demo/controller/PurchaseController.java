package com.wsousa.demo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.wsousa.demo.domain.Purchase;
import com.wsousa.demo.validation.CupomValidoValidator;
import com.wsousa.demo.validation.EstadoPertenceAPaisValidator;
import com.wsousa.demo.request.PurchaseRequest;
import com.wsousa.demo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {
	
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CupomValidoValidator cupomValidoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidator,cupomValidoValidator);
	}

	@PostMapping(value = "/purchase")
	@Transactional
	public String cria(@RequestBody @Valid PurchaseRequest request) {
		
		Purchase newPurchase = request.toModel(manager, couponRepository);
		manager.persist(newPurchase);
		
		return newPurchase.toString();
	}
	
}
