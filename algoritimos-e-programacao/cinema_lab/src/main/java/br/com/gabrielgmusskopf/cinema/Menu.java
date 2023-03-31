package br.com.gabrielgmusskopf.cinema;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.excecao.OpcaoInexistenteExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;
import br.com.gabrielgmusskopf.cinema.opcao.Opcao;

class Menu {

	private Interacao interacao;
	private List<Opcao> opcoes;
	private boolean deveExibir = true;

	public Menu(Interacao interacao, List<Opcao> opcoes) {
		this.interacao = interacao;
		this.opcoes = opcoes;
	}

	public boolean deveExibir() {
		return deveExibir;
	}

	public void exibir(){
		interacao.outln("\nEscolha uma opção:");
		opcoes.stream()
				.sorted(Comparator.comparing(Opcao::getNumero))
				.forEach(opcao -> interacao.info("%d - %s".formatted(opcao.getNumero(), opcao.getFrase())));

		try {
			Integer n = null;
			while (n == null){
				n = interacao.inInt();
			}

			buscarOpcao(n)
					.orElseThrow(OpcaoInexistenteExcecao::new)
					.executar();

		} catch (NegocioExcecao ne){
			interacao.err(ne.getMensagem());
		}
	}

	private Optional<Opcao> buscarOpcao(int numero){
		return opcoes.stream()
				.filter(o -> o.getNumero() == numero)
				.findFirst();
	}

}
