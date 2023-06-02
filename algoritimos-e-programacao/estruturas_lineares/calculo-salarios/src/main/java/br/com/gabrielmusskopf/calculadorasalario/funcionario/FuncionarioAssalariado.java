package br.com.gabrielmusskopf.calculadorasalario.funcionario;

public class FuncionarioAssalariado implements Funcionario {

	private final double salarioSemanal;
	private final String nome;

	public FuncionarioAssalariado(String nome, double salarioSemanal) {
		this.salarioSemanal = salarioSemanal;
		this.nome = nome;
	}

	@Override
	public Double calcularSalario() {
		return salarioSemanal;
	}

	@Override
	public String getNome() {
		return nome;
	}

}
