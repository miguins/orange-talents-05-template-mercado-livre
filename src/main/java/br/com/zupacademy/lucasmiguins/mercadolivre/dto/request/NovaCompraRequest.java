package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Compra;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumGatewayPagamento;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumStatusCompra;

public class NovaCompraRequest {
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumGatewayPagamento gateway;
	

	public NovaCompraRequest(@NotNull @Positive Integer quantidade, @NotNull EnumGatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.gateway = gateway;
	}

	public Compra toModel(Produto produto, Usuario usuarioLogado) {
		return new Compra(this.quantidade, produto.getValor(), this.gateway, EnumStatusCompra.INICIADA, produto, usuarioLogado);
	}

	public Integer getQuantidade() {
		return quantidade;
	}
}
