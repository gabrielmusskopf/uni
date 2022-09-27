package br.com.gabrielmusskopf;

import java.util.HashMap;
import java.util.Map;

public class DesejoDormir implements Desejo {

	private Comunicador comunicador;
	private Map<Integer, String> opcoes;

	public DesejoDormir(Comunicador comunicador) {
		this.comunicador = comunicador;
		opcoes = new HashMap<>();
		opcoes.put(1, "Dormir");
		opcoes.put(2, "Permanecer acordado");
	}

	@Override
	public void agir(Tamagotchi tamagotchi, int acaoUsuario) {
		switch (acaoUsuario){
			case 1: {
				tamagotchi.dormir();
				break;
			}
			case 2: {
				tamagotchi.permanecerAcordado();
				break;
			}
			default: comunicador.comunicar(() -> "Opção inválida.");
		}
	}

	@Override
	public String getComunicado() {
		//todo: méotod para formatar opções
		return "Seu Tamagotchi está com sono. O que deseja fazer?\n\n" + formatarOpcoes();
	}

	@Override
	public Map<Integer, String> getOpcoes(){
		return opcoes;
	}

}
