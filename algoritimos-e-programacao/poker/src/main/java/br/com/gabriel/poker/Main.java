package br.com.gabriel.poker;

import br.com.gabriel.poker.comunicacao.ComunicacaoLog;

public class Main {

	public static void main (String[] args) {

		var controle = new Controle(configuracoes -> {
			configuracoes.setSegundosDelayEntreRodadas(1);
			configuracoes.setSegundosDelayAposRodada(5);
			configuracoes.setQuantidadeDeJogadores(5);
		}, ComunicacaoLog::new);


		final var nomes = controle.buscarNomesJogadores();
		controle.iniciarJogo(nomes);

		while (!controle.getEtapaAtual().isCompelta() && controle.isAcontecendo()) {
			controle.executarEtapa();
		}

	}

}