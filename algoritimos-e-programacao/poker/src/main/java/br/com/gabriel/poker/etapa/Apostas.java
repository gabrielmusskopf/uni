package br.com.gabriel.poker.etapa;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Stream;

import br.com.gabriel.poker.Controle;
import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.util.Aleatorio;

public class Apostas implements Etapa {

	private final Comunicador comunicador;
	private boolean isCompleta;

	public Apostas(Comunicador comunicador) {
		this.comunicador = comunicador;
	}

	@Override
	public boolean isCompelta() {
		return isCompleta;
	}

	@Override
	public void executar(Jogo jogo) {

		final var primeirosJogadores = jogo.getJogadoresRestantes().subList(Jogo.POSICAO_SMALL_BLIND + 1, jogo.getJogadores().size());
		final var ultimosJogadores = jogo.getJogadoresRestantes().subList(Jogo.POSICAO_DEALER, Jogo.POSICAO_BIG_BLIND + 1);
		final var ordemJogadores = Stream.of(primeirosJogadores, ultimosJogadores)
				.flatMap(Collection::stream)
				.toList();

		final var scanner = new Scanner(System.in);

		final var smallBlind = jogo.getSmallBlind();

		int valorAposta = 0;
		if (Controle.isPlayer(smallBlind)) {
			comunicador.comunicar("Qual o valor do jogo?");
			valorAposta = scanner.nextInt();
		} else {
			valorAposta = Aleatorio.buscarEntre(1, smallBlind.getFichas());
		}
		jogo.apostar(smallBlind, valorAposta);
		comunicador.comunicar("O small blind, " + smallBlind.getNome() + ", apostou " + valorAposta + (valorAposta == 1 ? " ficha" : " fichas") + ".\n");


		for (int i = 0; i < ordemJogadores.size(); i++) {
			final var jogador = ordemJogadores.get(0);
			var isBlindEValorApostaUm = jogo.getBigBlind().equals(jogador) && valorAposta == 1;
			var limite = isBlindEValorApostaUm ? 4 : 3;

			var escolha = 0;
			if (Controle.isPlayer(jogador)) {
				if (jogo.getBigBlind().equals(jogador) && valorAposta == 1) {
					comunicador.comunicar("Sua vez. Você pode:\n1 - Compeltar\n2 - Aumentar a aposta\n3 - Desistir da mão\n" + (isBlindEValorApostaUm ? "4 - Não fazer nada. (\"Check\")\n" : ""));

					while (escolha <= 0 || escolha >= limite)
						escolha = scanner.nextInt();
				}
			} else {
				escolha = Aleatorio.buscarEntre(1, limite);
			}

			switch (escolha) {
				case 1 -> {
					var apostado = jogo.apostar(jogador, valorAposta);
					comunicador.comunicar(apostado + (apostado == 1 ? " foi apostado." : " foram apostadas"));
				}
				case 2 -> {
					comunicador.comunicar("Qual valor deseja apostar?");

					var novaAposta = 0;
					while (novaAposta < valorAposta) {
						novaAposta = Controle.isPlayer(jogador) ? scanner.nextInt() : Aleatorio.buscarEntre(1, jogador.getFichas());

						if (novaAposta > valorAposta)
							break;

						comunicador.comunicar("Valor " + novaAposta + " apostado. A nova aposta não pode ser menor que a aposta atual.");

						if (Controle.isPlayer(jogador))
							scanner.next();
					}

					valorAposta = novaAposta;
					comunicador.comunicar("O jogador " + jogador.getNome() + " aumentou a aposta para " + novaAposta);
				}
				case 3 -> comunicador.comunicar("O jogador " + jogador.getNome() + " desistiu da mão nesta rodada.");
				case 4 -> comunicador.comunicar("O big blind não tomou nenhuma ação.");
			}
		}

		isCompleta = Boolean.TRUE;
	}

	@Override
	public String getNome() {
		return "Apostas";
	}
}
