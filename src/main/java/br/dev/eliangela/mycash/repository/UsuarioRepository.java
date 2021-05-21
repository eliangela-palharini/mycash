package br.dev.eliangela.mycash.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.eliangela.mycash.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {

	Optional<Usuario> findByEmail(String email);
	
}
