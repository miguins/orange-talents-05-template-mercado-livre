package br.com.zupacademy.lucasmiguins.mercadolivre.shared.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Compra;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.PerguntaProduto;

@Component
public class CentralEmails {
	
	@Autowired EnviadorEmail enviador;
	
	public void enviarEmailPergunta(PerguntaProduto pergunta) {
		
		StringBuilder corpo = new StringBuilder();
		
		corpo.append("<html>" + "\n");
		corpo.append("O produto " + pergunta.getNomeProduto() + "\n");
		corpo.append("recebeu uma pergunta de "+ pergunta.getEmailUsuarioPergunta() + "\n");
		corpo.append("Pergunta: " + pergunta.getTitulo() + "\n");
		corpo.append("</html>");
		
		
		this.enviador.enviar("email@email.com", pergunta.getEmailDonoProduto(), "PERGUNTA SOBRE PRODUTO", corpo.toString());
	}
	
	public void enviarEmailCompra(Compra compra) {
		
		StringBuilder corpo = new StringBuilder();
		
		corpo.append("<html>" + "\n");
		corpo.append("O produto " + compra.getNomeProduto() + "\n");
		corpo.append("foi comprado por "+ compra.getNomeUsuarioCompra() + "\n");
		corpo.append("na quantidade " + compra.getQuantidade() + "\n");
		corpo.append("resultando em um total de R$" + compra.getValorTotalCompra(compra.getValorProduto()).toString() + "\n");
		corpo.append("</html>");
		
		this.enviador.enviar("email@email.com", compra.getEmailDonoProduto(), "COMPRA DE PRODUTO", corpo.toString());
	}
	

	public void enviarEmailTransacaoSucesso(Compra compra) {
		
		StringBuilder corpo = new StringBuilder();
		
		corpo.append("<html>" + "\n");
		corpo.append("O produto " + compra.getNomeProduto() + "\n");
		corpo.append("foi comprado com SUCESSO" + "\n");
		corpo.append("na quantidade " + compra.getQuantidade() + "\n");
		corpo.append("resultando em um total de R$" + compra.getValorTotalCompra(compra.getValorProduto()).toString() + "\n");
		corpo.append("</html>");
		
		
		this.enviador.enviar("email@email.com", compra.getNomeUsuarioCompra(), "COMPRA REALIZADA COM SUCESSO", corpo.toString());
	}
	
	public void enviarEmailTransacaoErro(Compra compra, UriComponentsBuilder uricb) {
		
		StringBuilder corpo = new StringBuilder();
		
		corpo.append("<html>" + "\n");
		corpo.append("O ocorreu uma falha na compra do produto " + compra.getNomeProduto() + "\n");
		corpo.append("por favor, tente novamente" + "\n");
		corpo.append("link de acesso " + compra.getUrlRetorno(compra.getId(), uricb) + "\n");
		corpo.append("</html>");
		
		
		this.enviador.enviar("email@email.com", compra.getNomeUsuarioCompra(), "COMPRA REALIZADA COM SUCESSO", corpo.toString());
	}
}
