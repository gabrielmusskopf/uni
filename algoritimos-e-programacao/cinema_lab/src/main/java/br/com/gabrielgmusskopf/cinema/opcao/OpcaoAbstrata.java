package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Contexto;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;

abstract class OpcaoAbstrata implements Opcao {

    private int numero;
	protected Cinema cinema;
	protected Grafico grafico;

	protected OpcaoAbstrata(int numero, Cinema cinema, Grafico grafico) {
        this.numero = numero;
		this.cinema = cinema;
		this.grafico = grafico;
	}

	protected String buscarEntrada() {
		grafico.mostrarPosicoes(cinema);
		Contexto.getUI().outln("\nDigite a posição (exemplo 'A2'):");

		String entrada;
		do {
			entrada = Contexto.getUI().in();
		} while (entrada.equals(""));

		var entradaMaxima = String.valueOf(cinema.getQuantidadeColunas()).length() + 1;

		if (entrada.length() > entradaMaxima){
			throw new NegocioExcecao("Posição para reserva inválida!");
		}

		return entrada;
	}

	protected char buscarY(String s) {
		var vertical = s.toUpperCase().substring(0, 1);
		return vertical.charAt(0);
	}

	protected int buscarX(String s) {
		var horizontal = s.substring(1);
		return Integer.parseInt(horizontal);
	}

    @Override
    public int getNumero() {
        return numero;
    }


}
