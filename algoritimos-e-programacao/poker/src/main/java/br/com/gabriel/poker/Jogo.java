package br.com.gabriel.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import lombok.Getter;

@Getter
public class Jogo {

	public static final Integer POSICAO_DEALER = 0;
	public static final Integer POSICAO_BIG_BLIND = POSICAO_DEALER + 1;
	public static final Integer POSICAO_SMALL_BLIND = POSICAO_BIG_BLIND + 1;
	public static final int QUANTIDADE_CARTAS_INICIAIS = 5;
	public static final int QUANTIDADE_JOGADORES = 5;
	private Baralho baralho;
	private List<Jogador> jogadores = new ArrayList<>();
	private int pote;


	public Jogo(int quantidadeJogadores) {
		baralho = new Baralho();
		var jogadoresNovos = IntStream.range(0, QUANTIDADE_JOGADORES)
				.mapToObj(i -> getJogador(quantidadeJogadores, i))
				.toList();

		jogadores.addAll(jogadoresNovos);
	}

	private static Jogador getJogador(int quantidadeJogadores, int i) {
		var jogador = i > --quantidadeJogadores ? new NPC(--i) : new Player();

		if (POSICAO_DEALER.equals(i))
			jogador.setPosicao(Posicao.DEALER);

		else if (POSICAO_BIG_BLIND.equals(i))
			jogador.setPosicao(Posicao.BIG_BLIND);

		else if (POSICAO_SMALL_BLIND.equals(i))
			jogador.setPosicao(Posicao.SMALL_BLIND);

		else jogador.setPosicao(Posicao.COMUM);

		return jogador;
	}

	public void novaRodada() {
		baralho.montarCartas();
		Collections.rotate(jogadores, -1);
	}

	public List<Jogador> getJogadoresRestantes() {
		return jogadores.stream()
				.filter(Jogador::getEstaJogando)
				.toList();
	}

	public List<Carta> comprarCartasIniciais() {
		return baralho.comprar(QUANTIDADE_CARTAS_INICIAIS);
	}

	public int apostar(Jogador jogador, int fichas) {
		var fichasApostadas = jogador.apostar(fichas);
		pote += fichasApostadas;

		return fichasApostadas;
	}

	public Jogador getDealer() {
		return getJogadoresRestantes().get(POSICAO_DEALER);
	}

	public Jogador getBigBlind() {
		return getJogadoresRestantes().get(POSICAO_BIG_BLIND);
	}

	public Jogador getSmallBlind() {
		return getJogadoresRestantes().get(POSICAO_SMALL_BLIND);
	}

}
