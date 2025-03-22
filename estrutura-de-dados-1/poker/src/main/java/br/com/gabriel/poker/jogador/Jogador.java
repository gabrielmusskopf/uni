package br.com.gabriel.poker.jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gabriel.poker.Carta;
import br.com.gabriel.poker.Posicao;
import br.com.gabriel.poker.TipoJogador;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Jogador {

	private static final Integer QUANTIDADE_FICHAS_INICIAIS = 20;
	private String nome;
	@Setter
	private List<Carta> cartas;
	private TipoJogador tipoJogador;
	private Boolean estaJogando;
	private int fichas;
	@Setter
	private Posicao posicao;

	public Jogador (String nome, TipoJogador tipoJogador) {
		this.nome = nome;
		this.tipoJogador = tipoJogador;
		estaJogando = Boolean.TRUE;
		fichas = QUANTIDADE_FICHAS_INICIAIS;
		cartas = new ArrayList<>();
		posicao = Posicao.COMUM;
	}

	public int apostar (int quantidade) {
		var fichasApostadas = Math.min(quantidade, fichas);
		fichas -= fichasApostadas;

		return fichasApostadas;
	}

	public void receberFichas (int quantidade) {
		fichas += quantidade;
	}

	public void setCartas (List<Carta> cartas) {
		this.cartas = new ArrayList<>(cartas);
	}

	public String informacoes () {
		return "[" + getPosicao().getDescricao() + "] " + getNome() + "\t| Fichas: " + getFichas();
	}

	public String visualizarCartas () {
		return cartas.stream()
				.map(Carta::visualizar)
				.collect(Collectors.joining("  "));
	}

	public boolean eliminar () {
		estaJogando = Boolean.FALSE;
		return Boolean.TRUE;
	}

	public boolean trocarCartas (List<Carta> cartasParaTrocar, List<Carta> cartasNovas) {
		if (cartasParaTrocar.size() != cartasNovas.size())
			return Boolean.FALSE;

		for (int i = 0; i < cartasParaTrocar.size(); i++)
			Collections.replaceAll(cartas, cartasParaTrocar.get(i), cartasNovas.get(i));

		return Boolean.TRUE;
	}

}
