package br.dev.eliangela.mycash.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.dev.eliangela.mycash.domain.Lancamento;
import br.dev.eliangela.mycash.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repo;

//	public Page<Lancamento> todos(Pageable pageable) {
//		return repo.findAll(pageable);
//	}

	public Page<Lancamento> todos(Pageable pageable) {
		return repo.findByExcluido(pageable, false);
	}
	
//	public List<Lancamento> todos() {
//		return repo.findByExcluido(false);
//	}

	public Lancamento getLancamento(Integer id) {
		return repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Lancamento criar(Lancamento lancamento) {
		return repo.save(lancamento);
	}

	public Lancamento atualizar(Integer id, Lancamento novoLancamento) {

		return repo.findById(id).map(lancamento -> {
			lancamento.setDescricao(novoLancamento.getDescricao());
			lancamento.setData(novoLancamento.getData());
			lancamento.setTipo(novoLancamento.getTipo());
			lancamento.setValor(novoLancamento.getValor());

			return repo.save(lancamento);
		}).orElseThrow(() -> new EntityNotFoundException());
	}

	public void excluir(Integer id) {
		repo.findById(id).map(lancamento -> {
			lancamento.excluir();

			return repo.save(lancamento);
		}).orElseThrow(() -> new EntityNotFoundException());
	}

}
