package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public class CancelarReservaOpcao extends Opcao {

	public CancelarReservaOpcao(int numero, Cinema cinema, Interacao interacao, Grafico grafico) {
		super(numero, "Cancelar reserva", cinema, interacao, grafico);
	}

	@Override
	public void executar() {
		var entrada = buscarEntrada();

		var x = buscarX(entrada);
		var y = buscarY(entrada);

		if(!cinema.cancelarReserva(y, x)){
			throw new NegocioExcecao("O lugar não está reservado!");
		}

		grafico.mostrarPosicoes(cinema);
	}
}
