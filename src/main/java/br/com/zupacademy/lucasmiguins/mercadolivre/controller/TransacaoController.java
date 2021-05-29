package br.com.zupacademy.lucasmiguins.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.PagSeguroRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.PayPalRequest;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.Compra;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumGatewayPagamento;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumStatus;
import br.com.zupacademy.lucasmiguins.mercadolivre.model.enums.EnumStatusCompra;
import br.com.zupacademy.lucasmiguins.mercadolivre.shared.connection.CentralIntegracao;
import br.com.zupacademy.lucasmiguins.mercadolivre.shared.email.CentralEmails;

@RestController
@RequestMapping("/api/v1/transacoes")
public class TransacaoController {
	
	@Autowired EntityManager em;
	
	@Autowired CentralIntegracao centralIntegracao;
	
	@Autowired CentralEmails centralEmails;
	
	@PostMapping("/compra-paypal/{id}")
	@Transactional
	public ResponseEntity<?> paypal(@PathVariable Long id, @RequestBody @Valid PayPalRequest request, UriComponentsBuilder uricb) throws BindException {
		
		Compra compra = em.find(Compra.class, id);
		
		if (compra == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (!compra.getGateway().equals(EnumGatewayPagamento.PAYPAL)) {
			BindException compraFinalizada = new BindException(request, "PayPalRequest");
            compraFinalizada.reject("Não foi possível processar o pagamento");
            throw compraFinalizada;
		}
		
		if (compra.getStatus().equals(EnumStatusCompra.FINALIZADA)) {
			BindException compraFinalizada = new BindException(request, "PayPalRequest");
            compraFinalizada.reject("A Compra ja se encontra com o status de FINALIZADA");
            this.centralEmails.enviarEmailTransacaoErro(compra, uricb);
            throw compraFinalizada;
		}
		
		compra.adicionarTransacao(request);
		em.merge(compra);
		
		if (request.getStatus().equals(EnumStatus.SUCESSO)) {
			this.centralIntegracao.gerarNotaFiscal(compra.getId(), compra.getIdUsuario());
			this.centralIntegracao.atualizarRankings(compra.getId(), compra.getIdDonoProduto());
			this.centralEmails.enviarEmailTransacaoSucesso(compra);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/compra-pagseguro/{id}")
	@Transactional
	public ResponseEntity<?> pagseguro(@PathVariable Long id, @RequestBody @Valid PagSeguroRequest request, UriComponentsBuilder uricb) throws BindException {
		
		Compra compra = em.find(Compra.class, id);
		
		if (compra == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (!compra.getGateway().equals(EnumGatewayPagamento.PAGSEGURO)) {
			BindException compraFinalizada = new BindException(request, "PayPalRequest");
            compraFinalizada.reject("Não foi possível processar o pagamento");
            throw compraFinalizada;
		}
		
		if (compra.getStatus().equals(EnumStatusCompra.FINALIZADA)) {
			BindException compraFinalizada = new BindException(request, "PagSeguroRequest");
            compraFinalizada.reject("A Compra ja se encontra com o status de FINALIZADA");
            this.centralEmails.enviarEmailTransacaoErro(compra, uricb);
            throw compraFinalizada;
		}
		
		compra.adicionarTransacao(request);
		em.merge(compra);
		
		if (request.getStatus().equals(EnumStatus.SUCESSO)) {
			this.centralIntegracao.gerarNotaFiscal(compra.getId(), compra.getIdUsuario());
			this.centralIntegracao.atualizarRankings(compra.getId(), compra.getIdDonoProduto());
			this.centralEmails.enviarEmailTransacaoSucesso(compra);
		}
		
		return ResponseEntity.ok().build();
	}
}
