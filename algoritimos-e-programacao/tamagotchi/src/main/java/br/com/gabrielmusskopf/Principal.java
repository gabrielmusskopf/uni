package br.com.gabrielmusskopf;

import java.util.List;
import java.util.Scanner;

public class Principal {
	private static final String TECLA_SAIDA = "Q";

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		var comunicador = new ComunicacaoLog();

		System.out.println("Digite o nome do Tamagotchi:");

		var jogo = new Jogo(config -> {
			config.setAcoes(List.of(new DesejoDormir(comunicador)));
			config.setComunicador(comunicador);
			config.setTamagotchi(new Tamagotchi(scanner.nextLine(), comunicador));
		});

		jogo.iniciar();

		var entrada = "";
		while (!entrada.toUpperCase().equals(TECLA_SAIDA)){
			var desejo = jogo.getAcao();
			System.out.println(desejo.getComunicado());

			var opcao = scanner.nextInt();
			jogo.executarAcao(desejo, opcao);

			//entrada = scanner.nextLine();
		}
	}
}