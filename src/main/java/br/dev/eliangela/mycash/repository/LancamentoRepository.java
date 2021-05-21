package br.dev.eliangela.mycash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.dev.eliangela.mycash.domain.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

	List<Lancamento> findByExcluido(Boolean excluido);
	
}
