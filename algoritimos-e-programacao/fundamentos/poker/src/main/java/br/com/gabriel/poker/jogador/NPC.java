package br.com.gabriel.poker.jogador;

import br.com.gabriel.poker.TipoJogador;

public class NPC extends Jogador {

	public NPC (int numero) {
		super("NPC " + numero, TipoJogador.NPC);
	}

}
