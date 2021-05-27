package br.com.zupacademy.lucasmiguins.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max = 500)
	private String descricao;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	

	@Deprecated
	public Opiniao() {}

	public Opiniao(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao, @Min(1) @Max(5) Integer nota,
			@NotNull Usuario usuario, @NotNull Produto produto) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.usuario = usuario;
		this.produto = produto;
	}
}
