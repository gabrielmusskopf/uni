package br.com.gabrielmusskopf.tamagotchi.desejo;

import java.util.Map;
import java.util.Optional;

import br.com.gabrielmusskopf.tamagotchi.Tamagotchi;

/**
 * Representa um desejo do Tamagotchi. <br/>
 * Contém métodos comuns dos desejos, sendo que cada desejo tem implementações distintas
 */
public interface Desejo extends Informacao {

	/**
	 * Representa a ação do tamagotchi
	 * @param tamagotchi Tamagotchi criado pelo usuário
	 * @param acaoUsuario Ação tomada pelo usuário
	 * @return Possível desejo gerado pelo desejo atual
	 */
	Optional<Desejo> agir(Tamagotchi tamagotchi, int acaoUsuario);

	/**
	 * Comunica o desejo com as possíveis opções de escolha
	 */
	void comunicado();

	/**
	 * Buscar opções do desejo específico
	 * @return Opções
	 */
	Map<Integer, String> getOpcoes();

	/**
	 * Formatação padrão para as opções dos desejos
	 * @return Opções formatadas
	 */
	default String formatarOpcoes(){
		return getOpcoes().entrySet().stream()
				.map(entry -> formatar(entry.getKey(), entry.getValue()))
				.map(s -> s.concat("\n"))
				.reduce(String::concat)
				.orElse(null);
	}

}
