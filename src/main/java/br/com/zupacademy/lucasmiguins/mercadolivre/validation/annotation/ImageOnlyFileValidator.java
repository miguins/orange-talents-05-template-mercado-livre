package br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.lucasmiguins.mercadolivre.dto.request.NovoImagemProdutoRequest;

public class ImageOnlyFileValidator implements ConstraintValidator<ImageOnlyFile, NovoImagemProdutoRequest> {


	@Override
	public void initialize(ImageOnlyFile params) {

	}

	@Override
	public boolean isValid(NovoImagemProdutoRequest values, ConstraintValidatorContext context) {
		
		for (MultipartFile file : values.getImagens()) {
			String fileExtension = getFileExtension(file.getOriginalFilename());
			
			if (!fileExtension.equals("png") && !fileExtension.equals("jpg") && !fileExtension.equals("jpeg")) {
				return false;
			}
		}
		
		return true;
	}
	
	private String getFileExtension(String fileName) {
	    int i = fileName.lastIndexOf('.');
	    String ext = i > 0 ? fileName.substring(i + 1) : "";
	    return ext;
	}
}
