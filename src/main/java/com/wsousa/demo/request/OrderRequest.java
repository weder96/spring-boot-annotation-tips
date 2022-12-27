package com.wsousa.demo.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.wsousa.demo.domain.Purchase;
import com.wsousa.demo.domain.ItemOrder;
import com.wsousa.demo.domain.Order;
import org.springframework.util.Assert;

import com.wsousa.demo.annotation.Generated;

public class OrderRequest {

	@Positive
	@NotNull
	private BigDecimal total;
	@Size(min = 1)
	@Valid
	private List<CouponItemRequest> itens = new ArrayList<>();

	public OrderRequest(@Positive @NotNull BigDecimal total,
                        @Size(min = 1) @Valid List<CouponItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}
	
	public List<CouponItemRequest> getItens() {
		return itens;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "NovoPedidoRequest [total=" + total + ", itens=" + itens + "]";
	}

	public Function<Purchase, Order> toModel(EntityManager manager) {
		
		Set<ItemOrder> itensCalculados = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());
		
		return (compra) -> {
			Order order = new Order(compra,itensCalculados);
			Assert.isTrue(order.totalIgual(total),"Olha, o total("+total+") enviado não corresponde ao total real("+ order.total()+"). Itens = "+itensCalculados);
			return order;
		};
						
	}


}
