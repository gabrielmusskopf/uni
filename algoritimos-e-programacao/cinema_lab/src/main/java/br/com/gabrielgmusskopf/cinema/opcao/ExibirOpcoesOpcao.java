package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public class ExibirOpcoesOpcao extends Opcao {

	public ExibirOpcoesOpcao(int numero, Cinema cinema, Interacao interacao, Grafico grafico) {
		super(numero, "Exibir posições", cinema, interacao, grafico);
	}

	@Override
	public void executar() {
		grafico.mostrarPosicoes(cinema);
		grafico.mostrarQuantidadePosicoes(cinema);
	}
}
