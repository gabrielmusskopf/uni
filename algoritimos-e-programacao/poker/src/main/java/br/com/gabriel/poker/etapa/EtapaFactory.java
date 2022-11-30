package br.com.gabriel.poker.etapa;

import java.util.List;

import br.com.gabriel.poker.Configuracoes;
import br.com.gabriel.poker.comunicacao.Comunicador;

public class EtapaFactory {

	private static final Comunicador comunicador = Configuracoes.COMUNICADOR;

	public static List<Etapa> contruirEtapas () {
		return List.of(
				distribuicaoDeCartas(),
				apostaInicial(),
				trocaDeCartas(),
				apostas(),
				correcaoAposta(),
				resultados()
		);
	}

	public static Etapa distribuicaoDeCartas () {
		return new DistribuicaoCartas(comunicador);
	}

	public static Etapa trocaDeCartas () {
		return new TrocaDeCartas(comunicador);
	}

	public static Etapa apostas () {
		return new Apostas(comunicador);

	}

	public static Etapa apostaInicial () {
		return new ApostaInicial(comunicador);
	}

	public static Etapa correcaoAposta () {
		return new CorrecaoApostas(comunicador);
	}

	public static Etapa resultados () {
		return new Resultados(comunicador);
	}

}
