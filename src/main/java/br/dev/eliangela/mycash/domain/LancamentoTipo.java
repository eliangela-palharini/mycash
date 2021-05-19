package br.dev.eliangela.mycash.domain;

public enum LancamentoTipo {

	DESPESA {
		@Override
		public String toString() {
			return "Despesa";
		}
	}, RECEITA {
		@Override
		public String toString() {
			return "Receita";
		}
	};
	
	public abstract String toString();
	
}
