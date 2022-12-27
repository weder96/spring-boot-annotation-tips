package com.wsousa.demo.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.wsousa.demo.domain.ItemOrder;
import com.wsousa.demo.domain.Book;
import com.wsousa.demo.annotation.ExistsId;
import com.wsousa.demo.annotation.Generated;

public class CouponItemRequest {

	@NotNull
	@ExistsId(domainClass = Book.class, fieldName = "id")
	private Long idLivro;
	@Positive
	private int quantidade;

	public CouponItemRequest(@NotNull Long idLivro,
							 @Positive int quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}
	
	public Long getIdLivro() {
		return idLivro;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "NovoPedidoItemRequest [idLivro=" + idLivro + ", quantidade="
				+ quantidade + "]";
	}

	public ItemOrder toModel(EntityManager manager) {
		@NotNull Book livro = manager.find(Book.class, idLivro);
		return new ItemOrder(livro,quantidade);
	}
	
}
