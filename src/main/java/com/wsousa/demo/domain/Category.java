package com.wsousa.demo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String name;

	public Category() {

	}

	public Category(@NotBlank String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", nome=" + name + "]";
	}

}
