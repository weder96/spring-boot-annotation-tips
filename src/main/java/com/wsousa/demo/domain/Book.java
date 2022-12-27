package com.wsousa.demo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wsousa.demo.annotation.Generated;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String title;
	private @NotBlank @Size(max = 500) String resume;
	private @NotBlank String summary;
	private @NotNull @Min(20) BigDecimal price;
	private @Min(100) int numberPages;
	private @NotBlank String isbn;
	@NotNull
	private @Future LocalDate dataPublish;
	@ManyToOne
	private @NotNull @Valid Author author;
	@ManyToOne
	private @NotNull @Valid Category category;

	public Book() {

	}

	public Book(@NotBlank String title,
				@NotBlank @Size(max = 500) String resume, @NotBlank String summary,
				@NotNull @Min(20) BigDecimal price, @Min(100) int numberPages,
				@NotBlank String isbn, @Future @NotNull LocalDate dataPublish,
				@NotNull @Valid Author author, @NotNull @Valid Category category) {
				this.title = title;
				this.resume = resume;
				this.summary = summary;
				this.price = price;
				this.numberPages = numberPages;
				this.isbn = isbn;
				this.dataPublish = dataPublish;
				this.author = author;
				this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Author getAutor() {
		return author;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public String getResume() {
		return resume;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public int getNumberPages() {
		return numberPages;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", title=" + title + ", resume=" + resume
				+ ", summary=" + summary + ", price=" + price
				+ ", numberPages=" + numberPages + ", isbn=" + isbn
				+ ", dataPublish=" + dataPublish + ", author=" + author
				+ ", category=" + category + "]";
	}

	public LocalDate getDataPublish() {
		return this.dataPublish;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
		
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	

}
