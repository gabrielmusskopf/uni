package br.com.gabriel.poker.jogador;

import java.util.Objects;

import br.com.gabriel.poker.TipoJogador;

public class Player extends Jogador {

	public Player (String nome) {
		super(Objects.requireNonNullElse(nome, "Player"), TipoJogador.USUARIO);
	}

}
