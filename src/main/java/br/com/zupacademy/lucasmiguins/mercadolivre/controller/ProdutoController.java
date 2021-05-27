package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovoProdutoRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.dto.response.ProdutoDetalheResponse;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.validation.ProibeCaracteristicaProdutoComNomeIgualValidator;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
	
	@Autowired EntityManager em;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaProdutoComNomeIgualValidator());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovoProdutoRequest> cadastro(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
		
		Produto produto = request.toModel(em, usuarioLogado);
		 em.persist(produto);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetalheResponse> detalhe(@PathVariable Long id) {

		Produto produto = em.find(Produto.class, id);
		
		if (produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(new ProdutoDetalheResponse(produto));
	}
}
