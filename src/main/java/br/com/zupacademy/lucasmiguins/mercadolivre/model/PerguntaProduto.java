package br.com.zupacademy.lucasmiguins.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PerguntaProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	private LocalDateTime dataCriacao;
	
	@NotNull
	@ManyToOne
	private Usuario usuarioCriacao;
	
	@NotNull
	@ManyToOne
	private Produto produto;

	@Deprecated
	public PerguntaProduto() {}

	public PerguntaProduto(@NotBlank String titulo, @NotNull Usuario usuarioCriacao, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuarioCriacao = usuarioCriacao;
		this.produto = produto;
		
		this.dataCriacao = LocalDateTime.now();
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getNomeProduto() {
		return this.produto.getNome();
	}

	public String getEmailUsuarioPergunta() {
		return this.usuarioCriacao.getUsername();
	}
	
	public String getEmailDonoProduto() {
		return this.produto.getUsuarioCriacao().getUsername();
	}
}
