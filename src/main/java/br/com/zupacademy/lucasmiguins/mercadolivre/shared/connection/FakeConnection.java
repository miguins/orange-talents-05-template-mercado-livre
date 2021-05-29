package br.com.zupacademy.lucasmiguins.mercadolivre.shared.connection;

public interface FakeConnection {
	
	void gerarNotaFiscal(Long idCompra, Long idUsuario);
	
	void atualizarRankings(Long idCompra, Long idDonoProduto);
}
