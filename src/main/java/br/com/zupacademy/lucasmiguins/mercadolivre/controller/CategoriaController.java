package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaCategoriaRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Categoria;
import br.com.zupacademy.lucasmiguins.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

	@Autowired CategoriaRepository categoriaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovaCategoriaRequest> cadastro(@RequestBody @Valid NovaCategoriaRequest request) {
		
		Categoria categoria = request.toModel(categoriaRepository);
		categoriaRepository.save(categoria);
		
		return ResponseEntity.ok().build();
	}
}
