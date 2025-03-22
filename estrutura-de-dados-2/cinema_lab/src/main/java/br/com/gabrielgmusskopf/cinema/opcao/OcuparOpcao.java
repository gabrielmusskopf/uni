package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;

class OcuparOpcao extends OpcaoAbstrata {

	public OcuparOpcao(int numero, Cinema cinema, Grafico grafico) {
		super(numero, cinema, grafico);
	}

	@Override
	public void executar() {
		var entrada = buscarEntrada();

		var x = buscarX(entrada);
		var y = buscarY(entrada);

		if(!cinema.ocupar(y, x)){
			throw new NegocioExcecao("O lugar já está reservado ou ocupado!");
		}

		grafico.mostrarPosicoes(cinema);
	}

	@Override
	public String getFrase() {
		return "Ocupar lugar";
	}
}
