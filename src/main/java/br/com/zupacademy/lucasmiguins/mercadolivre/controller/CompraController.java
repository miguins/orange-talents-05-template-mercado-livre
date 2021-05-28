package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaCompraRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.dto.response.CompraResponse;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Compra;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Produto;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.shared.email.CentralEmails;

@RestController
@RequestMapping("/api/v1/compras")
public class CompraController {
	
	@Autowired EntityManager em;
	
	@Autowired CentralEmails centralEmails;
	
	@PostMapping("/produto/{id}")
	@Transactional
	public ResponseEntity<?> cadastro(@PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado,
						@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriBuilder) {
		
		Produto produto = em.find(Produto.class, id);
		
		if (produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (!produto.processaCompra(request.getQuantidade())) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		
		Compra compra = request.toModel(produto, usuarioLogado);
		em.persist(compra);
		
		this.centralEmails.enviarEmailCompra(compra);
		
		String urlRetorno = compra.getUrlRetorno(compra.getId(), uriBuilder);
		return ResponseEntity.status(HttpStatus.FOUND).body(new CompraResponse(urlRetorno));
	}
}
