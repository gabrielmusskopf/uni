package br.com.gabriel.poker.etapa;

import java.util.Scanner;

import br.com.gabriel.poker.Jogador;
import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.TipoJogador;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.util.Aleatorio;

public class TrocaDeCartas implements Etapa {

	private final Comunicador comunicador;
	private boolean isCompleta;

	public TrocaDeCartas(Comunicador comunicador) {
		this.comunicador = comunicador;
	}

	@Override
	public boolean isCompelta() {
		return isCompleta;
	}

	@Override
	public void executar(Jogo jogo) {

		for (int i = Jogo.POSICAO_BIG_BLIND; i < jogo.getJogadoresRestantes().size(); i++) {
			var jogador = jogo.getJogadores().get(i);
			trocarCartas(jogo, jogador);
		}

		var dealer = jogo.getDealer();
		trocarCartas(jogo, dealer);

		isCompleta = Boolean.TRUE;
	}

	private void trocarCartas(Jogo jogo, Jogador jogador) {
		int quantidadeTrocada = -1;

		if (TipoJogador.USUARIO.equals(jogador.getTipoJogador())) {
			comunicador.comunicar(jogador.getNome() + " deseja trocar quantas cartas?");
			quantidadeTrocada = buscarQuantidadeTrocadaPeloJogador(quantidadeTrocada);
		} else {
			quantidadeTrocada = Aleatorio.buscarEntre(0, Jogo.QUANTIDADE_CARTAS_INICIAIS);
			comunicador.comunicar(jogador.getNome() + " irá trocar " + quantidadeTrocada + (quantidadeTrocada == 1 ? " carta." : " cartas."));
		}

		if (quantidadeTrocada == 0) {
			comunicador.comunicar("Nenhuma carta trocada.\n");
			return;
		}

		var cartasNovas = jogo.getBaralho().comprar(quantidadeTrocada);
		jogador.substituirCartasAleatorias(cartasNovas);

		comunicador.comunicar(quantidadeTrocada + (quantidadeTrocada == 1 ? " carta foi trocada." : " cartas foram trocadas") + "\n");
	}

	private int buscarQuantidadeTrocadaPeloJogador(int quantidadeTrocada) {

		var scanner = new Scanner(System.in);

		while (quantidadeTrocada < 0 || quantidadeTrocada > Jogo.QUANTIDADE_CARTAS_INICIAIS) {
			comunicador.comunicar("Digite um número de 0 à 5.");

			while (!scanner.hasNextInt()) {
				comunicador.comunicar(Cor.VERMELHO, "Valor inválido.");
				scanner.next();
			}

			quantidadeTrocada = scanner.nextInt();
		}
		return quantidadeTrocada;
	}

	@Override
	public String getNome() {
		return "Troca de cartas";
	}
}
