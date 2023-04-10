package br.com.gabrielmusskopf.calculadorasalario;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.gabrielmusskopf.calculadorasalario.funcionario.Funcionario;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioAssalariado;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioComissionado;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioComissionadoBaseSalario;
import br.com.gabrielmusskopf.calculadorasalario.funcionario.FuncionarioHorista;

class FolhaPagamentoTest {

	private Empresa empresa;
	private FuncionarioAssalariado assalariado;
	private FuncionarioHorista horista;
	private FuncionarioComissionado comissionado;
	private FuncionarioComissionado comissionadoComBase;

	@BeforeEach
	void setUp() {
		empresa = new Empresa();

		horista = new FuncionarioHorista("Maria", 30);
		horista.setHorasSemanaisTrabalhadas(20);

		comissionado = new FuncionarioComissionado("João", 15.0);
		comissionado.setQuantidadeVendas(5);

		comissionadoComBase= new FuncionarioComissionadoBaseSalario("Marcos", 1000.0, 10.0);
		comissionadoComBase.setQuantidadeVendas(2);

		assalariado = new FuncionarioAssalariado("Gabriel", 1000.0);

		empresa.adicionarFuncionarios( assalariado, horista, comissionado, comissionadoComBase);
	}

	@Test
	void deveCalcularPagamentoPorFuncionario_emUmaSemana() {
		var folha = new FolhaPagamento();

		var resposta = folha.totalPorFuncionario(empresa);

		assertEquals(4, resposta.size());
		assertEquals(1020.0, (double) resposta.get(comissionadoComBase));
		assertEquals(1000.0, (double) resposta.get(assalariado));
		assertEquals(600.0, (double) resposta.get(horista));
		assertEquals(75.0, (double) resposta.get(comissionado));
	}

	@Test
	void deveCalcularPagamentoPorFuncionario_emDuasSemanas() {
		var folha = new FolhaPagamento(2);

		var resposta = folha.totalPorFuncionario(empresa);

		assertEquals(4, resposta.size());
		assertEquals(2040.0, (double) resposta.get(comissionadoComBase));
		assertEquals(2000.0, (double) resposta.get(assalariado));
		assertEquals(1200.0, (double) resposta.get(horista));
		assertEquals(150.0, (double) resposta.get(comissionado));
	}

	@Test
	void deveCalcularPagamentoPorEmpresa_emUmaSemana() {
		var folha = new FolhaPagamento();

		var resposta = folha.totalPorEmpresa(empresa);

		assertEquals(2695, resposta);
	}

	@Test
	void deveCalcularPagamentoPorEmpresa_emDuasSemanas() {
		var folha = new FolhaPagamento(2);

		var resposta = folha.totalPorEmpresa(empresa);

		assertEquals(2 * 2695, resposta);
	}

}