package br.com.zupacademy.lucasmiguins.mercadolivre.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.response.ValidationErrorResponse;


@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public List<ValidationErrorResponse> handleValidationError(MethodArgumentNotValidException exception) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		
		return buildValidationErrors(fieldErrors, globalErrors);
	}
	
	private List<ValidationErrorResponse> buildValidationErrors(List<FieldError> fieldErrors, List<ObjectError> globalErrors) {
		
		List<ValidationErrorResponse> dto = new ArrayList<>();
		
		globalErrors.forEach(e -> {
			ValidationErrorResponse erro = new ValidationErrorResponse(e.getObjectName(), e.getDefaultMessage());
			dto.add(erro);
		});
		
		fieldErrors.forEach(e -> {
			String mensagem = getErrorMessage(e);
			ValidationErrorResponse erro = new ValidationErrorResponse(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}
