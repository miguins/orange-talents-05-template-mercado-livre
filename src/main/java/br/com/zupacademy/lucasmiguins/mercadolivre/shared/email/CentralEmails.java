package br.com.zupacademy.lucasmiguins.mercadolivre.shared.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.PerguntaProduto;

@Component
public class CentralEmails {
	
	@Autowired EnviadorEmail enviador;
	
	public void enviarEmailPergunta(PerguntaProduto pergunta) {
		this.enviador.enviar(pergunta.getEmailUsuarioPergunta(), pergunta.getEmailDonoProduto(), pergunta.getNomeProduto(), pergunta.getTitulo());
	}
}
