package br.com.zupacademy.lucasmiguins.mercadolivre.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zupacademy.lucasmiguins.mercadolivre.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
