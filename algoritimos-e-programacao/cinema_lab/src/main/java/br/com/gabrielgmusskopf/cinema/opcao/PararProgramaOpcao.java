package br.com.gabrielgmusskopf.cinema.opcao;

import br.com.gabrielgmusskopf.cinema.Aplicacao;
import br.com.gabrielgmusskopf.cinema.Contexto;

class PararProgramaOpcao implements Opcao {

    private final int numero;

	public PararProgramaOpcao(int numero) {
        this.numero = numero;
	}

	@Override
	public void executar() {
        Contexto.getUI().info("Obrigado por utilizar \\0/");
        Aplicacao.finalizar();
	}

	@Override
	public String getFrase() {
		return "Terminar";
	}

    @Override
    public int getNumero() {
        return numero;
    }
}
