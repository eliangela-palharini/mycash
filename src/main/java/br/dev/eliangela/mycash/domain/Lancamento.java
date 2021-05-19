package br.dev.eliangela.mycash.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lancamento")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_lancamento")
	private Integer id;
	
	@Column(name = "ds_lancamento")
	@NotBlank(message = "Campo Descrição é obrigatório")
	@Size(max = 50)
	private String descricao;
	
	@Column(name = "vl_lancamento", precision = 8, scale = 2)
	@NotBlank(message = "Campo Valor é obrigatório.")
	@PositiveOrZero(message = "É permitido apenas valores acima de zero.")
	private Double valor;
	
	@Column(name = "dt_lancamento")
	@NotBlank(message = "Campo Data é obrigatório.")
	@PastOrPresent(message = "Não é permitido data futura.")
	private LocalDate data;
	
	@Column(name = "tp_lancamento")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo Tipo é obrigatório.")
	private LancamentoTipo tipo;
	
	@Column(name = "in_excluido")
	@NotBlank(message = "Campo Excluído é obrigatório.")
	private Boolean excluido = Boolean.FALSE;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LancamentoTipo getTipo() {
		return tipo;
	}

	public void setTipo(LancamentoTipo tipo) {
		this.tipo = tipo;
	}
	
	public void excluir() {
		this.excluido = true;
	}

}
