package br.dev.eliangela.mycash.web.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.eliangela.mycash.domain.Lancamento;
import br.dev.eliangela.mycash.service.LancamentoService;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoService service;

	@GetMapping
	public Page<Lancamento> todos(Pageable pageable) {
		return service.todos(pageable);
	}

	@GetMapping("/{id}")
	public Lancamento getLancamento(@PathVariable("id") Integer id) {
		return service.getLancamento(id);
	}

	@PostMapping
	public Lancamento criar(@Valid @RequestBody Lancamento lancamento) {
		return service.criar(lancamento);
	}

	@PutMapping("/{id}")
	public Lancamento atualizar(@PathVariable Integer id, @RequestBody Lancamento novoLancamento) {
		return service.atualizar(id, novoLancamento);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		service.excluir(id);
	}

}
