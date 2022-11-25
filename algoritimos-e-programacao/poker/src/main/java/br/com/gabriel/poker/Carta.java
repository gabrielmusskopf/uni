package br.com.gabriel.poker;


import lombok.Getter;

@Getter
public class Carta {

	private Integer numero;
	private Naipe naipe;

	public Carta(Integer numero, Naipe naipe) {
		if (numero < 0 || numero > 13) {
			throw new RegraException("Número inválido. Deve ser de 1 à 13.");
		}

		this.numero = numero;
		this.naipe = naipe;
	}

}
