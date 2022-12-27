package com.wsousa.demo.repository;

import org.springframework.stereotype.Repository;

import com.wsousa.demo.domain.Coupon;

@Repository
public interface CouponRepository extends org.springframework.data.repository.Repository<Coupon, Long> {

	/**
	 * Busca por um cupom que existe no sistema
	 * @param code
	 * @return
	 */
	public Coupon getByCode(String code);
}
