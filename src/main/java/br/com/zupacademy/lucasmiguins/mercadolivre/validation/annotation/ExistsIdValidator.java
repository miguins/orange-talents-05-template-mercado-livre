package br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void initialize(ExistsId params) {
		this.domainAttribute = params.fieldName();
		this.klass = params.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return true;
		}
		
		Query query = entityManager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + " = :value");
		query.setParameter("value", value);
		
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Existe mais de um " + klass.getName() + " com o atributo " + domainAttribute +" com o valor = " + value);
		
		return !list.isEmpty();
	}

}
