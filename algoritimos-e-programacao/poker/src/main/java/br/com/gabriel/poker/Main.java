package br.com.gabriel.poker;

public class Main {
	public static void main(String[] args) {

		var controle = new Controle();

		controle.iniciarJogo(2);

		while (!controle.getEtapaAtual().isCompelta()) {
			controle.executarEtapa();
		}

	}
}