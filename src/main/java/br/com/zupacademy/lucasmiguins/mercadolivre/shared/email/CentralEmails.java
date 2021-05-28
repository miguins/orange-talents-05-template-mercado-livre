package br.com.zupacademy.lucasmiguins.mercadolivre.shared.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Compra;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.PerguntaProduto;

@Component
public class CentralEmails {
	
	@Autowired EnviadorEmail enviador;
	
	public void enviarEmailPergunta(PerguntaProduto pergunta) {
		this.enviador.enviar("email@email.com", pergunta.getEmailDonoProduto(), "PERGUNTA SOBRE PRODUTO", constroiCorpoPergunta(pergunta));
	}
	
	public void enviarEmailCompra(Compra compra) {
		this.enviador.enviar("email@email.com", compra.getEmailDonoProduto(), "COMPRA DE PRODUTO", constroiCorpoEmailCompra(compra));
	}
	
	private String constroiCorpoEmailCompra(Compra compra) {
		
		StringBuilder corpo = new StringBuilder();
		
		corpo.append("<html>" + "\n");
		corpo.append("O produto " + compra.getNomeProduto() + "\n");
		corpo.append("foi comprado por "+ compra.getNomeUsuarioCompra() + "\n");
		corpo.append("na quantidade " + compra.getQuantidade() + "\n");
		corpo.append("resultando em um total de R$" + compra.getValorTotalCompra(compra.getValorProduto()).toString() + "\n");
		corpo.append("</html>");
		
		return corpo.toString();
	}
	
	private String constroiCorpoPergunta(PerguntaProduto pergunta) {
		
		StringBuilder corpo = new StringBuilder();
		
		corpo.append("<html>" + "\n");
		corpo.append("O produto " + pergunta.getNomeProduto() + "\n");
		corpo.append("recebeu uma pergunta de "+ pergunta.getEmailUsuarioPergunta() + "\n");
		corpo.append("Pergunta: " + pergunta.getTitulo() + "\n");
		corpo.append("</html>");
		
		return corpo.toString();
	}
}
