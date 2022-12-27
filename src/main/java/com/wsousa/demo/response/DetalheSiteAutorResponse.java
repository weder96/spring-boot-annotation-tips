package com.wsousa.demo.response;

import com.wsousa.demo.domain.Author;

public class DetalheSiteAutorResponse  {

	private String nome;
	private String descricao;

	public DetalheSiteAutorResponse(Author author) {
		nome = author.getName();
		descricao = author.getDescription();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
