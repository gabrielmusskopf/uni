package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public class ReservarMultiplosColunaOuFileOpcao extends Opcao {

	private static final int A_ASCII = 65;

	public ReservarMultiplosColunaOuFileOpcao(int numero, Cinema cinema, Interacao interacao, Grafico grafico) {
		super(numero, "Reservar múltiplos em uma coluna ou fila", cinema, interacao, grafico);
	}

	@Override
	public void executar() {
		grafico.mostrarPosicoes(cinema);
		interacao.outln("\nDigite as posições separadas por ':' (exemplo 'A2:A8'):");

		String entrada;
		do {
			entrada = interacao.in();
		}while (entrada.equals(""));

		int entradaMaxima = String.valueOf(cinema.getQuantidadeColunas()).length() + 1;
		var posicoes = entrada.split(":");

		var minima = posicoes[0].toUpperCase();
		var maxima = posicoes[1].toUpperCase();

		if (isReservaDeFila(minima, maxima)) {
			reservarFila(entradaMaxima, minima, maxima);
		}
		else if (isReservaDeColuna(minima, maxima)) {
			reservarColuna(entradaMaxima, minima, maxima);
		}

		grafico.mostrarPosicoes(cinema);
	}

	private void reservarFila(int entradaMaxima, String minima, String maxima) {
		char coluna = minima.charAt(0);
		int inicio = Integer.parseInt(minima.substring(1));
		int fim = Integer.parseInt(maxima.substring(1));

		if (String.valueOf(inicio).length() > entradaMaxima){
			throw new NegocioExcecao("Posição %c%d para reserva inválida!".formatted(coluna, inicio));
		}

		if (String.valueOf(fim).length() > entradaMaxima){
			throw new NegocioExcecao("Posição %c%d para reserva inválida!".formatted(coluna, coluna));
		}

		for (int i = inicio; i <= fim; i++) {
			if(!cinema.reservar(coluna, i)){
				throw new NegocioExcecao("O lugar %c%d já está ocupado!".formatted(coluna, i));
			}
		}
	}

	private void reservarColuna(int entradaMaxima, String minima, String maxima) {
		int fila = Integer.parseInt(minima.substring(1));
		char inicio = minima.charAt(0);
		char fim= maxima.charAt(0);

		if (String.valueOf(fila).length() > entradaMaxima){
			throw new NegocioExcecao("Posição %d para reserva inválida!".formatted(fila));
		}

		for (int i = inicio - A_ASCII; i <= fim - A_ASCII; i++) {
			if(!cinema.reservar(i, fila)){
				throw new NegocioExcecao("O lugar %c%d já está ocupado!".formatted(fila, i));
			}
		}
	}

	private boolean isReservaDeFila(String minima, String maxima) {
		return minima.substring(0, 1)
				.equals(maxima.substring(0, 1));
	}

	private boolean isReservaDeColuna(String minima, String maxima) {
		return minima.substring(1)
				.equals(maxima.substring(1));
	}
}
