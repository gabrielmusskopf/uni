package br.com.gabrielgmusskopf.cinema;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.excecao.OpcaoInexistenteExcecao;
import br.com.gabrielgmusskopf.cinema.opcao.Opcao;
import br.com.gabrielgmusskopf.cinema.util.IntegerUtil;

public class Menu {

	private final List<Opcao> opcoes;

	public Menu(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	public void exibir(){
		Contexto.getUI().outln("\nEscolha uma opção:");
		opcoes.stream()
				.sorted(Comparator.comparing(Opcao::getNumero))
				.forEach(opcao -> Contexto.getUI().info("%d - %s".formatted(opcao.getNumero(), opcao.getFrase())));

		try {
			buscarOpcao(IntegerUtil.buscar())
					.orElseThrow(OpcaoInexistenteExcecao::new)
					.executar();

		} catch (NegocioExcecao ne){
			Contexto.getUI().err(ne.getMensagem());
		}
	}

	private Optional<Opcao> buscarOpcao(int numero){
		return opcoes.stream()
				.filter(o -> o.getNumero() == numero)
				.findFirst();
	}

}
