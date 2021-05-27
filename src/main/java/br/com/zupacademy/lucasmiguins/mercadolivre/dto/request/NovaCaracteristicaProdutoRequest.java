package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.CaracteristicaProduto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.UniqueValue;

public class NovaCaracteristicaProdutoRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public NovaCaracteristicaProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public CaracteristicaProduto toModel(Produto produto) {
		return new CaracteristicaProduto(this.nome, this.descricao, produto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		NovaCaracteristicaProdutoRequest other = (NovaCaracteristicaProdutoRequest) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
