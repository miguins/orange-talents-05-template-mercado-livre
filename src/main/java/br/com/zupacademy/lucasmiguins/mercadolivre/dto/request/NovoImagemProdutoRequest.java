package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.lucasmiguins.mercadolivre.validation.annotation.ImageOnlyFile;


@ImageOnlyFile
public class NovoImagemProdutoRequest {
	
	@NotNull
	@Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
}
