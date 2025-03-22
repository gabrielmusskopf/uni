package br.com.gabrielmusskopf.calculadorasalario.funcionario;

public class FuncionarioComissionado implements Funcionario {

	private final String nome;
	private final double comissao;
	private int quantidadeVendas;

	public FuncionarioComissionado(String nome, double comissao) {
		this.nome = nome;
		this.comissao = comissao;
	}

	@Override
	public Double calcularSalario() {
		return quantidadeVendas * comissao;
	}

	@Override
	public String getNome() {
		return nome;
	}

	public void setQuantidadeVendas(int quantidadeVendas) {
		this.quantidadeVendas = quantidadeVendas;
	}

}
