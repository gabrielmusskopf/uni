package br.com.gabrielgmusskopf.cinema.opcao;

public enum TipoOpcao {

	RESERVAR(1),
	RESERVAR_MULTIPLOS(2),
	RESERVAR_MULTIPLOS_COLUNA_OU_FILA(3),
	OCUPAR(4),
	CANCELAR_RESERVA(5),
	EXIBIR_POSICOES(6),
	PARAR(7);

	private final int numero;

	TipoOpcao(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

}
