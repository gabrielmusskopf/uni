package br.com.gabriel.poker.etapa;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.jogador.Jogador;

class OpcoesAposta {

	static String opcoesFormatadas (Jogo jogo, Jogador jogador, int valorAposta) {
		var opcoes = buscarOpcoes(jogo, jogador, valorAposta).entrySet().stream()
				.map(entry -> MessageFormat.format("{0} - {1}", entry.getKey(), entry.getValue()))
				.collect(Collectors.joining("\n"));

		return jogador.getNome() + " você pode:\n" + opcoes;
	}

	static Map<Integer, String> buscarOpcoes (Jogo jogo, Jogador jogador, int valorAposta) {
		final var opcoes = new HashMap<Integer, String>();

		opcoes.put(1, "Compeltar");
		opcoes.put(3, "Desistir da mão");

		if (jogador.getFichas() > valorAposta)
			opcoes.put(2, "Aumentar a aposta");


		var isBlindEValorApostaUm = jogo.getBigBlind().equals(jogador) && valorAposta == 1;

		if (isBlindEValorApostaUm) {
			opcoes.put(4, "Não fazer nada. (\"Check\")");
		}

		return opcoes.entrySet().stream()
				.sorted(Comparator.comparingInt(Map.Entry::getKey))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
