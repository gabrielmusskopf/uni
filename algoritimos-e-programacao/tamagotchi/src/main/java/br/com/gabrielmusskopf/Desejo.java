package br.com.gabrielmusskopf;

import java.text.MessageFormat;
import java.util.Map;
import java.util.stream.Collectors;

public interface Desejo {

	void agir(Tamagotchi tamagotchi, int acaoUsuario);

	String getComunicado();

	Map<Integer, String> getOpcoes();

	default String formatarOpcoes(){
		return getOpcoes().entrySet().stream()
				.map(entry -> MessageFormat.format("{0} - {1}\n", entry.getKey(), entry.getValue()))
				.reduce(String::concat)
				.orElse(null);
	}
}
