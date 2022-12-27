package com.wsousa.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String code;
	private @Positive @NotNull BigDecimal discountPercentage;
	private @Future @NotNull LocalDate validity;
	
	@Deprecated
	public Coupon() {

	}

	public Coupon(@NotBlank String code,
				  @Positive @NotNull BigDecimal discountPercentage,
				  @FutureOrPresent @NotNull LocalDate validity) {
				Assert.isTrue(validity.compareTo(LocalDate.now()) >= 0,"A validade tem que ser no futuro");
				this.code = code;
				this.discountPercentage = discountPercentage;
				this.validity = validity;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", code=" + code
				+ ", discountPercentage=" + discountPercentage + ", validity="
				+ validity + "]";
	}

	public boolean isValid() {
		return LocalDate.now().compareTo(this.validity) <= 0;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}
	
	public LocalDate getValidity() {
		return validity;
	}
	
	

}
