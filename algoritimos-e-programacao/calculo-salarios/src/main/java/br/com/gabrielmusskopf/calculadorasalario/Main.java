package br.com.gabrielmusskopf.calculadorasalario;

import java.util.Scanner;

import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioAssalariado;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioComissionado;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioComissionadoBaseSalario;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioHorista;

public class Main {

	public static void main(String[] args) {

		testeFuncionarios();

		var empresa = new Empresa();

		var h1 = new FuncionarioHorista("Maria", 30);
		h1.setHorasSemanaisTrabalhadas(20);

		var h2 = new FuncionarioHorista("Josefa", 40);
		h2.setHorasSemanaisTrabalhadas(100);

		var c1 = new FuncionarioComissionado("João", 15.0);
		c1.setQuantidadeVendas(5);

		var c2 = new FuncionarioComissionadoBaseSalario("Marcos", 1000.0, 10.0);
		c2.setQuantidadeVendas(2);

		empresa.adicionarFuncionarios(
				new FuncionarioAssalariado("Gabriel", 1000.0),
				new FuncionarioAssalariado("Júnior", 1200.0),
				h1, h2, c1, c2
		);

		var scanner = new Scanner(System.in);
		System.out.println("Número de semanas para calcular a folha:");

		var numeroSemanas  = scanner.nextInt();
		var folha = new FolhaPagamento(numeroSemanas);

		System.out.printf("Gasto por funcionario em %d semana%s:\n", numeroSemanas, adicionarPlural(numeroSemanas));
		folha.totalPorFuncionario(empresa)
				.forEach((funcionario, total) -> System.out.printf("%s\t-\tR$%.2f\n", funcionario.getNome(), total));

		System.out.printf("\nGasto total da empresa em %d semana%s:\n", numeroSemanas, adicionarPlural(numeroSemanas));
		System.out.printf("R$%.2f", folha.totalPorEmpresa(empresa));

        scanner.close();
	}

	private static String adicionarPlural(int numeroSemanas) {
		return numeroSemanas > 1 ? "s" : "";
	}

	private static void testeFuncionarios() {
		var calculadora = new CalculadoraSalario();

		System.out.println("Funcionário assalariado que recebe R$1000,00 semanais");
		var assalariado = new FuncionarioAssalariado("Gabriel", 1000);
		System.out.printf("Resultado: R$%.2f\n\n", calculadora.calcularSemana(assalariado));


		System.out.println("Funcionário comissionado que recebe 10% de comissão e 2 vendas");
		var comissionado = new FuncionarioComissionado("João", 10.0);
		comissionado.setQuantidadeVendas(2);
		System.out.printf("Resultado: R$%.2f\n\n", calculadora.calcularSemana(comissionado));

		System.out.println("Funcionário comissionado com saĺario base que recebe 10% de comissão, R$1000,00 de saĺário semanal e 2 vendas");
		var comissionadoBaseSalario = new FuncionarioComissionadoBaseSalario("Pedro", 1000, 10.0);
		comissionadoBaseSalario.setQuantidadeVendas(2);
		System.out.printf("Resultado: R$%.2f\n\n", calculadora.calcularSemana(comissionadoBaseSalario));

		System.out.println("Funcionário horísta que recebe R$10,00 por hora, com 30 horas trabalhadas");
		var horista = new FuncionarioHorista("Maria", 10.0);
		horista.setHorasSemanaisTrabalhadas(30);
		System.out.printf("Resultado: R$%.2f\n\n", calculadora.calcularSemana(horista));

		System.out.println("Funcionário horísta que recebe R$10,00 por hora, com 50 horas trabalhadas");
		horista.setHorasSemanaisTrabalhadas(50);
		System.out.printf("Resultado: R$%.2f\n\n", calculadora.calcularSemana(horista));
	}

}
