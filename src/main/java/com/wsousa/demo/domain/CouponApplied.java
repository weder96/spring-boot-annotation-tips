package com.wsousa.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.wsousa.demo.annotation.Generated;

@Embeddable
public class CouponApplied {

	@ManyToOne
	private Coupon coupon;
	@Positive
	@NotNull
	private BigDecimal percentualDescontoMomento;
	@NotNull
	@Future
	private LocalDate validadeMomento;
	
	@Deprecated
	@Generated(Generated.ECLIPSE)
	public CouponApplied() {

	}

	public CouponApplied(Coupon coupon) {
		this.coupon = coupon;
		this.percentualDescontoMomento = coupon.getDiscountPercentage();
		this.validadeMomento = coupon.getValidity();
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "CupomAplicado [cupom=" + coupon + ", percentualDescontoMomento="
				+ percentualDescontoMomento + ", validadeMomento="
				+ validadeMomento + "]";
	}
	
	

	
}
