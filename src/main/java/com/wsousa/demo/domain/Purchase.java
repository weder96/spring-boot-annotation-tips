package com.wsousa.demo.domain;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	@ManyToOne
	private @NotNull Country country;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	@ManyToOne
	private State state;
	@OneToOne(mappedBy = "purchase",cascade = CascadeType.PERSIST)
	private Order order;
	@Embedded
	private CouponApplied couponApplied;

	public Purchase(@Email @NotBlank String email, @NotBlank String nome,
					@NotBlank String sobrenome, @NotBlank String documento,
					@NotBlank String endereco, @NotBlank String complemento,
					@NotNull Country country, @NotBlank String telefone,
					@NotBlank String cep, Function<Purchase, Order> funcaoCriacaoPedido) {
				this.email = email;
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.country = country;
				this.telefone = telefone;
				this.cep = cep;
				this.order = funcaoCriacaoPedido.apply(this);
	}

	public Purchase() {

	}


	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome
				+ ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento
				+ ", pais=" + country + ", telefone=" + telefone + ", cep=" + cep
				+ ", estado=" + state + ", pedido=" + order
				+ ", cupomAplicado=" + couponApplied + "]";
	}



	public void setState(@NotNull @Valid State state) {
		Assert.notNull(country,"Não rola associar um estado enquanto o pais for nulo");
		Assert.isTrue(state.isBelongsCountry(country),"Este estado não é do país associado a compra");
		this.state = state;
	}


	public void aplicaCupom(Coupon coupon) {
		Assert.isTrue(coupon.isValid(),"Olha o cupom que está sendo aplicado não está mais válido");
		Assert.isNull(couponApplied,"Olha você não pode trocar um cupom de uma compra");
		this.couponApplied = new CouponApplied(coupon);
	}

}
