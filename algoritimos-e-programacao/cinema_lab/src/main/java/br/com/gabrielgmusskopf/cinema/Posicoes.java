package br.com.gabrielgmusskopf.cinema;

public class Posicoes {

	private int x;
	private int y;
	private Posicao[][] posicoes;

	public Posicoes(int x, int y, Posicao[][] posicoes) {
		this.x = x;
		this.y = y;
		this.posicoes = posicoes;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Posicao[][] getPosicoes() {
		return posicoes;
	}
}
