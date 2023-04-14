package br.com.gabrielgmusskopf.cinema.util;

import br.com.gabrielgmusskopf.cinema.Contexto;

public class IntegerUtil {

	public static int buscar() {
		Integer n;
		do {
			n = Contexto.getUI().inInt();
		} while (n == null);

		return n;
	}

}
