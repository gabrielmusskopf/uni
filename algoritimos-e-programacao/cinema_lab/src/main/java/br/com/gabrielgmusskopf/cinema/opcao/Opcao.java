package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Cinema;
import br.com.gabrielgmusskopf.cinema.Grafico;
import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public abstract class Opcao {

	private int numero;
	private String frase;
	protected Cinema cinema;
	protected Interacao interacao;
	protected Grafico grafico;

	public Opcao(int numero, String frase, Cinema cinema, Interacao interacao, Grafico grafico) {
		this.numero = numero;
		this.frase = frase;
		this.cinema = cinema;
		this.interacao = interacao;
		this.grafico = grafico;
	}

	public abstract void executar();

	protected String buscarEntrada() {
		grafico.mostrarPosicoes(cinema);
		interacao.outln("\nDigite a posição (exemplo 'A2'):");

		String entrada;
		do {
			entrada = interacao.in();
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

	public int getNumero() {
		return numero;
	}

	public String getFrase() {
		return frase;
	}
}
