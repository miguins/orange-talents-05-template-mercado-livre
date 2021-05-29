package br.com.zupacademy.lucasmiguins.mercadolivre.model.enums;

import javax.validation.constraints.NotNull;

public enum EnumStatus {
	
	SUCESSO,
	ERRO;

	@NotNull
	public EnumStatusTransacao converte() {
		if (this.equals(SUCESSO)) {
			return EnumStatusTransacao.SUCESSO;
		}
		
		return EnumStatusTransacao.ERRO;
	}
}
