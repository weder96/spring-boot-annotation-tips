package com.wsousa.demo.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

import com.wsousa.demo.domain.*;
import com.wsousa.demo.domain.Author;
import com.wsousa.demo.domain.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderTest {

	@DisplayName("verifica se o total do pedido é igual ao passado como argumento")
	@ParameterizedTest
	@CsvSource({
		"10,true",
		"9.99,false",
		"10.01,false"
	})
	void teste1(BigDecimal valor,boolean resultadoEsperado) throws Exception {
		Author author = new Author("nome", "email@email.com", "descricao");
		Category categoria = new Category("categoria");
		Book livro = new Book("titulo", "resumo", "sumario", BigDecimal.TEN, 100, "98754985743", LocalDate.of(2000, 10, 10), author, categoria);
		Set<ItemOrder> itens = Set.of(new ItemOrder(livro, 1));
		
		Country country = new Country("brasil");
		Function<Purchase, Order> funcaoCriaPedido = compra -> {
			return new Order(compra, itens);
		};
		
		Purchase purchase = new Purchase("email@email", "nome", "sobrenome", "58495784", "endereco", "complemento", country, "7654965437", "89567453", funcaoCriaPedido);
		
		Order order = funcaoCriaPedido.apply(purchase);
		
		Assertions.assertEquals(resultadoEsperado, order.totalIgual(valor));
	}
}
