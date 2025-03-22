package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;

class ExibirOpcoesOpcao extends OpcaoAbstrata {

	public ExibirOpcoesOpcao(int numero, Cinema cinema, Grafico grafico) {
		super(numero, cinema, grafico);
	}

	@Override
	public void executar() {
		grafico.mostrarPosicoes(cinema);
		grafico.mostrarQuantidadePosicoes(cinema);
	}

	@Override
	public String getFrase() {
		return "Exibir posições";
	}

}
