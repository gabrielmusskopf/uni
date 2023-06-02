package br.com.gabrielgmusskopf.cinema.opcao;

import java.util.Arrays;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Contexto;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;

class ReservarMultiplosOpcao extends OpcaoAbstrata {

	public ReservarMultiplosOpcao(int numero, Cinema cinema, Grafico grafico) {
		super(numero, cinema, grafico);
	}

	@Override
	public void executar() {
		grafico.mostrarPosicoes(cinema);
		Contexto.getUI().outln("\nDigite as posições separadas por espaço (exemplo 'A2 A3 B4'):");

		String entrada;
		do {
			entrada = Contexto.getUI().in();
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

	@Override
	public String getFrase() {
		return "Reservar múltiplos";
	}
}
