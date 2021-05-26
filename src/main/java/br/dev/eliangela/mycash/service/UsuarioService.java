package br.dev.eliangela.mycash.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.dev.eliangela.mycash.domain.Usuario;
import br.dev.eliangela.mycash.domain.UsuarioRole;
import br.dev.eliangela.mycash.exception.UsuarioException;
import br.dev.eliangela.mycash.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Usuario> todos() {
		return repo.findAll();
	}
	
	public void registraUsuarioAdmin(String email, String senha) {
		if (repo.findByEmail(email).isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(passwordEncoder.encode(senha));
			usuario.setRole(UsuarioRole.ROLE_ADMIN);
			
			repo.save(usuario);
		}
	}

	public Usuario save(String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(passwordEncoder.encode(senha));
		usuario.setRole(UsuarioRole.ROLE_USER);
		
		if (repo.findByEmail(email).isPresent()) {
			throw new UsuarioException("Já existe usuário com o e-mail " + email + ".");
		}
		
		return repo.save(usuario);
	}

	public Usuario findByEmail(String email) {
		return repo.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
	}

	public Usuario resetarSenha(String email, String senhaNova) {
		Usuario usuario = findByEmail(email);
		usuario.setSenha(passwordEncoder.encode(senhaNova));
		
		return repo.save(usuario);
	}
}
