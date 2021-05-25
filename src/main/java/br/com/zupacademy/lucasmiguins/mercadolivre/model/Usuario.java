package br.com.zupacademy.lucasmiguins.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Length(min = 6)
	private String senha;
	
	private LocalDateTime dataCriacao;

	public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
		this.dataCriacao = LocalDateTime.now();
	}
}
