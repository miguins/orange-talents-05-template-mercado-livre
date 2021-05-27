package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaOpiniaoProdutoRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Opiniao;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/api/v1/opnioes")
public class OpiniaoController {

	@Autowired EntityManager em;
	
	@PostMapping("/produto/{id}")
	@Transactional
	public ResponseEntity<?> cadastro(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado,
						@RequestBody @Valid NovaOpiniaoProdutoRequest request) {
		
		Produto produto = em.find(Produto.class, id);
		
		if (produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		Opiniao opiniao = request.toModel(produto, usuarioLogado);
		em.persist(opiniao);
		
		return ResponseEntity.ok().build();
	}
}
