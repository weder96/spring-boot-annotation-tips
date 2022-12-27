package com.wsousa.demo.domain;

import com.wsousa.demo.annotation.Generated;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Embeddable
public class ItemOrder {

	@ManyToOne
	private @NotNull Book book;
	private @Positive int quantity;
	@Positive
	private BigDecimal price;

	public ItemOrder() {

	}

	public ItemOrder(@NotNull Book book, @Positive int quantity) {
		this.book = book;
		this.quantity = quantity;
		this.price = book.getPrice();
	}

	@Override
	public String toString() {
		return "ItemOrder [book=" + book + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	public BigDecimal total() {
		return price.multiply(new BigDecimal(quantity));
	}

	@Generated(Generated.ECLIPSE)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		return result;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrder other = (ItemOrder) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}

	
	

}
