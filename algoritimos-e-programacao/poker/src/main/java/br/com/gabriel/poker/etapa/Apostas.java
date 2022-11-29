package br.com.gabriel.poker.etapa;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Stream;

import br.com.gabriel.poker.Controle;
import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.jogador.Jogador;
import br.com.gabriel.poker.util.Aleatorio;

public class Apostas implements Etapa {

	private final Comunicador comunicador;
	private boolean isCompleta;

	public Apostas (Comunicador comunicador) {
		this.comunicador = comunicador;
	}

	@Override
	public boolean isCompelta () {
		return isCompleta;
	}

	@Override
	public void executar (Jogo jogo) {

		final var scanner = new Scanner(System.in);

		final var primeirosJogadores = jogo.getJogadoresRestantes().subList(Jogo.POSICAO_SMALL_BLIND + 1, jogo.getJogadoresRestantes().size());
		final var ultimosJogadores = jogo.getJogadoresRestantes().subList(Jogo.POSICAO_DEALER, Jogo.POSICAO_BIG_BLIND + 1);
		final var ordemJogadores = Stream.of(primeirosJogadores, ultimosJogadores)
				.flatMap(Collection::stream)
				.toList();

		final var smallBlind = jogo.getSmallBlind();

		int valorAposta;
		if (Controle.isPlayer(smallBlind)) {
			comunicador.comunicar("{0}, qual o valor do jogo?", smallBlind.getNome());
			comunicador.comunicar(Cor.AZUL, "Fichas: {0}", smallBlind.getFichas());
			valorAposta = scanner.nextInt();
		} else {
			valorAposta = Aleatorio.buscarEntre(1, smallBlind.getFichas());
		}

		jogo.apostar(smallBlind, valorAposta);
		comunicador.comunicar("O small blind, " + smallBlind.getNome() + ", apostou " + valorAposta + (valorAposta == 1 ? " ficha." : " fichas."));

		for (final Jogador jogador : ordemJogadores) {
			var isBlindEValorApostaUm = jogo.getBigBlind().equals(jogador) && valorAposta == 1;
			var limite = isBlindEValorApostaUm ? 4 : 3;

			var escolha = 0;
			if (Controle.isPlayer(jogador)) {
				comunicador.comunicar(Cor.AZUL, "\n" + jogador.informacoes());
				comunicador.comunicar(Cor.AZUL, jogador.visualizarCartas());
				comunicador.comunicar(Cor.AZUL, "\nAposta atual: " + valorAposta + "\n");
				comunicador.comunicar(OpcoesAposta.opcoesFormatadas(jogo, jogador, valorAposta));

				while (escolha <= 0 || escolha > limite)
					escolha = scanner.nextInt();

			} else {
				escolha = Aleatorio.buscarEntre(1, limite);
			}

			switch (escolha) {
				case 1 -> cobrirValor(jogo, valorAposta, jogador);
				case 2 -> valorAposta = aumentarValor(jogo, scanner, valorAposta, jogador);
				case 3 -> comunicador.comunicar("O jogador " + jogador.getNome() + " desistiu da mão nesta rodada.");
				case 4 -> comunicador.comunicar("O big blind não tomou nenhuma ação.");
			}
		}

		jogo.setApostaDaRodada(valorAposta);

		isCompleta = Boolean.TRUE;
	}

	private void cobrirValor (Jogo jogo, int valorAposta, Jogador jogador) {
		var apostado = jogo.apostar(jogador, valorAposta);

		if (apostado != 0)
			comunicador.comunicar("O jogador " + jogador.getNome() + " cobriu a aposta de " + valorAposta + " com " + apostado + (valorAposta == 1 ? " ficha." : " fichas."));
	}

	private int aumentarValor (Jogo jogo, Scanner scanner, int valorAposta, Jogador jogador) {
		if (jogador.getFichas() <= valorAposta) {
			var fichasApostadas = jogo.apostar(jogador, jogador.getFichas());

			if (fichasApostadas != 0) {
				comunicador.comunicar("O jogador " + jogador.getNome() + " não tem fichas o suficientes para aumentar a aposta, então todas as " + fichasApostadas + " fichas foram apostadas.");
				return fichasApostadas;

			} else return valorAposta;
		}

		if (Controle.isPlayer(jogador))
			comunicador.comunicar("Qual valor deseja apostar?");

		var novaAposta = 0;
		while (novaAposta < valorAposta || novaAposta >= jogador.getFichas()) {
			novaAposta = Controle.isPlayer(jogador) ? scanner.nextInt() : Aleatorio.buscarEntre(1, jogador.getFichas());

			if ((novaAposta > valorAposta) && novaAposta <= jogador.getFichas())
				break;

			if (Controle.isPlayer(jogador)) {
				comunicador.comunicar("Valor inválido. A nova aposta não pode ser menor que a aposta atual e o jogador deve ter essa quantidade de fichas.");
			}
			novaAposta = 0;
		}

		jogo.apostar(jogador, novaAposta);
		comunicador.comunicar("O jogador " + jogador.getNome() + " aumentou a aposta para " + novaAposta);

		return novaAposta;
	}

	@Override
	public String getNome () {
		return "Apostas";
	}

}
