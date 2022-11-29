package br.com.gabriel.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import br.com.gabriel.poker.jogador.Jogador;
import br.com.gabriel.poker.jogador.NPC;
import br.com.gabriel.poker.jogador.Player;
import lombok.Getter;
import lombok.Setter;

public class Jogo {

	public static final Integer POSICAO_DEALER = 0;
	public static final Integer POSICAO_BIG_BLIND = POSICAO_DEALER + 1;
	public static final Integer POSICAO_SMALL_BLIND = POSICAO_BIG_BLIND + 1;
	public static final int QUANTIDADE_CARTAS_INICIAIS = 5;
	public static final int QUANTIDADE_JOGADORES = 5;
	@Getter
	private Baralho baralho;
	private List<Jogador> jogadores = new ArrayList<>();
	@Getter
	private int pote;
	@Setter
	private int apostaDaRodada;
	@Getter
	private Map<Jogador, Integer> apostas = new HashMap<>();

	public Jogo (List<String> nomesJogadores) {
		baralho = new Baralho();
		for (int i = 0; i < QUANTIDADE_JOGADORES; i++) {
			var jogador = i > nomesJogadores.size() - 1 ? new NPC(i) : new Player(nomesJogadores.get(i));
			adicionarPosicoes(i, jogador);

			jogadores.add(jogador);
		}
	}

	public void novaRodada () {
		baralho.montarCartas();
		Collections.rotate(jogadores, -1);

		IntStream.range(0, getJogadoresRestantes().size())
				.forEach(i -> adicionarPosicoes(i, getJogadoresRestantes().get(i)));

		apostas.clear();
	}

	private void adicionarPosicoes (int i, Jogador jogador) {
		if (POSICAO_DEALER.equals(i))
			jogador.setPosicao(Posicao.DEALER);

		else if (POSICAO_BIG_BLIND.equals(i))
			jogador.setPosicao(Posicao.BIG_BLIND);

		else if (POSICAO_SMALL_BLIND.equals(i))
			jogador.setPosicao(Posicao.SMALL_BLIND);

		else jogador.setPosicao(Posicao.COMUM);
	}

	public List<Jogador> getJogadoresRestantes () {
		return jogadores.stream()
				.filter(Jogador::getEstaJogando)
				.toList();
	}

	public List<Carta> comprarCartasIniciais () {
		return baralho.comprar(QUANTIDADE_CARTAS_INICIAIS);
	}

	public int apostar (Jogador jogador, int fichas) {
		var fichasApostadas = jogador.apostar(fichas);
		pote += fichasApostadas;

		if (fichasApostadas != 0) {
			apostas.put(jogador, fichasApostadas);
		}

		return fichasApostadas;
	}

	public Jogador getDealer () {
		return getJogadorPorPosicao(Posicao.DEALER);
	}

	public Jogador getBigBlind () {
		return getJogadorPorPosicao(Posicao.BIG_BLIND);
	}

	public Jogador getSmallBlind () {
		return getJogadorPorPosicao(Posicao.SMALL_BLIND);
	}

	private Jogador getJogadorPorPosicao (Posicao posicao) {
		return getJogadoresRestantes().stream()
				.filter(jogador -> posicao.equals(jogador.getPosicao()))
				.findFirst()
				.orElse(null);
	}

	public void distribuirPote (List<Jogador> vencedores) {
		vencedores.forEach(vencedor -> {
			var apostaRealizada = apostas.entrySet().stream()
					.filter(entry -> entry.getKey().equals(vencedor))
					.map(Map.Entry::getValue)
					.findFirst()
					.orElse(0);

			if ((apostaDaRodada / vencedores.size()) > apostaRealizada && apostaRealizada < apostaDaRodada)
				distribuirPote(vencedor, apostaRealizada);
			else
				distribuirPote(vencedor, pote / vencedores.size());

		});

		if (pote != 0) {
			var quantidadePorJogador = pote / getJogadoresParticipandoNaRodada().size();
			getJogadoresParticipandoNaRodada().forEach(jogador -> distribuirPote(jogador, quantidadePorJogador));
			pote = 0;
		}
	}

	private void distribuirPote (Jogador jogador, int fichas) {
		jogador.receberFichas(fichas);
		pote -= fichas;
	}

	public List<Jogador> eliminarJogadoresSemFicha () {
		return getJogadoresParticipandoNaRodada().stream()
				.filter(jogador -> jogador.getFichas() <= 0)
				.peek(Jogador::eliminar)
				.toList();
	}

	public Set<Jogador> getJogadoresParticipandoNaRodada () {
		return apostas.keySet();
	}

}
