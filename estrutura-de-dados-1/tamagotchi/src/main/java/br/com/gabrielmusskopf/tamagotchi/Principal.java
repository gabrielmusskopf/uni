package br.com.gabrielmusskopf.tamagotchi;

import java.util.HashMap;
import java.util.List;

import br.com.gabrielmusskopf.tamagotchi.comunicacao.ComunicacaoLog;
import br.com.gabrielmusskopf.tamagotchi.comunicacao.Comunicador;
import br.com.gabrielmusskopf.tamagotchi.comunicacao.Cor;
import br.com.gabrielmusskopf.tamagotchi.desejo.Desejo;
import br.com.gabrielmusskopf.tamagotchi.desejo.DesejoFactory;
import br.com.gabrielmusskopf.tamagotchi.exception.TamagochiFaleceuException;


public class Principal {

	private static final String TECLA_INICIO = "1";
	private static final String TECLA_SAIDA = "Q";

	/**
	 * Método principal. Responsável pela configuração, injeção de dependências, menu e incício do jogo
	 */
	public static void main(String[] args) {
		var comunicador = new ComunicacaoLog();

		var jogo = new Jogo(config -> {
			config.setDesejos(List.of(
					DesejoFactory.criarDesejoDormir(comunicador),
					DesejoFactory.criarDesejoComer(comunicador),
					DesejoFactory.criarDesejoTedio(comunicador)
			));
			config.setComunicador(comunicador);
		});


		var opcoes = new HashMap<String, String>();
		opcoes.put(TECLA_INICIO, "Iniciar");
		opcoes.put(TECLA_SAIDA, "Sair");

		jogo.iniciar(opcoes);

		String opcaoInicial;
		do {
			opcaoInicial= comunicador.lerLinha();
		} while (!opcoes.containsKey(opcaoInicial));

		while (!TECLA_SAIDA.equals(opcaoInicial)){

			if (TECLA_INICIO.equals(opcaoInicial)) {
				jogar(comunicador, jogo);
			}

			if (!jogo.deveReiniciar())
				break;

		}

		comunicador.comunicar("\nFoi um prazer!");
	}


	/**
	 * Método responsável por controlar a dinâmica básica do jogo.
	 * @param comunicador Classe que implementa a interface Comunicador. Responvável pela comunicação com o usuário.
	 * @param jogo Classe jogo contendo os métodos e informações necessárias para iniciar.
	 */
	private static void jogar(Comunicador comunicador, Jogo jogo) {
		comunicador.comunicar("\nDigite o nome do Tamagotchi:");

		var tamagotchi = new Tamagotchi(comunicador.lerLinha(), comunicador);
		tamagotchi.comunicarStatus();

		jogo.setTamagotchi(tamagotchi);

		while (jogo.existe()){

			Desejo desejo;
			do {
				desejo = jogo.getAcao();
				desejo.comunicado();

				String opcao = comunicador.lerLinha();
				while (opcaoInvalida(opcao)){
					comunicador.comunicar(Cor.VERMELHO, "Opção inválida. Selecione novamente.\n");
					opcao = comunicador.lerLinha();
				}

				if (opcaoInvalida(opcao))
					continue;

				try {
					jogo.executarAcao(desejo, Integer.parseInt(opcao));
				}catch (TamagochiFaleceuException e){
					comunicador.comunicar(Cor.VERMELHO, e.getMessage());
				}

			}while (jogo.temProximoDesejo());
		}
	}

	/**
	 * Método auxiliar para validar se a opção informada é válida, ou seja, se o valor digitado é um inteiro
	 * @param opcao Texto digitado pelo usuário
	 * @return Boleano informando se texto é um inteiro
	 */
	private static boolean opcaoInvalida(String opcao) {
			try {
				Integer.valueOf(opcao);
				return false;
			}catch (Exception e){
				return true;
			}
	}
}