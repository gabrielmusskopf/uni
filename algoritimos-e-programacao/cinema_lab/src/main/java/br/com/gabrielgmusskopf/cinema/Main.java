package br.com.gabrielgmusskopf.cinema;

import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.CANCELAR_RESERVA;
import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.EXIBIR_POSICOES;
import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.OCUPAR;
import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.PARAR;
import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.RESERVAR;
import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.RESERVAR_MULTIPLOS;
import static br.com.gabrielgmusskopf.cinema.opcao.TipoOpcao.RESERVAR_MULTIPLOS_COLUNA_OU_FILA;

import br.com.gabrielgmusskopf.cinema.opcao.OpcoesFactory;
import br.com.gabrielgmusskopf.cinema.ui.TipoUI;
import br.com.gabrielgmusskopf.cinema.ui.UIFactory;

class Main {

	public static void main(String[] args) {
        Aplicacao.iniciar();
		Contexto.definirUI(UIFactory.construir(TipoUI.CONSOLE));

		final var grafico = new Grafico();
		final var cinema = new Cinema(10, 30);
		final var opcoesFactory = new OpcoesFactory(cinema, grafico);

		final var opcoes = opcoesFactory.construir(
				RESERVAR,
				RESERVAR_MULTIPLOS,
				RESERVAR_MULTIPLOS_COLUNA_OU_FILA,
				OCUPAR,
				CANCELAR_RESERVA,
				EXIBIR_POSICOES,
                PARAR
		);

		final var menu = new Menu(opcoes);

		while (Aplicacao.isExecutando()){
			menu.exibir();
		}
	}
}
