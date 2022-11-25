package br.com.gabriel.poker.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Aleatorio {

	public static int buscarEntre(int min, int max) {
		return (int) ((Math.random() * (max - min) + min));
	}

}
