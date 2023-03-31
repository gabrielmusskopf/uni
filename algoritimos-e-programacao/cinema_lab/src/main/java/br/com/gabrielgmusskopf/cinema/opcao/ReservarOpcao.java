package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public class ReservarOpcao extends Opcao {

	public ReservarOpcao(int numero, Cinema cinema, Interacao interacao, Grafico grafico) {
		super(numero, "Reservar", cinema, interacao, grafico);
	}

	@Override
	public void executar() {
		var entrada = buscarEntrada();

		var x = buscarX(entrada);
		var y = buscarY(entrada);

		if(!cinema.reservar(y, x)){
			throw new NegocioExcecao("O lugar já está ocupado!");
		}

		grafico.mostrarPosicoes(cinema);
	}
}
