package br.com.gabrielgmusskopf.cinema;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Grafico {

	public void mostrarPosicoes(Cinema cinema) {
		var numFileiras = cinema.getQuantidadeFileiras();
		var numColunas = cinema.getQuantidadeColunas();

		for (int i = 0; i <= numFileiras; i++){
			for (int j = 0; j < numColunas; j++){

				exibirLetraFileira(numFileiras, i, j);

				if (i != numFileiras) {
					var posicao = cinema.getPosicoes()[i][j];
					Contexto.getUI().out(posicao.informacoes() + "\t");

					continue;
				}

				exibirNumeroColuna(j);
			}
			Contexto.getUI().outln();
		}
	}

	private void exibirLetraFileira(int numFileiras, int i, int j) {
		if (j == 0 && i != numFileiras) {
			Contexto.getUI().out("%c\t".formatted((char) (i + 65)));
		}
	}

	private void exibirNumeroColuna(int j) {
		var texto = j + 1 < 10 ? "\t %d " : "\t %d";
		Contexto.getUI().out(texto.formatted(j + 1));
	}

	public void mostrarQuantidadePosicoes(Cinema cinema){
		var posicoes = Arrays.stream(cinema.getPosicoes())
				.flatMap(Arrays::stream)
				.toList();

		int total = posicoes.size();

		var posicoesPorStatus= posicoes.stream()
				.collect(Collectors.groupingBy(Posicao::getStatusPosicao, Collectors.counting()));

		long livres = quantidade(StatusPosicao.LIVRE, posicoesPorStatus);
		long ocupadas = quantidade(StatusPosicao.OCUPADO, posicoesPorStatus);
		long reservado = quantidade(StatusPosicao.RESERVADO, posicoesPorStatus);

		Contexto.getUI().outln("\nLivre:\t\t%d\t\t%d%%".formatted(livres, percentual(livres, total)));
		Contexto.getUI().outln("Ocupado:\t%d\t\t%d%%".formatted(ocupadas, percentual(ocupadas, total)));
		Contexto.getUI().outln("Reservado:\t%d\t\t%d%%".formatted(reservado, percentual(reservado, total)));
		Contexto.getUI().outln("Total:\t\t%d\n".formatted(total));
	}

	private long quantidade(StatusPosicao statusPosicao, Map<StatusPosicao, Long> posicoes){
		return posicoes.getOrDefault(statusPosicao, 0L);
	}

	private long percentual(long a, long total){
		return a * 100 / total;
	}

}
