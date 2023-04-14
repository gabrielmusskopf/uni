package br.com.gabrielgmusskopf.cinema;

import br.com.gabrielgmusskopf.cinema.ui.UI;

public class Contexto {

	private static UI ui;

	public static void definirUI(UI ui) {
		Contexto.ui = ui;
	}

	public static UI getUI() {
		return ui;
	}

}
