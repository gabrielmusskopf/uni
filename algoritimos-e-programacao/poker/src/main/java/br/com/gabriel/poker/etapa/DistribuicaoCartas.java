package br.com.gabriel.poker.etapa;

import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.comunicacao.Comunicador;

public class DistribuicaoCartas implements Etapa {

	private Comunicador comunicador;
	private boolean isCompleta;

	public DistribuicaoCartas(Comunicador comunicador) {
		this.comunicador = comunicador;
	}

	@Override
	public boolean isCompelta() {
		return isCompleta;
	}

	@Override
	public void executar(Jogo jogo) {
		jogo.getJogadoresRestantes().forEach(jogador -> {
			jogador.setCartas(jogo.comprarCartasIniciais());
			comunicador.comunicar("Cartas distríbuidas para o jogador " + jogador.getNome());
		});

		isCompleta = Boolean.TRUE;
	}

	@Override
	public String getNome() {
		return "Distribuição de cartas";
	}

}
