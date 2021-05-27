package br.com.zupacademy.lucasmiguins.mercadolivre.shared.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviadorEmailFake implements EnviadorEmail {

	@Override
	public void enviar(String remetente, String destinatario, String titulo, String corpo) {

		StringBuilder email = new StringBuilder();
		
		email.append(" Interessado: " + remetente + "\n");
		email.append(" Destinatário: " + destinatario + "\n");
		email.append(" Título: " + "Você recebeu uma pergunta do produto: " + titulo + "\n");
		email.append(" Pergunta: " + corpo);
		
		System.out.println(email);
	}
}
