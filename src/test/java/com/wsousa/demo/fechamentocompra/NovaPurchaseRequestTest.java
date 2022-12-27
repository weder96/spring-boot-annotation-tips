package com.wsousa.demo.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import com.wsousa.demo.domain.Category;
import com.wsousa.demo.domain.Purchase;
import com.wsousa.demo.domain.Coupon;
import com.wsousa.demo.domain.Book;
import com.wsousa.demo.domain.Author;
import com.wsousa.demo.domain.State;
import com.wsousa.demo.domain.Country;
import com.wsousa.demo.repository.CouponRepository;
import com.wsousa.demo.request.PurchaseRequest;
import com.wsousa.demo.request.CouponItemRequest;
import com.wsousa.demo.request.OrderRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class NovaPurchaseRequestTest {

	private EntityManager manager = Mockito.mock(EntityManager.class);
	private CouponRepository couponRepository = Mockito
			.mock(CouponRepository.class);

	private List<CouponItemRequest> itens = List
			.of(new CouponItemRequest(1l, 5));
	private OrderRequest pedido = new OrderRequest(new BigDecimal("50"),
			itens);
	
	private Country country = new Country("pais");
	private Author author = new Author("nome", "email@email.com", "descricao");
	private Category categoria = new Category("categoria");
	private Book livro = new Book("titulo", "resunmo", "sumario", BigDecimal.TEN,
			100, "97834985782", LocalDate.of(2000, 10, 10), author,
			categoria);
	
	{
		Mockito.when(manager.find(Country.class, 1l)).thenReturn(country);
		
		Mockito.when(manager.find(Book.class, 1l)).thenReturn(livro);
		
		Mockito.when(manager.find(State.class, 1l))
		.thenReturn(new State("estado", country));
		
		Mockito.when(couponRepository.getByCode("codigo-cupom"))
		.thenReturn(new Coupon("codigo-cupom", BigDecimal.TEN,
				LocalDate.now().plusDays(1l)));
	}
	
	private PurchaseRequest request = new PurchaseRequest("email@email.com",
			"Alberto", "Souza", "11111111", "endereco", "complemento",
			"salvador", 1l, "999999999", "400000", pedido);
	
	@Test
	@DisplayName("cria compra com estado e cupom")
	void teste1() throws Exception {

		request.setCodigoCupom("codigo-cupom");
		request.setIdEstado(1l);
		Purchase novaPurchase = request.toModel(manager, couponRepository);

		Assertions.assertNotNull(novaPurchase);
		Mockito.verify(manager).find(State.class, 1l);
		Mockito.verify(couponRepository).getByCode("codigo-cupom");

	}
	
	@Test
	@DisplayName("cria compra sem estado e com cupom")
	void teste2() throws Exception {
		request.setCodigoCupom("codigo-cupom");
		Purchase novaPurchase = request.toModel(manager, couponRepository);
		
		Assertions.assertNotNull(novaPurchase);
		//abre o find para garantir que tal find nao eh para ser chamado nunca
		Mockito.verify(manager,Mockito.never()).find(Mockito.eq(State.class), Mockito.anyLong());
		Mockito.verify(couponRepository).getByCode("codigo-cupom");
		
	}
	
	@Test
	@DisplayName("cria compra sem estado e sem cupom")
	void teste3() throws Exception {
		Purchase novaPurchase = request.toModel(manager, couponRepository);
		
		Assertions.assertNotNull(novaPurchase);
		
		//usa o matcher para facilitar achar o bug, já que as buscas nunca devem ser chamadas com nada
		Mockito.verify(manager,Mockito.never()).find(Mockito.eq(State.class), Mockito.anyLong());
		Mockito.verify(couponRepository,Mockito.never()).getByCode(Mockito.anyString());
		
	}
	
	/*
	 * cpf true cnpj false
	 * cpf false cnpj true
	 * cpf false e cnpj false
	 */
	@ParameterizedTest
	@DisplayName("verifica documento válido")
	@CsvSource({
		"79744157038,true","43134638000128,true","987453895349,false"
	})
	void teste4(String documento,boolean resultadoEsperado) throws Exception {
		PurchaseRequest request = new PurchaseRequest("email@email.com",
				"Alberto", "Souza", documento, "endereco", "complemento",
				"salvador", 1l, "999999999", "400000", pedido);
		
		Assertions.assertEquals(resultadoEsperado, request.documentoValido());
	}
}
