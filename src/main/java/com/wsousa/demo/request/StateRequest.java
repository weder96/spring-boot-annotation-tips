package com.wsousa.demo.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wsousa.demo.annotation.ExistsId;
import com.wsousa.demo.annotation.Generated;
import com.wsousa.demo.annotation.UniqueValue;
import com.wsousa.demo.domain.State;
import com.wsousa.demo.domain.Country;

public class StateRequest {

	@NotBlank
	@UniqueValue(domainClass = State.class,fieldName = "nome")
	private String nome;
	@NotNull
	@ExistsId(domainClass = Country.class, fieldName = "id")
	private Long idPais;

	public StateRequest(@NotBlank String nome, @NotNull Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "NovoEstadoRequest [nome=" + nome + ", idPais=" + idPais + "]";
	}

	public State toModel(EntityManager manager) {
		return new State(nome, manager.find(Country.class, idPais));
	}

}
