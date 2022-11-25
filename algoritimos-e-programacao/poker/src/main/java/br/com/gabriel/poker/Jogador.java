package br.com.gabriel.poker;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Jogador {

	private static final Integer QUANTIDADE_FICHAS_INICIAIS = 20;
	private String nome;
	@Setter
	private List<Carta> cartas;
	private TipoJogador tipoJogador;
	private Boolean estaJogando;
	private int fichas;
	@Setter
	private Posicao posicao;

	public Jogador(String nome, TipoJogador tipoJogador) {
		this.nome = nome;
		this.tipoJogador = tipoJogador;
		estaJogando = Boolean.TRUE;
		fichas = QUANTIDADE_FICHAS_INICIAIS;
		cartas = new ArrayList<>();
		posicao = Posicao.COMUM;
	}

	//TODO: Implementar opção para o jogador decidir se aposta tudo o que tem, quando não houver o suficiente
	public int apostar(int quantidade) {
		var fichasApostadas = quantidade >= fichas ? fichas : quantidade;
		fichas -= fichasApostadas;

		return fichasApostadas;
	}

	public boolean substituirCartasAleatorias(List<Carta> cartasNovas) {
		for (int i = 0; i < cartasNovas.size(); i++) {
			var carta = cartasNovas.get(i);
			if (i < cartas.size()) {
				cartas.set(i, carta);
			} else
				cartas.add(carta);
		}

		return Boolean.TRUE;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = new ArrayList<Carta>(cartas);
	}

}
