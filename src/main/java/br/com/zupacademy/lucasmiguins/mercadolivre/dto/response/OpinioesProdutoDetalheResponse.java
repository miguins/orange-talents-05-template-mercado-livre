package br.com.zupacademy.lucasmiguins.mercadolivre.dto.response;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Opiniao;

public class OpinioesProdutoDetalheResponse {
	
	private String titulo;
	private String descricao;
	private Integer nota;

	public OpinioesProdutoDetalheResponse(Opiniao opiniao) {
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.nota = opiniao.getNota();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}
}
