package br.com.zupacademy.lucasmiguins.mercadolivre.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovoProdutoRequest;

public class ProibeCaracteristicaProdutoComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovoProdutoRequest request = (NovoProdutoRequest) target;
		if (request.temCaracteristicasNomesIguais()) {
			errors.rejectValue("caracteristicas", null, "As características não podem ter nomes iguais");
		}
		
	}

}
