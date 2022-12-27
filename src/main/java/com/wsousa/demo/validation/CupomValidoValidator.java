package com.wsousa.demo.validation;

import java.util.Objects;
import java.util.Optional;

import com.wsousa.demo.request.PurchaseRequest;
import com.wsousa.demo.repository.CouponRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wsousa.demo.domain.Coupon;

@Component
public class CupomValidoValidator implements Validator {

	private CouponRepository couponRepository;

	public CupomValidoValidator(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		PurchaseRequest request = (PurchaseRequest) target;
		Optional<String> possivelCodigo = request.getCodigoCupom();
		if (possivelCodigo.isPresent()) {
			Coupon coupon = couponRepository.getByCode(possivelCodigo.get());
			Assert.state(Objects.nonNull(coupon),"O código do cupom precisa existir neste ponto da validacao");
			if (!coupon.isValid()) {
				errors.rejectValue("codigoCupom", null,
						"Este cupom não é mais válido");
			}
		} 
	}

}
