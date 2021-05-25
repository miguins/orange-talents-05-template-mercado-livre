package br.com.zupacademy.lucasmiguins.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
}
