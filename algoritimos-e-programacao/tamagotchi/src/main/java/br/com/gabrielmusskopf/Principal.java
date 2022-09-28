package br.com.gabrielmusskopf;

import java.util.List;

import br.com.gabrielmusskopf.comunicacao.ComunicacaoLog;
import br.com.gabrielmusskopf.desejo.Desejo;
import br.com.gabrielmusskopf.desejo.DesejoFactory;

public class Principal {
	private static final String TECLA_SAIDA = "Q";

	public static void main(String[] args) {
		var comunicador = new ComunicacaoLog();

		comunicador.comunicar("Digite o nome do Tamagotchi:");
		var tamagotchi = new Tamagotchi(comunicador.lerLinha(), comunicador);

		var jogo = new Jogo(config -> {
			config.setDesejos(List.of(
					DesejoFactory.criarDesejoDormir(comunicador),
					DesejoFactory.criarDesejoComer(comunicador),
					DesejoFactory.criarDesejoTedio(comunicador)
			));
			config.setComunicador(comunicador);
			config.setTamagotchi(tamagotchi);
		});

		tamagotchi.comunicarStatus();

		jogo.iniciar();

		var entrada = "";
		while (!entrada.equalsIgnoreCase(TECLA_SAIDA) && jogo.existe()){

			Desejo desejo;
			do {
				desejo = jogo.getAcao();
				desejo.comunicado();

				var opcao = comunicador.lerInteiro();
				jogo.executarAcao(desejo, opcao);

			}while (jogo.temProximoDesejo());

		}
	}
}