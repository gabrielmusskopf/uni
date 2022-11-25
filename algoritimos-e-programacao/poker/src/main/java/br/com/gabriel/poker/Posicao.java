package br.com.gabriel.poker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Posicao {
	DEALER("D"),
	BIG_BLIND("BB"),
	SMALL_BLIND("SB"),
	COMUM("C"),
	;

	private final String descricao;

}
