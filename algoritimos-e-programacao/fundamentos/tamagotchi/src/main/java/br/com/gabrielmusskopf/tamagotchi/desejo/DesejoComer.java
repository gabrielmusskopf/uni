package br.com.gabrielmusskopf.tamagotchi.desejo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.gabrielmusskopf.tamagotchi.comunicacao.Comunicador;
import br.com.gabrielmusskopf.tamagotchi.Tamagotchi;
import br.com.gabrielmusskopf.tamagotchi.comunicacao.Cor;

/**
 * Desejo de comer, com as devidas ações implementadas de maneira específica
 */
class DesejoComer implements Desejo {

	private Comunicador comunicador;
	private Map<Integer, String> opcoes;

	public DesejoComer(Comunicador comunicador) {
		this.comunicador = comunicador;
		opcoes = new HashMap<>();
		opcoes.put(1, "Comer muito");
		opcoes.put(2, "Comer pouco");
		opcoes.put(3, "Não comer");
	}

	@Override
	public Optional<Desejo> agir(Tamagotchi tamagotchi, int acaoUsuario) {
		switch (acaoUsuario) {
			case 1 -> tamagotchi.comerMuito();
			case 2 -> tamagotchi.comerPouco();
			case 3 -> tamagotchi.naoComer();
			default -> {
				comunicador.comunicar(Cor.VERMELHO, "Opção inválida.\n");
				return Optional.of(this);
			}
		}
		return Optional.empty();
	}

	@Override
	public void comunicado() {
		comunicador.comunicar("Seu Tamagotchi está com fome. O que deseja fazer?\n\n" + formatarOpcoes());
	}

	@Override
	public Map<Integer, String> getOpcoes(){
		return opcoes;
	}

}
