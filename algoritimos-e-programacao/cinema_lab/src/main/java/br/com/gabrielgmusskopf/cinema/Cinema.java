package br.com.gabrielgmusskopf.cinema;

import java.util.Arrays;
import java.util.List;

import br.com.gabrielgmusskopf.cinema.excecao.NegocioExcecao;
import br.com.gabrielgmusskopf.cinema.interacao.Interacao;

public class Cinema {

	private static final int PRIMEIRA_POSICAO_ASCII = 65;
	private static final int QUANTIDADE_FILEIRAS_MAXIMA = 26;
	private static final int QUANTIDADE_FILEIRAS_PADRAO = 14;
	private static final int QUANTIDADE_COLUNAS_PADRAO = 12;
	private Interacao interacao;
	private int quantidadeFileiras;
	private int quantidadeColunas;
	private Posicao[][] posicoes;

	public Cinema(Interacao interacao){
		this(QUANTIDADE_FILEIRAS_PADRAO, QUANTIDADE_COLUNAS_PADRAO, interacao);
	}

	public Cinema(int quantidadeColunas, int quantidadeFileiras, Interacao interacao){
		if (quantidadeFileiras > QUANTIDADE_FILEIRAS_MAXIMA) {
			interacao.info("Número máximo de fileiras excede o limite. Cinema criado com 26 fileiras.");
			quantidadeFileiras = QUANTIDADE_FILEIRAS_MAXIMA;
		}

		this.interacao = interacao;
		this.quantidadeFileiras = quantidadeFileiras;
		this.quantidadeColunas = quantidadeColunas;
		posicoes = new Posicao[quantidadeFileiras][quantidadeColunas];

		inicializarPosicoes();
	}

	private void inicializarPosicoes(){
		for (int i = 0; i < quantidadeFileiras; i++){
			for (int j = 0; j < quantidadeColunas; j++){
				var posicaoVertical = (char) (i + PRIMEIRA_POSICAO_ASCII);

				posicoes[i][j] = new Posicao(j + 1, posicaoVertical);
			}
		}
	}

	public boolean ocupar(char fila, int coluna){
		return alterarStatus(coluna, fila, StatusPosicao.OCUPADO);
	}

	public boolean reservar(char fila, int coluna){
		return reservar(fila - PRIMEIRA_POSICAO_ASCII, coluna);
	}

	public boolean reservar(int fila, int coluna){
		return alterarStatus(coluna, fila, StatusPosicao.RESERVADO);
	}

	private boolean alterarStatus(int coluna, char fila, StatusPosicao statusPosicao) {
		return alterarStatus(coluna, fila - PRIMEIRA_POSICAO_ASCII, statusPosicao);
	}

	private boolean alterarStatus(int coluna, int fila, StatusPosicao statusPosicao) {
		var posicao = buscarPosicao(coluna - 1, fila);

		if (!StatusPosicao.LIVRE.equals(posicao.getStatusPosicao())) {
			return false;
		}

		posicao.setStatusPosicao(statusPosicao);
		return true;
	}

	public boolean cancelarReserva(char fila, int coluna){
		var posicao = buscarPosicao(coluna - 1, fila);

		if (!StatusPosicao.RESERVADO.equals(posicao.getStatusPosicao())) {
			return false;
		}

		posicao.setStatusPosicao(StatusPosicao.LIVRE);
		return true;
	}

	private Posicao buscarPosicao(int x, int y){
		if (x > quantidadeColunas || x < 0) {
			throw new NegocioExcecao("Fila inválida");
		}

		if (y > quantidadeFileiras || y < 0){
			throw new NegocioExcecao("Coluna inválida");
		}

		return posicoes[y][x];
	}

	public Posicao[][] getPosicoes() {
		return posicoes;
	}

	public int getQuantidadeFileiras() {
		return quantidadeFileiras;
	}

	public int getQuantidadeColunas() {
		return quantidadeColunas;
	}
}
