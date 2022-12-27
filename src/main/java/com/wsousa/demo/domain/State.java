package com.wsousa.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String name;
	@ManyToOne
	private @NotNull @Valid Country country;

	public State() {

	}

	public State(@NotBlank String name, @NotNull @Valid Country country) {
		this.name = name;
		this.country = country;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

	public boolean isBelongsCountry(Country country) {
		return this.country.equals(country);
	}
	
	

}
