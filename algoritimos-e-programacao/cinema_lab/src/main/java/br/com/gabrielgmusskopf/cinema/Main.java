package br.com.gabrielgmusskopf.cinema;

import java.util.List;

import br.com.gabrielgmusskopf.cinema.interacao.Terminal;
import br.com.gabrielgmusskopf.cinema.opcao.CancelarReservaOpcao;
import br.com.gabrielgmusskopf.cinema.opcao.ExibirOpcoesOpcao;
import br.com.gabrielgmusskopf.cinema.opcao.OcuparOpcao;
import br.com.gabrielgmusskopf.cinema.opcao.Opcao;
import br.com.gabrielgmusskopf.cinema.opcao.ReservarMultiplosColunaOuFileOpcao;
import br.com.gabrielgmusskopf.cinema.opcao.ReservarMultiplosOpcao;
import br.com.gabrielgmusskopf.cinema.opcao.ReservarOpcao;

public class Main {

	public static void main(String[] args) {
		final var terminal = new Terminal();
		final var cinema = new Cinema(10, 30, terminal);
		final var grafico = new Grafico(terminal);

		final List<Opcao> opcoes = List.of(
				new ReservarOpcao(1, cinema, terminal, grafico),
				new CancelarReservaOpcao(2, cinema, terminal, grafico),
				new OcuparOpcao(3, cinema, terminal, grafico),
				new ExibirOpcoesOpcao(4, cinema, terminal, grafico),
				new ReservarMultiplosOpcao(5, cinema, terminal, grafico),
				new ReservarMultiplosColunaOuFileOpcao(6, cinema, terminal, grafico)
		);

		final var menu = new Menu(terminal, opcoes);

		while (menu.deveExibir()){
			menu.exibir();
		}
	}
}
