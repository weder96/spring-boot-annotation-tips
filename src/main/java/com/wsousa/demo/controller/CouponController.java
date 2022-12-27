package com.wsousa.demo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.wsousa.demo.domain.Coupon;
import com.wsousa.demo.request.CouponRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/cupons")
	@Transactional
	public String cria(@RequestBody @Valid CouponRequest request) {
		
		Coupon novoCoupon = request.toModel();
		manager.persist(novoCoupon);
		
		return novoCoupon.toString();
	}

}
