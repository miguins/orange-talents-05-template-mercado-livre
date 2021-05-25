package br.com.zupacademy.lucasmiguins.mercadolivre.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;

public class NovoUsuarioRequest {
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Length(min = 6)
	private String senha;

	public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	private String encodeSenha() {
		return new BCryptPasswordEncoder().encode(senha);
	}
	
	public Usuario toModel() {
		return new Usuario(login, encodeSenha());
	}
}
