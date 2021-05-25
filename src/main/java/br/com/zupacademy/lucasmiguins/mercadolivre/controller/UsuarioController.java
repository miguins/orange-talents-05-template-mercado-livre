package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovoUsuarioRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;
import br.com.zupacademy.lucasmiguins.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
	
	@Autowired UsuarioRepository usuarioRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovoUsuarioRequest> cadastro(@RequestBody @Valid NovoUsuarioRequest request) {
		
		Usuario usuario = request.toModel();
		usuarioRepository.save(usuario);
		
		return ResponseEntity.ok().build();
	}
}
