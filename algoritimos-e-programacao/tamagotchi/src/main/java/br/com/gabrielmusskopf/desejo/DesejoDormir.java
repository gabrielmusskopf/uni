package br.com.gabrielmusskopf.desejo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.gabrielmusskopf.comunicacao.Comunicador;
import br.com.gabrielmusskopf.Tamagotchi;

class DesejoDormir implements Desejo {

	private Comunicador comunicador;
	private Map<Integer, String> opcoes;

	public DesejoDormir(Comunicador comunicador) {
		this.comunicador = comunicador;
		opcoes = new HashMap<>();
		opcoes.put(1, "Dormir");
		opcoes.put(2, "Permanecer acordado");
	}

	@Override
	public Optional<Desejo> agir(Tamagotchi tamagotchi, int acaoUsuario) {
		switch (acaoUsuario) {
			case 1 -> tamagotchi.dormir();
			case 2 -> tamagotchi.permanecerAcordado();
			default -> comunicador.comunicar(() -> "Opção inválida.");
		}

		return Optional.empty();
	}

	@Override
	public void comunicado() {
		comunicador.comunicar("\nSeu Tamagotchi está com sono. O que deseja fazer?\n\n" + formatarOpcoes());
	}

	@Override
	public Map<Integer, String> getOpcoes(){
		return opcoes;
	}

	@Override
	public TipoDesejo getTipoDesejo() {
		return TipoDesejo.DORMIR;
	}

}
