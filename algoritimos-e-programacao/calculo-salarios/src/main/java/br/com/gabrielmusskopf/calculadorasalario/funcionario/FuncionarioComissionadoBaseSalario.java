package br.com.gabrielmusskopf.calculadorasalario.funcionario;

public class FuncionarioComissionadoBaseSalario extends FuncionarioComissionado {

	private double salarioBase;

	public FuncionarioComissionadoBaseSalario(String nome, double salarioBase, double comissao) {
		super(nome, comissao);
		this.salarioBase = salarioBase;
	}

	@Override
	public Double calcularSalario() {
		return salarioBase + super.calcularSalario();
	}

}
