package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Categoria;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.ExistsId;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.UniqueValue;

public class NovoProdutoRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	
	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaProdutoRequest> caracteristicas = new ArrayList<>();

	public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Min(0) Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Long idCategoria, @Size(min = 3) @Valid List<NovaCaracteristicaProdutoRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas = caracteristicas;
	}
	
	public List<NovaCaracteristicaProdutoRequest> getCaracteristicas() {
		return caracteristicas;
	}
	
	public boolean temCaracteristicasNomesIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		
		for (NovaCaracteristicaProdutoRequest caracteristica : this.caracteristicas) {
			if (!nomesIguais.add(caracteristica.getNome())) {
				return true;
			}
		}
		
		return false;
	}

	
	public Produto toModel(EntityManager em, Usuario usuarioLogado) {
		Categoria categoria = em.find(Categoria.class, idCategoria);
	
		return new Produto(nome, valor, quantidade, descricao, categoria, caracteristicas, usuarioLogado);
	}
}
