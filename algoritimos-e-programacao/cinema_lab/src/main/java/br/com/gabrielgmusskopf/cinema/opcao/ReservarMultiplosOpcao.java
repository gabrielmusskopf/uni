package br.com.gabrielgmusskopf.cinema.opcao;

import java.util.Arrays;
import java.util.Scanner;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public class ReservarMultiplosOpcao extends Opcao {

	public ReservarMultiplosOpcao(int numero, Cinema cinema, Interacao interacao, Grafico grafico) {
		super(numero, "Reservar múltiplos", cinema, interacao, grafico);
	}

	@Override
	public void executar() {
		grafico.mostrarPosicoes(cinema);
		interacao.outln("\nDigite as posições separadas por espaço (exemplo 'A2 A3 B4'):");

		String entrada;
		do {
			entrada = interacao.in();
		}while (entrada.equals(""));

		var entradaMaxima = String.valueOf(cinema.getQuantidadeColunas()).length() + 1;
		var posicoes = entrada.split(" ");

		Arrays.stream(posicoes)
				.distinct()
				.forEach(posicao -> {
					if (posicao.length() > entradaMaxima){
						throw new NegocioExcecao("Posição %s para reserva inválida!".formatted(posicao));
					}

					var x = buscarX(posicao);
					var y = buscarY(posicao);

					if(!cinema.reservar(y, x)){
						throw new NegocioExcecao("O lugar %s já está ocupado!".formatted(posicao));
					}
				});

		grafico.mostrarPosicoes(cinema);
	}
}
