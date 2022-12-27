package com.wsousa.demo.request;

import javax.validation.constraints.NotBlank;

import com.wsousa.demo.annotation.UniqueValue;
import com.wsousa.demo.domain.Country;

public class CountryRequest {

	@NotBlank
	@UniqueValue(domainClass = Country.class,fieldName =  "nome")
	private String nome;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
