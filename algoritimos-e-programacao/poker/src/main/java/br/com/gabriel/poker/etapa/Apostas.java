package br.com.gabriel.poker.etapa;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import br.com.gabriel.poker.Controle;
import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.jogador.Jogador;
import br.com.gabriel.poker.util.AcaoJogador;
import br.com.gabriel.poker.util.Aleatorio;
import br.com.gabriel.poker.util.Console;

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
		if (!jogo.isDeveExecutarRodadas()) return;

		final var smallBlind = jogo.getSmallBlind();
		int valorAposta = getValorAposta(smallBlind);

		jogo.apostar(smallBlind, valorAposta);
		jogo.adicionarJogadorNaRodada(smallBlind, valorAposta);

		comunicador.comunicar("O small blind, " + smallBlind.getNome() + ", apostou " + valorAposta + (valorAposta == 1 ? " ficha." : " fichas."));

		for (final Jogador jogador : buscarJogadores(jogo)) {
			var isBlindEValorApostaUm = jogo.getBigBlind().equals(jogador) && valorAposta == 1;
			var limite = isBlindEValorApostaUm ? 4 : 3;

			int escolha = buscarEscolha(jogo, valorAposta, jogador, limite);

			switch (escolha) {
				case 1 -> cobrirValor(jogo, valorAposta, jogador);
				case 2 -> valorAposta = aumentarValor(jogo, valorAposta, jogador);
				case 3 -> comunicador.comunicar("O jogador " + jogador.getNome() + " desistiu da mão nesta rodada.");
				case 4 -> comunicador.comunicar("O big blind não tomou nenhuma ação.");
				default -> comunicador.comunicar(Cor.VERMELHO, "Opção inválida.");
			}
		}

		jogo.setApostaDaRodada(valorAposta);

		isCompleta = Boolean.TRUE;
	}


	private List<Jogador> buscarJogadores (Jogo jogo) {
		var posicaoSmallBlind = jogo.getJogadoresRestantes().indexOf(jogo.getSmallBlind());
		var posicaoBigBlind = jogo.getJogadoresRestantes().indexOf(jogo.getBigBlind());
		var posicaoDealer = jogo.getJogadoresRestantes().indexOf(jogo.getDealer());

		final var primeirosJogadores = jogo.getJogadoresRestantes().subList(posicaoSmallBlind + 1, jogo.getJogadoresRestantes().size());
		final var ultimosJogadores = jogo.getJogadoresRestantes().subList(posicaoDealer, posicaoBigBlind + 1);

		return Stream.of(primeirosJogadores, ultimosJogadores)
				.flatMap(Collection::stream)
				.toList();
	}

	private int getValorAposta (Jogador smallBlind) {
		return AcaoJogador.executar(smallBlind)
				.casoPlayer(() -> {
					comunicador.comunicar("{0}, qual o valor do jogo?", smallBlind.getNome());
					comunicador.comunicar(Cor.AZUL, "Fichas: {0}", smallBlind.getFichas());
				})
				.casoPlayer(Scanner::nextInt)
				.casoNPC(fichas -> Aleatorio.buscarEntre(1, fichas));
	}

	private int buscarEscolha (Jogo jogo, int valorAposta, Jogador jogador, int limite) {
		return AcaoJogador.executar(jogador)
				.casoPlayer(() -> {
					comunicador.comunicar(Cor.AZUL, "\n" + jogador.informacoes());
					comunicador.comunicar(Cor.AZUL, jogador.visualizarCartas());
					comunicador.comunicar(Cor.AZUL, "\nAposta atual: " + valorAposta + "\n");
					comunicador.comunicar(OpcoesAposta.opcoesFormatadas(jogo, jogador, valorAposta));
				})
				.casoPlayer(scann -> Console.buscarValor(0, limite))
				.casoNPC(() -> Aleatorio.buscarEntre(1, limite));
	}

	private void cobrirValor (Jogo jogo, int valorAposta, Jogador jogador) {
		var apostado = jogo.apostar(jogador, valorAposta);

		if (apostado != 0)
			comunicador.comunicar("O jogador " + jogador.getNome() + " cobriu a aposta de " + valorAposta + " com " + apostado + (valorAposta == 1 ? " ficha." : " fichas."));

		jogo.adicionarJogadorNaRodada(jogador, apostado);
	}

	private int aumentarValor (Jogo jogo, int valorAposta, Jogador jogador) {
		if (jogador.getFichas() <= valorAposta) {
			var fichasApostadas = jogo.apostar(jogador, jogador.getFichas());

			if (fichasApostadas != 0) {
				comunicador.comunicar("O jogador " + jogador.getNome() + " não tem fichas o suficientes para aumentar a aposta, então todas as " + fichasApostadas + " fichas foram apostadas.");
				jogo.adicionarJogadorNaRodada(jogador, fichasApostadas);
				return fichasApostadas;

			} else return valorAposta;
		}

		if (Controle.isPlayer(jogador)) {
			comunicador.comunicar("Qual valor deseja apostar?");
			comunicador.comunicar(Cor.AZUL, jogador.informacoes());
		}

		int novaAposta = buscarNovaAposta(valorAposta, jogador);

		jogo.apostar(jogador, novaAposta);
		comunicador.comunicar("O jogador " + jogador.getNome() + " aumentou a aposta para " + novaAposta);

		jogo.adicionarJogadorNaRodada(jogador, novaAposta);

		return novaAposta;
	}

	private int buscarNovaAposta (int valorAposta, Jogador jogador) {
		var novaAposta = 0;

		while (novaAposta < valorAposta || novaAposta >= jogador.getFichas()) {
			novaAposta = AcaoJogador.executar(jogador)
					.casoPlayer(Scanner::nextInt)
					.casoNPC(max -> Aleatorio.buscarEntre(1, max));

			if ((novaAposta > valorAposta) && novaAposta <= jogador.getFichas())
				break;

			if (Controle.isPlayer(jogador))
				comunicador.comunicar("Valor inválido. A nova aposta não pode ser menor que a aposta atual e o jogador deve ter essa quantidade de fichas.");

			novaAposta = 0;
		}

		return novaAposta;
	}

	@Override
	public String getNome () {
		return "Apostas";
	}

}
