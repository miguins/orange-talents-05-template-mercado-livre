package br.com.zupacademy.lucasmiguins.mercadolivre.dto.response;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.CaracteristicaProduto;

public class CaracteristicasProdutoDetalheResponse {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	public CaracteristicasProdutoDetalheResponse(CaracteristicaProduto caracteristica) {
		
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
