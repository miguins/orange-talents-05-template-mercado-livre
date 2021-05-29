package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Compra;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Transacao;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumGatewayPagamento;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumStatus;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.UniqueValue;

public class PayPalRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Transacao.class, fieldName = "idTransacaoGateway")
	private String idTransacao;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	public PayPalRequest(@NotBlank String idTransacao, @NotNull EnumStatus status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}
	
	public Transacao toModelTransacao(Compra compra) {
		return new Transacao(status.converte(), idTransacao, EnumGatewayPagamento.PAYPAL, compra);
	}

	public EnumStatus getStatus() {
		return status;
	}
}
