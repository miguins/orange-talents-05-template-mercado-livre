package br.com.zupacademy.lucasmiguins.mercadolivre.shared.connection;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CentralIntegracao implements FakeConnection {
	
	public static String API_URL = "http://localhost:8080/api/v1";

	@Override
	public void gerarNotaFiscal(Long idCompra, Long idUsuario) {
		RestTemplate rest = new RestTemplate();
        Map<String, String> request = Map.of("idCompra", idCompra.toString(),"idUsuario", idUsuario.toString());

        rest.postForEntity(API_URL+"/fakes/nf", request, String.class);
	}

	@Override
	public void atualizarRankings(Long idCompra, Long idDonoProduto) {
		RestTemplate rest = new RestTemplate();
        Map<String, String> request = Map.of("idCompra", idCompra.toString(),"idDonoProduto", idDonoProduto.toString());

        rest.postForEntity(API_URL+"/fakes/rankings", request, String.class);
	}
}
