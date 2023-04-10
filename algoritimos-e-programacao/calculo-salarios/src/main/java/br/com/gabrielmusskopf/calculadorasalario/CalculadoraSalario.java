package br.com.gabrielmusskopf.calculadorasalario;

import br.com.gabrielmusskopf.calculadorasalario.funcionario.Funcionario;

public class CalculadoraSalario {

	public Double calcularSemana(Funcionario funcionario){
		return funcionario.calcularSalario();
	}

	public Double calcular(Funcionario funcionario, int numeroSemanas){
		return numeroSemanas * funcionario.calcularSalario();
	}

}
