package br.com.zupacademy.lucasmiguins.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumGatewayPagamento;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumStatusCompra;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumGatewayPagamento gateway;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumStatusCompra status;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	private LocalDateTime dataCriacao;
	
	@Deprecated
	public Compra() {}

	public Compra(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal valor,
			@NotNull EnumGatewayPagamento gateway, @NotNull EnumStatusCompra status, @NotNull Produto produto,
			@NotNull Usuario usuario) {
		this.quantidade = quantidade;
		this.valor = this.getValorTotalCompra(produto.getValor());
		this.gateway = gateway;
		this.status = status;
		this.produto = produto;
		this.usuario = usuario;
		
		this.dataCriacao = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getNomeUsuarioCompra() {
		return this.usuario.getUsername();
	}
	
	public String getEmailDonoProduto() {
		return this.produto.getUsuarioCriacao().getUsername();
	}
	
	public String getNomeProduto() {
		return this.produto.getNome();
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public BigDecimal getValorProduto() {
		return this.produto.getValor();
	}
	
	public BigDecimal getValorTotalCompra(BigDecimal valor) {
		return valor.multiply(new BigDecimal(this.quantidade));
	}

	public String getUrlRetorno(Long id, UriComponentsBuilder uricb) {
		return this.gateway.obterUrlGateway(id.toString(), uricb);
	}
}
