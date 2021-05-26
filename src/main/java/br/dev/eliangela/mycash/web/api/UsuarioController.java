package br.dev.eliangela.mycash.web.api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.eliangela.mycash.domain.Usuario;
import br.dev.eliangela.mycash.service.UsuarioService;
import br.dev.eliangela.mycash.web.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UsuarioDTO> todos() {
		List<Usuario> todos = service.todos();
		List<UsuarioDTO> usuariosDTO = todos.stream().map((usuario) -> mapper.map(usuario, UsuarioDTO.class)).collect(Collectors.toList());
		
		return usuariosDTO;
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UsuarioDTO criar(@RequestParam(required = true) String email, @RequestParam(required = true) String senha) {
		Usuario usuario = service.save(email, senha);
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		
		return usuarioDTO;
	}

//	@GetMapping("/{email}")
//	@PreAuthorize("#email == authentication.getName() or hasRole('ROLE_ADMIN')")
//	public Usuario getUsuario(Authentication authentication, @PathVariable("email") String email) {
//		return service.findByEmail(email);
//	}
	
	@GetMapping("/{email}")
	@PreAuthorize("#email == authentication.getName() or hasRole('ROLE_ADMIN')")
	public UsuarioDTO getUsuario(@PathVariable("email") String email) {
		Usuario usuario = service.findByEmail(email);
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		
		return usuarioDTO;
	}

	@PutMapping("/{email}")
	@PreAuthorize("#email == authentication.getName() or hasRole('ROLE_ADMIN')")
	public UsuarioDTO resetarSenha(@PathVariable("email") String email, @RequestParam(required = true) String senhaNova) {
		Usuario usuario = service.resetarSenha(email, senhaNova);
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		
		return usuarioDTO;
	}
}
