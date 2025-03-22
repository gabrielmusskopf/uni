package br.com.gabrielgmusskopf.cinema.excecao;

public class OpcaoInexistenteExcecao extends NegocioExcecao {

	public OpcaoInexistenteExcecao(String mensagem) {
		super(mensagem);
	}

	public OpcaoInexistenteExcecao() {
		super("Opção inexistente!");
	}

}
