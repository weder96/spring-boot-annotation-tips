package com.wsousa.demo.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.wsousa.demo.annotation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.wsousa.demo.domain.Coupon;

public class CouponRequest {

	@NotBlank
	@UniqueValue(domainClass = Coupon.class,fieldName = "codigo")
	private String codigo;
	@Positive
	@NotNull
	private BigDecimal percentualDesconto;
	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	public CouponRequest(@NotBlank String codigo,
						 @Positive @NotNull BigDecimal percentualDesconto) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
	}
	
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Coupon toModel() {
		return new Coupon(codigo,percentualDesconto,validade);
	}

}
