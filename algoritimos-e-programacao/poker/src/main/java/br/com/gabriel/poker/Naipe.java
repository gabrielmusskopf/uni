package br.com.gabriel.poker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Naipe {
	COPAS("♡"),
	OURO("◆"),
	ESPADA("♠"),
	PAUS("♣");

	private final String simbolo;

}
