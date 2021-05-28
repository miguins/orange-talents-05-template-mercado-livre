package br.com.zupacademy.lucasmiguins.mercadolivre.model.enums;

import org.springframework.web.util.UriComponentsBuilder;

public enum EnumGatewayPagamento {
	
	PAYPAL {
		@Override
		public String obterUrlGateway(String id, UriComponentsBuilder uri) {
			
			String urlRetorno = uri.path("/compra/{id}").buildAndExpand(id).toString();
			
			return "paypal.com?buyerId=" + id + "&redirectUrl=" + urlRetorno;
		}
	},
	
	PAGSEGURO {
		@Override
		public String obterUrlGateway(String id, UriComponentsBuilder uri) {
			
			String urlRetorno = uri.path("/compra/{id}").buildAndExpand(id).toString();
			
			return "pagseguro.com?returnId=" + id + "&redirectUrl=" + urlRetorno;
		}
	};

	public abstract String obterUrlGateway(String id, UriComponentsBuilder uri);
}