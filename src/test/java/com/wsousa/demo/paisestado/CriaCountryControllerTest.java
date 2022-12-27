package com.wsousa.demo.paisestado;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.wsousa.demo.handler.CustomMockMvc;
import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class CriaCountryControllerTest {
	
	@Autowired
	private CustomMockMvc mvc;
	private static Set<String> unicos = new HashSet<>();

	@Property(tries = 10)
	@Label("fluxo de cadastro de novo pais")
	public void teste(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String nome) throws Exception {
		
		Assumptions.assumeTrue(unicos.add(nome));
		
		mvc.post("/paises", Map.of("nome",nome)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		mvc.post("/paises", Map.of("nome",nome)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
		
	}
	
}
