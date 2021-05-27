package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovoImagemProdutoRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.shared.imagem.Uploader;

@RestController
@RequestMapping("/api/v1/imagens")
public class ImagemController {
	
	@Autowired Uploader uploader;
	
	@Autowired EntityManager em;
	
	@PostMapping("/produto/{id}")
	@Transactional
	public ResponseEntity<NovoImagemProdutoRequest> uploadImagemProduto(@PathVariable Long id, @Valid NovoImagemProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
		
		Produto produto = em.find(Produto.class, id);
		
		if (produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (!produto.pertenceAoUsuario(usuarioLogado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploader.envia(request.getImagens());
		produto.adicionaImagens(links);
		
		em.merge(produto);
		
		return ResponseEntity.ok().build();
	}
}
