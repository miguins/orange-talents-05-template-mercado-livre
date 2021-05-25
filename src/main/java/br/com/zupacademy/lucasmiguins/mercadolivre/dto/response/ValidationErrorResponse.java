package br.com.zupacademy.lucasmiguins.mercadolivre.dto.response;

public class ValidationErrorResponse {
	public String campo;
	public String erro;
	
	public ValidationErrorResponse(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
}
