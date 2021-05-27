package br.com.zupacademy.lucasmiguins.mercadolivre.dto.response;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.PerguntaProduto;

public class PerguntasProdutoDetalheResponse {

	private String titulo;
	
	public PerguntasProdutoDetalheResponse(PerguntaProduto pergunta) {
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
}
