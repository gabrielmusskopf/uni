package br.com.gabrielmusskopf.calculadorasalario;

import java.util.Map;
import java.util.stream.Collectors;

import br.com.gabrielmusskopf.calculadorasalario.funcionario.Funcionario;

public class FolhaPagamento {

	private static final CalculadoraSalario CALCULADORA_SALARIO = new CalculadoraSalario();
	private static final int SEMANAS_TRABALHADAS_PADRAO = 1;
	private int semanasTrabalhadas;

	public FolhaPagamento() {
		this(SEMANAS_TRABALHADAS_PADRAO);
	}

	public FolhaPagamento(int semanasTrabalhadas) {
		this.semanasTrabalhadas = semanasTrabalhadas;
	}

	public Map<Funcionario, Double> totalPorFuncionario(Empresa empresa) {
		return empresa.getFuncionarios()
				.stream()
				.collect(Collectors.toMap(funcionario -> funcionario, funcionario -> CALCULADORA_SALARIO.calcular(funcionario, semanasTrabalhadas)));
	}

	public double totalPorEmpresa(Empresa empresa) {
		return empresa.getFuncionarios()
				.stream()
				.map(CALCULADORA_SALARIO::calcularSemana)
				.reduce(Double::sum)
				.map(valor -> valor * semanasTrabalhadas)
				.orElse(0D);
	}

}
