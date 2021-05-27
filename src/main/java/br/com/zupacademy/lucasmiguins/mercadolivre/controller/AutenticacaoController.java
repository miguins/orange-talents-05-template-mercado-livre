package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.mercadolivre.configuration.security.GerenciaToken;
import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.AutenticacaoRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.dto.response.TokenResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {
	
	@Autowired AuthenticationManager authManager;
	
	@Autowired GerenciaToken tokenService;

	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid AutenticacaoRequest request) {
		
		UsernamePasswordAuthenticationToken dadosLogin = request.converter();
		
		try {
			
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
			
	}

}
