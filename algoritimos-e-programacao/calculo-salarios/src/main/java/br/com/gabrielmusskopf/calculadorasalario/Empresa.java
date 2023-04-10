package br.com.gabrielmusskopf.calculadorasalario;

import java.util.ArrayList;
import java.util.List;

import br.com.gabrielmusskopf.calculadorasalario.funcionario.Funcionario;

public class Empresa {

	private List<Funcionario> funcionarios = new ArrayList<>();

	public void adicionarFuncionarios(Funcionario... funcionarios){
		this.funcionarios.addAll(List.of(funcionarios));
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
}
