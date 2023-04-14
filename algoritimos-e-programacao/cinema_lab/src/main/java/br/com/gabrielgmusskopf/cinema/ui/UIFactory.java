package br.com.gabrielgmusskopf.cinema.ui;

public class UIFactory {

	public static UI construir(TipoUI ui) {
		return switch (ui) {
			case CONSOLE -> new Terminal();
		};
	}

}
