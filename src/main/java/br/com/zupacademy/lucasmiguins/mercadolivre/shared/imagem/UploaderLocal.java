package br.com.zupacademy.lucasmiguins.mercadolivre.shared.imagem;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderLocal implements Uploader {

	public Set<String> envia(List<MultipartFile> imagens) {

		return imagens.stream().map(
				i -> "http://linklocal/" 
				+ UUID.randomUUID().toString()
				+ "-"
				+ i.getOriginalFilename()
				
				).collect(Collectors.toSet());
	}

}
