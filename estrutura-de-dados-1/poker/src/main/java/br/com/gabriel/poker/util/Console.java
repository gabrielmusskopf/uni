package br.com.gabriel.poker.util;

import br.com.gabriel.poker.Configuracoes;
import lombok.Getter;

@Getter
public class Console {

	public static Integer buscarValor (Integer min, Integer max) {
		var opcao = Integer.MIN_VALUE;
		while (opcao <= min || opcao > max)
			opcao = Configuracoes.SCANNER.nextInt();

		return opcao;
	}

}
