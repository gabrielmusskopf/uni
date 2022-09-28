package br.com.gabrielmusskopf.desejo;

import br.com.gabrielmusskopf.comunicacao.Comunicador;

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
