package br.com.gabriel.poker.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Aleatorio {

	public static Integer buscarEntre (Integer min, Integer max) {
		return (int) ((Math.random() * ((max + 1) - min) + min));
	}

}
