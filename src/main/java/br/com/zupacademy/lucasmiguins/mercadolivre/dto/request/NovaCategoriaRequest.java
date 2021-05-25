package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Categoria;
import br.com.zupacademy.lucasmiguins.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.ExistsId;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.UniqueValue;

public class NovaCategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	public NovaCategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public Categoria toModel(CategoriaRepository categoriaReposity) {
		
		Categoria categoria = new Categoria(this.nome);
		
		if (this.idCategoriaMae != null) {
			Categoria categoriaMae = categoriaReposity.findById(this.idCategoriaMae).orElse(null);
			categoria.setCategoriaMae(categoriaMae);
		}
		
		return categoria;
	}
}
