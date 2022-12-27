package com.wsousa.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String name;
	private @NotBlank @Email String email;
	private @NotBlank @Size(max = 400) String description;
	private LocalDateTime dtaCreated = LocalDateTime.now();
	
	@Deprecated
	public Author() {

	}

	public Author(@NotBlank String name, @NotBlank @Email String email,
				  @NotBlank @Size(max = 400) String description) {
		this.name = name;
		this.email = email;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", email=" + email
				+ ", description=" + description + ", dtaCreated="
				+ dtaCreated + "]";
	}

	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return description;
	}

}
