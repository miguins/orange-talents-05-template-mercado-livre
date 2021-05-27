package br.com.zupacademy.lucasmiguins.mercadolivre.shared.email;

public interface EnviadorEmail {
	
	void enviar(String remetente, String destinatario, String titulo, String corpo);
}
