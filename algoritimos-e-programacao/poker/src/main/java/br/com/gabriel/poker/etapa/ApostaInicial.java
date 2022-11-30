package br.com.gabriel.poker.etapa;

import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.comunicacao.Comunicador;

public class ApostaInicial implements Etapa {

	private static final int APOSTA_BIG_BLIND = 10;
	private final Comunicador comunicador;
	private boolean isCompleta;

	public ApostaInicial (Comunicador comunicador) {
		this.comunicador = comunicador;
	}

	@Override
	public boolean isCompelta () {
		return isCompleta;
	}

	@Override
	public void executar (Jogo jogo) {
		if (!jogo.isDeveExecutarRodadas()) return;

		var bigBlind = jogo.getBigBlind();
		var smallBlind = jogo.getSmallBlind();

		var apostaBB = jogo.apostar(bigBlind, APOSTA_BIG_BLIND);
		var apostaSB = jogo.apostar(smallBlind, APOSTA_BIG_BLIND / 2);
		comunicador.comunicar("Apostas iniciais feitas. Big Blind apostou {0} e o Small Blind {1}.\nO pote está com {2} fichas.", apostaBB, apostaSB, jogo.getPote());

		jogo.adicionarJogadorNaRodada(bigBlind, apostaBB);
		jogo.adicionarJogadorNaRodada(smallBlind, apostaSB);

		isCompleta = Boolean.TRUE;
	}

	@Override
	public String getNome () {
		return "Apostas iniciais";
	}

}
