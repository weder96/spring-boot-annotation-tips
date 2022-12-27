package com.wsousa.demo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.wsousa.demo.annotation.UniqueValue;
import com.wsousa.demo.domain.Author;

public class AuthorRequest {

	@NotBlank
	private String nome;
	@NotBlank
	@Email
	@UniqueValue(domainClass = Author.class,fieldName = "email")
	private String email;
	@NotBlank
	@Size(max = 400)
	private String descricao;

	public AuthorRequest(@NotBlank String nome,
						 @NotBlank @Email String email,
						 @NotBlank @Size(max = 400) String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Author toModel() {
		return new Author(this.nome,this.email,this.descricao);
	}

	public String getEmail() {
		return this.email;
	}

}
