package com.wsousa.demo.response;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import com.wsousa.demo.domain.Book;

public class DetalheSiteLivroResponse {

	private DetalheSiteAutorResponse autor;
	private String titulo;
	private String isbn;
	private int numeroPaginas;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private String dataPublicacao;

	public DetalheSiteLivroResponse(Book livro) {
		titulo = livro.getTitle();
		autor = new DetalheSiteAutorResponse(livro.getAutor());
		isbn = livro.getIsbn();
		numeroPaginas = livro.getNumberPages();
		preco = livro.getPrice().setScale(2);
		resumo = livro.getResume();
		sumario = livro.getSummary();
		dataPublicacao = livro.getDataPublish()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}
	
	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public DetalheSiteAutorResponse getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

}
