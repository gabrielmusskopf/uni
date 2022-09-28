package br.com.gabrielmusskopf.desejo;

import java.util.Map;
import java.util.Optional;

import br.com.gabrielmusskopf.Tamagotchi;

public interface Desejo extends Informacao {

	Optional<Desejo> agir(Tamagotchi tamagotchi, int acaoUsuario);

	void comunicado();

	Map<Integer, String> getOpcoes();

	TipoDesejo getTipoDesejo();

	default String formatarOpcoes(){
		return getOpcoes().entrySet().stream()
				.map(entry -> formatar(entry.getKey(), entry.getValue()))
				.map(s -> s.concat("\n"))
				.reduce(String::concat)
				.orElse(null);
	}

	enum TipoDesejo {
		COMER, DORMIR, TEDIO;
	}
}
