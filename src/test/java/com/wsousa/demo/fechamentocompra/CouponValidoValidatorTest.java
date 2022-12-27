package com.wsousa.demo.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.wsousa.demo.domain.Coupon;
import com.wsousa.demo.repository.CouponRepository;
import com.wsousa.demo.request.PurchaseRequest;
import com.wsousa.demo.request.CouponItemRequest;
import com.wsousa.demo.request.OrderRequest;
import com.wsousa.demo.validation.CupomValidoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class CouponValidoValidatorTest {

	private CouponRepository couponRepository = Mockito.mock(CouponRepository.class);
	private List<CouponItemRequest> itens = List.of(new CouponItemRequest(1l, 10));
	private OrderRequest pedidoRequest = new OrderRequest(BigDecimal.TEN, itens);
	private PurchaseRequest request = new PurchaseRequest("email@email.com", "nome",
			"sobrenome", "8967548654", "endereco", "complemento", "cidade",
			1l, "987454778", "54534534", pedidoRequest);
	
	@Test
	@DisplayName("deveria parar caso o cupom esteja invalido")
	void teste1() throws Exception {
		Coupon coupon = new Coupon("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		//o cupom precisa ficar com uma validade para trás do dia de hoje
		ReflectionTestUtils.setField(coupon, "validade", LocalDate.now().minusDays(1));
		
		request.setCodigoCupom("codigo");
		Mockito.when(couponRepository.getByCode("codigo")).thenReturn(coupon);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		CupomValidoValidator validador = new CupomValidoValidator(couponRepository);
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertEquals("Este cupom não é mais válido",errors.getFieldError("codigoCupom").getDefaultMessage());
	}
	
	@Test
	@DisplayName("deveria passar caso o cupom esteja válido")
	void teste2() throws Exception {
		Coupon coupon = new Coupon("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		
		request.setCodigoCupom("codigo");
		Mockito.when(couponRepository.getByCode("codigo")).thenReturn(coupon);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		CupomValidoValidator validador = new CupomValidoValidator(couponRepository);
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria passar caso nao tenha codigo de cupom")
	void teste3() throws Exception {
		Errors errors = new BeanPropertyBindingResult(request , "target");
		CupomValidoValidator validador = new CupomValidoValidator(couponRepository);
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria parar caso já tenha erro")
	void teste4() throws Exception {
		Errors errors = new BeanPropertyBindingResult(request , "target");
		errors.reject("codigo");
		
		CupomValidoValidator validador = new CupomValidoValidator(couponRepository);
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertEquals("codigo",errors.getGlobalErrors().get(0).getCode());
	}	
	
}
