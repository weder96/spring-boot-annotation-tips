package com.wsousa.demo.validation;

import javax.persistence.EntityManager;

import com.wsousa.demo.request.PurchaseRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.wsousa.demo.domain.State;
import com.wsousa.demo.domain.Country;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

	private EntityManager manager;

	public EstadoPertenceAPaisValidator(EntityManager manager) {
		super();
		this.manager = manager;
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

		if (request.temEstado()) {
			Country country = manager.find(Country.class, request.getIdPais());
			State state = manager.find(State.class, request.getIdEstado());
			if (!state.isBelongsCountry(country)) {
				errors.rejectValue("idEstado", null, "este estado não é o do país selecionado");
			}
		}

	}

}
