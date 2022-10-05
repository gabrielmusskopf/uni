package br.com.gabrielmusskopf.tamagotchi.desejo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.gabrielmusskopf.tamagotchi.comunicacao.Comunicador;
import br.com.gabrielmusskopf.tamagotchi.Tamagotchi;
import br.com.gabrielmusskopf.tamagotchi.comunicacao.Cor;
/**
 * Desejo de tédio, com as devidas ações implementadas de maneira específica
 */
class DesejoTedio implements Desejo {

	private Comunicador comunicador;
	private Map<Integer, String> opcoes;

	public DesejoTedio(Comunicador comunicador) {
		this.comunicador = comunicador;
		opcoes = new HashMap<>();
		opcoes.put(1, "Correr 10 minutos");
		opcoes.put(2, "Caminhar 10 minutos");
	}

	@Override
	public Optional<Desejo> agir(Tamagotchi tamagotchi, int acaoUsuario) {
		switch (acaoUsuario) {
			case 1 -> tamagotchi.correr();
			case 2 -> {
				tamagotchi.caminhar();
				return Optional.of(DesejoFactory.criarDesejoComer(comunicador));
			}
			default -> {
				comunicador.comunicar(Cor.VERMELHO, "Opção inválida.\n");
				return Optional.of(this);
			}
		}

		return Optional.empty();
	}

	@Override
	public void comunicado() {
		comunicador.comunicar("Seu Tamagotchi está entendiado. O que deseja fazer?\n\n" + formatarOpcoes());
	}

	@Override
	public Map<Integer, String> getOpcoes(){
		return opcoes;
	}

}
