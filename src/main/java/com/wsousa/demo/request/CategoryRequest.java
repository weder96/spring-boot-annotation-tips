package com.wsousa.demo.request;

import javax.validation.constraints.NotBlank;

import com.wsousa.demo.annotation.Document;

public class CategoryRequest {

	@Document
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
