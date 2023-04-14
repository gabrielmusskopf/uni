package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;

class ReservarOpcao extends OpcaoAbstrata {

	public ReservarOpcao(int numero, Cinema cinema, Grafico grafico) {
		super(numero, cinema, grafico);
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

	@Override
	public String getFrase() {
		return "Reservar posição";
	}
}
