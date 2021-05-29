package br.com.zupacademy.lucasmiguins.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumGatewayPagamento;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumStatusTransacao;

@Entity
public class Transacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumStatusTransacao status;
	
	@NotBlank
	private String idTransacaoGateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumGatewayPagamento gateway;
	
	private LocalDateTime dataCriacao;
	
	@NotNull
	@Valid
	@ManyToOne
	private Compra compra;

	@Deprecated
	public Transacao() {}

	public Transacao(@NotNull EnumStatusTransacao status, @NotBlank String idTransacaoGateway,
			@NotNull EnumGatewayPagamento gateway, @NotNull @Valid Compra compra) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.gateway = gateway;
		this.compra = compra;
		
		this.dataCriacao = LocalDateTime.now();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		return true;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(EnumStatusTransacao.SUCESSO);
	}
}
