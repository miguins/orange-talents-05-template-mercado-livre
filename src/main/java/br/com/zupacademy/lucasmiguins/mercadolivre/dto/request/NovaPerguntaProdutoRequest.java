package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;

public class NovaPerguntaProdutoRequest {
	
	@NotBlank
	private String titulo;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaPerguntaProdutoRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	
	public PerguntaProduto toModel(Produto produto, Usuario usuarioLogado) {
		return new PerguntaProduto(titulo, usuarioLogado, produto);
	}
}
