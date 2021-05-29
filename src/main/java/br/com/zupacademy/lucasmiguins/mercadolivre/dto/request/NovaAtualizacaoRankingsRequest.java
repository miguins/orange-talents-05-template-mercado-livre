package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.NotNull;

public class NovaAtualizacaoRankingsRequest {
	
	@NotNull
	private Long idCompra;
	
	@NotNull
	private Long idDonoProduto;

	public NovaAtualizacaoRankingsRequest(Long idCompra, Long idDonoProduto) {
		this.idCompra = idCompra;
		this.idDonoProduto = idDonoProduto;
	}

	@Override
	public String toString() {
		return "NovaAtualizacaoRankingsRequest [idCompra=" + idCompra + ", idDonoProduto=" + idDonoProduto + "]";
	}
}
