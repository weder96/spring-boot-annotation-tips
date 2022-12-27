package com.wsousa.demo.fechamentocompra;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.wsousa.demo.domain.State;
import com.wsousa.demo.domain.Country;
import com.wsousa.demo.request.PurchaseRequest;
import com.wsousa.demo.request.CouponItemRequest;
import com.wsousa.demo.request.OrderRequest;
import com.wsousa.demo.validation.EstadoPertenceAPaisValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class EstadoPertenceACountryValidatorTest {
	private EntityManager manager = Mockito.mock(EntityManager.class);	
	private Country countryPadrao = new Country("teste");
	private List<CouponItemRequest> itens = List.of(new CouponItemRequest(1l, 10));
	private OrderRequest pedidoRequest = new OrderRequest(BigDecimal.TEN, itens);
	private PurchaseRequest request = new PurchaseRequest("email@email.com", "nome",
			"sobrenome", "8967548654", "endereco", "complemento", "cidade",
			1l, "987454778", "54534534", pedidoRequest);

	@Test
	@DisplayName("deveria validar uma compra com pais e estado pertencente ao pais")
	void teste1() throws Exception {		
		State estadoDoPais = new State("estado", countryPadrao);
		Mockito.when(manager.find(Country.class, 1l)).thenReturn(countryPadrao);
		Mockito.when(manager.find(State.class, 1l)).thenReturn(estadoDoPais);
		request.setIdEstado(1l);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria bloquear compra com pais e estado não pertecente a compra")
	void teste2() throws Exception {				
		Country country2 = new Country("diferente");
		State estadoDeOutroPais = new State("estado", country2);
		Mockito.when(manager.find(State.class, 2l)).thenReturn(estadoDeOutroPais);
		
		Mockito.when(manager.find(Country.class, 1l)).thenReturn(countryPadrao);
		request.setIdEstado(2l);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertTrue(errors.hasFieldErrors("idEstado"));
	}
	
	@Test
	@DisplayName("deveria deixar passar se a compra ta sem estado")
	void teste3() throws Exception {				
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria parar se já tem erro de validacao no fluxo")
	void teste4() throws Exception {				
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		errors.reject("codigoQualquer");
		
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertEquals("codigoQualquer",errors.getGlobalErrors().get(0).getCode());
	}
	
}
