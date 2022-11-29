package br.com.gabriel.poker.etapa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.Mao;
import br.com.gabriel.poker.PontuarSequencia;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.jogador.Jogador;

public class Resultados implements Etapa {

	private boolean isCompleta;
	private Comunicador comunicador;

	public Resultados (Comunicador comunicador) {
		this.comunicador = comunicador;
	}

	@Override
	public boolean isCompelta () {
		return isCompleta;
	}

	@Override
	public void executar (Jogo jogo) {

		var jogadoresComMaiorPeso = jogo.getJogadoresParticipandoNaRodada().stream()
				.collect(Collectors.groupingBy(jogador -> PontuarSequencia.pontuar(jogador.getCartas())))
				.entrySet().stream()
				.max(Comparator.comparingInt(e -> e.getKey().getPeso()))
				.filter(entry -> !Mao.NENHUM.equals(entry.getKey()))
				.stream()
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		var cartasPorJogador = jogo.getJogadoresParticipandoNaRodada().stream()
				.collect(Collectors.toMap(jogador -> jogador, Jogador::getCartas));

		comunicador.comunicar("\nCartas dos participantes:");
		cartasPorJogador.forEach((jogador, cartas) -> comunicador.comunicar("{0}: {1}", jogador.getNome(), jogador.visualizarCartas()));

		if (jogadoresComMaiorPeso.isEmpty()) {
			var jogadorComCartaMaior = PontuarSequencia.buscarCartaMaisAlta(cartasPorJogador);
			comunicador.comunicar(jogadorComCartaMaior.getNome() + " ganhou pela maior carta!"); //melhorar
			exibirVencedores(List.of(jogadorComCartaMaior));

		} else {
			var vencedores = jogadoresComMaiorPeso.entrySet().stream()
					.max(Comparator.comparingInt(e -> e.getKey().getPeso()))
					.map(Map.Entry::getValue)
					.orElse(Collections.emptyList());

			jogo.distribuirPote(vencedores);
			var jogoVencedor = jogadoresComMaiorPeso.keySet().stream()
					.findFirst()
					.map(Mao::getNome)
					.orElse(null);

			if (vencedores.size() != 1)
				comunicador.comunicar(Cor.VERDE, "\nHouve um empate entre os jogadores {0}.\nJogo: {1}\n", exibirVencedores(vencedores), jogoVencedor);
			else
				comunicador.comunicar(Cor.VERDE, "\n{0} venceu.\nJogo: {1}\n", exibirVencedores(vencedores), jogoVencedor);

		}

		jogo.eliminarJogadoresSemFicha()
				.forEach(jogador -> comunicador.comunicar(Cor.VERMELHO, "{0} ficou sem fichas e foi eliminado.", jogador.getNome()));

		isCompleta = Boolean.TRUE;
	}

	private String exibirVencedores (List<Jogador> vencedores) {
		var builder = new StringBuilder();

		if (vencedores.size() == 1)
			builder.append(vencedores.stream()
					.map(Jogador::getNome)
					.findFirst()
					.orElse(null));

		for (int i = 0; i < vencedores.size() - 1; i++) {
			var jogadorAtual = vencedores.get(i);
			if (i == vencedores.size() - 2) {
				var proximoJogador = vencedores.get(i + 1);
				builder.append(jogadorAtual.getNome()).append(" e ").append(proximoJogador.getNome());
				break;
			}

			builder.append(jogadorAtual.getNome()).append(", ");
		}

		return builder.toString();
	}

	@Override
	public String getNome () {
		return "Resultados";
	}

}
