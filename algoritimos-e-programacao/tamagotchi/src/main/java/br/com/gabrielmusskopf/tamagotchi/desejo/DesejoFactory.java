package br.com.gabrielmusskopf.tamagotchi.desejo;

import br.com.gabrielmusskopf.tamagotchi.comunicacao.Comunicador;

/**
 * Responsável por criar os desejos
 */
public abstract class DesejoFactory {

	public static Desejo criarDesejoComer(Comunicador comunicador) {
		return new DesejoComer(comunicador);
	}

	public static Desejo criarDesejoDormir(Comunicador comunicador) {
		return new DesejoDormir(comunicador);
	}

	public static Desejo criarDesejoTedio(Comunicador comunicador) {
		return new DesejoTedio(comunicador);
	}

}
