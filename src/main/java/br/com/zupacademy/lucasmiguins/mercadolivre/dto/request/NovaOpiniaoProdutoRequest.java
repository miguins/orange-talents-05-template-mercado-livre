package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Opiniao;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;

public class NovaOpiniaoProdutoRequest {
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Length(max = 500)
	private String descricao;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;

	public NovaOpiniaoProdutoRequest(@NotBlank String titulo, @NotBlank @Length(max = 500) String descricao,
			@Min(1) @Max(5) Integer nota) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
	}
	
	public Opiniao toModel(Produto produto, Usuario usuarioLogado) {
		return new Opiniao(titulo, descricao, nota, usuarioLogado, produto);
	}
}
