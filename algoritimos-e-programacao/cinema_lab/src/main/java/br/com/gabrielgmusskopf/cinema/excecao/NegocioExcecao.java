package br.com.gabrielgmusskopf.cinema.excecao;

public class NegocioExcecao extends RuntimeException{

	private final String mensagem;

	public NegocioExcecao(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
