package com.wsousa.demo.cadastrocupom;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.wsousa.demo.domain.Coupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

public class CouponTest {

	@ParameterizedTest
	@CsvSource({
		"0,true",
		"1,true"				
	})
	void teste1(long valor,boolean resultado) throws Exception {
		Coupon coupon = new Coupon("codigo", BigDecimal.TEN, LocalDate.now().plusDays(valor));
		Assertions.assertEquals(resultado, coupon.isValid());
	}
	
	@Test
	@DisplayName("cupom com data passada nao eh mais valido")
	void teste2() throws Exception {
		Coupon coupon = new Coupon("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		ReflectionTestUtils.setField(coupon, "validade", LocalDate.now().minusDays(1));
		Assertions.assertFalse(coupon.isValid());
	}
	
	@Test
	@DisplayName("não pode criar cupom com data no passado")
	void teste3() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Coupon("codigo", BigDecimal.TEN, LocalDate.now().minusDays(1));
		});
		
	}
}
