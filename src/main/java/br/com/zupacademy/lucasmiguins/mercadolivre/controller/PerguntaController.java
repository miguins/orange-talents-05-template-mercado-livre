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

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaPerguntaProdutoRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.PerguntaProduto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.shared.email.CentralEmails;

@RestController
@RequestMapping("/api/v1/perguntas")
public class PerguntaController {
	
	@Autowired EntityManager em;
	
	@Autowired CentralEmails centralEmails;
	
	@PostMapping("/produto/{id}")
	@Transactional
	public ResponseEntity<?> cadastro(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado,
						@RequestBody @Valid NovaPerguntaProdutoRequest request) {
		
		Produto produto = em.find(Produto.class, id);
		
		if (produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		PerguntaProduto pergunta = request.toModel(produto, usuarioLogado);
		em.persist(pergunta);
		centralEmails.enviarEmailPergunta(pergunta);
		
		return ResponseEntity.ok().build();
	}

}
