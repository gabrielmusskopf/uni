package br.com.gabriel.poker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mao {
	NENHUM(0, "Nenhum jogo"),
	CARTA_ALTA(1, "Carta alta"),
	UM_PAR(2, "Um par"),
	DOIS_PARES(3, "Dois pares"),
	TRINCA(4, "Trinca"),
	SEQUENCIA(5, "Sequência"),
	FLUSH(6, "Flush"),
	FULL_HOUSE(7, "Full house"),
	QUADRA(8, "Quadra"),
	STRAIGTH_FLUSH(9, "Straigth flush"),
	ROYAL_FLUSH(10, "Royal flush"),
	;

	private final int peso;
	private final String nome;

}
