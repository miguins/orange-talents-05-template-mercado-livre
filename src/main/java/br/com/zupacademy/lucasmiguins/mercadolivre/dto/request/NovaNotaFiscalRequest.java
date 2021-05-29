package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.NotNull;

public class NovaNotaFiscalRequest {
	
	@NotNull
	private Long idCompra;
	
	@NotNull
	private Long idUsuario;
	
	public NovaNotaFiscalRequest(Long idCompra, Long idUsuario) {
		this.idCompra = idCompra;
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "NovaNotaFiscalRequest [idCompra=" + idCompra + ", idUsuario=" + idUsuario + "]";
	}
}
