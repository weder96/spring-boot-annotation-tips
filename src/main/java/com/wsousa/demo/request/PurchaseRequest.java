package com.wsousa.demo.request;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wsousa.demo.domain.Purchase;
import com.wsousa.demo.domain.Order;
import com.wsousa.demo.repository.CouponRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.wsousa.demo.domain.Coupon;
import com.wsousa.demo.annotation.Document;
import com.wsousa.demo.annotation.ExistsId;
import com.wsousa.demo.annotation.Generated;
import com.wsousa.demo.domain.State;
import com.wsousa.demo.domain.Country;

public class PurchaseRequest {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
	@Document
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistsId(domainClass = Country.class, fieldName = "id")
	private Long idPais;
	@ExistsId(domainClass = State.class, fieldName = "id")
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	@Valid
	@NotNull
	//1
	private OrderRequest pedido;
	@ExistsId(domainClass = Coupon.class,fieldName = "codigo")
	private String codigoCupom;

	public PurchaseRequest(@Email @NotBlank String email,
						   @NotBlank String nome, @NotBlank String sobrenome,
						   @NotBlank String documento, @NotBlank String endereco,
						   @NotBlank String complemento, @NotBlank String cidade,
						   @NotNull Long idPais, @NotBlank String telefone,
						   @NotBlank String cep, @Valid @NotNull OrderRequest pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = pedido;
	}
	
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}

	public OrderRequest getPedido() {
		return pedido;
	}

	public String getDocumento() {
		return documento;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "NovaCompraRequest [email=" + email + ", nome=" + nome
				+ ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento
				+ ", cidade=" + cidade + ", idPais=" + idPais + ", idEstado="
				+ idEstado + ", telefone=" + telefone + ", cep=" + cep
				+ ", pedido=" + pedido + "]";
	}

	public boolean documentoValido() {
		Assert.hasLength(documento,
				"você nao deveria validar o documento se ele não tiver sido preenchido");

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null)
				|| cnpjValidator.isValid(documento, null);
	}

	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	//1
	public Purchase toModel(EntityManager manager, CouponRepository couponRepository) {
		@NotNull
		//1
		Country country = manager.find(Country.class, idPais);

		//1
		//1
		Function<Purchase, Order> funcaoCriacaoPedido = pedido.toModel(manager);
		
		//1 funcao como argumento
		Purchase purchase = new Purchase(email, nome, sobrenome, documento, endereco,
				complemento, country, telefone, cep,funcaoCriacaoPedido);
		//1
		if (idEstado != null) {
			purchase.setState(manager.find(State.class, idEstado));
		}
		
		//1
		if(StringUtils.hasText(codigoCupom)) {
			Coupon coupon = couponRepository.getByCode(codigoCupom);
			purchase.aplicaCupom(coupon);
		}
		
		
		
		return purchase;
	}

	public boolean temEstado() {
		return Optional.ofNullable(idEstado).isPresent();
	}

	public Optional<String> getCodigoCupom() {
		return Optional.ofNullable(codigoCupom);
	}

}
