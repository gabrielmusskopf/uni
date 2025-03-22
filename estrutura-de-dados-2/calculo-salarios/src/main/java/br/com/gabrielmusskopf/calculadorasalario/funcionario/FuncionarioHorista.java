package br.com.gabrielmusskopf.calculadorasalario.funcionario;

public class FuncionarioHorista implements Funcionario {

	private static final int QUARENTA_HORAS = 40;
	private static final double TRES_MEIOS = 1.5;

	private final String nome;
	private final double salarioPorHora;
	private int horasSemanaisTrabalhadas;

	public FuncionarioHorista(String nome, double salarioPorHora) {
		this.nome = nome;
		this.salarioPorHora = salarioPorHora;
	}

	@Override
	public Double calcularSalario() {

		if (horasSemanaisTrabalhadas < QUARENTA_HORAS) {
			return salarioPorHora * horasSemanaisTrabalhadas;
		}

		return QUARENTA_HORAS * salarioPorHora + (horasSemanaisTrabalhadas - QUARENTA_HORAS) * salarioPorHora * TRES_MEIOS;
	}

	@Override
	public String getNome() {
		return nome;
	}

	public void setHorasSemanaisTrabalhadas(int horasSemanaisTrabalhadas) {
		this.horasSemanaisTrabalhadas = horasSemanaisTrabalhadas;
	}

}
