package br.com.zupacademy.lucasmiguins.mercadolivre.shared.imagem;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderS3 implements Uploader {

	public Set<String> envia(List<MultipartFile> imagens) {

		return imagens.stream().map(
				i -> "http://link-amazon-etc/" 
				+ UUID.randomUUID().toString()
				+ "-"
				+ i.getOriginalFilename()
				
				).collect(Collectors.toSet());
	}

}
