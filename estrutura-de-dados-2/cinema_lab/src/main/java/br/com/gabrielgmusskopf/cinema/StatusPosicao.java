package br.com.gabrielgmusskopf.cinema;

enum StatusPosicao {
	LIVRE(" "),
	OCUPADO("O"),
	RESERVADO("R"),
	;

	private final String representacao;

	StatusPosicao(String representacao) {
		this.representacao = representacao;
	}

	public String getRepresentacao() {
		return representacao;
	}

}
