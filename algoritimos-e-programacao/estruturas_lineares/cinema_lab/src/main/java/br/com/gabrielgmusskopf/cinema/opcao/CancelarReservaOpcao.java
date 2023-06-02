package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;

class CancelarReservaOpcao extends OpcaoAbstrata {

	public CancelarReservaOpcao(int numero, Cinema cinema, Grafico grafico) {
		super(numero, cinema, grafico);
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

	@Override
	public String getFrase() {
		return "Cancelar reserva";
	}
}
