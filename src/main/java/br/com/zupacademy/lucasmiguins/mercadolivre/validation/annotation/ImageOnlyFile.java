package br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = {ImageOnlyFileValidator.class})
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface ImageOnlyFile {
	
	String message() default "Um arquivo não possui um formato de imagem suportado.";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
}
