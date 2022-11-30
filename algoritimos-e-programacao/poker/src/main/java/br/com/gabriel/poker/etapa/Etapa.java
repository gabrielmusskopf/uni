package br.com.gabriel.poker.etapa;

import br.com.gabriel.poker.Jogo;

public interface Etapa {

	boolean isCompelta ();

	void executar (Jogo jogo);

	String getNome ();

}
