package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaAtualizacaoRankingsRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovaNotaFiscalRequest;

@RestController
@RequestMapping("/api/v1/fakes")
public class ControllerFake {
	
	@PostMapping("/nf")
	public void gerarNf(@RequestBody @Valid NovaNotaFiscalRequest request) {
		System.out.println("Gerando nota fiscal para... " + request.toString());
	}
	
	@PostMapping("/rankings")
	public void atualizarRankings(@RequestBody @Valid NovaAtualizacaoRankingsRequest request) {
		System.out.println("Atualizando rankings para... " + request.toString());
	}
}
