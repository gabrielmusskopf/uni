package br.com.gabriel.poker.etapa;

import java.util.Map;

import br.com.gabriel.poker.Jogo;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.jogador.Jogador;
import br.com.gabriel.poker.util.AcaoJogador;
import br.com.gabriel.poker.util.Aleatorio;
import br.com.gabriel.poker.util.Console;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CorrecaoApostas implements Etapa {

	private final Comunicador comunicador;
	private boolean isCompelta;

	@Override
	public boolean isCompelta () {
		return isCompelta;
	}

	@Override
	public void executar (Jogo jogo) {
		if (!jogo.isDeveExecutarRodadas()) return;

		final var jogadorescomApostaMenor = jogo.getApostas().entrySet().stream()
				.filter(entry -> entry.getValue() < jogo.getApostaDaRodada() &&
						entry.getKey().getFichas() - jogo.getApostaDaRodada() > 0)
				.map(Map.Entry::getKey)
				.toList();

		jogadorescomApostaMenor.forEach(jogador -> {
			final int opcao = AcaoJogador.executar(jogador)
					.casoPlayer(() -> comunicador.comunicar("{0} deseja cobir a aposta final de {1}?\n1 - Sim\n2 - Não", jogador.getNome(), jogo.getApostaDaRodada()))
					.casoPlayer(() -> Console.buscarValor(1, 2))
					.casoNPC(() -> Aleatorio.buscarEntre(1, 2));

			switch (opcao) {
				case 1 -> cobrirValor(jogador, jogo);
				case 2 -> desistirDoJogo(jogador, jogo);
				default -> comunicador.comunicar(Cor.VERMELHO, "Opção inválida");
			}
		});

		if (jogo.getApostas().size() == 1) {
			var vencedor = jogo.getApostas().keySet().stream()
					.findFirst()
					.orElse(null);

			jogo.terminarRodada(vencedor);
		}

		isCompelta = Boolean.TRUE;
	}

	private void cobrirValor (Jogador jogador, Jogo jogo) {
		comunicador.comunicar("{0} cobriu a aposta e parmanece no jogo", jogador.getNome());
		jogo.apostar(jogador, jogo.getApostaDaRodada() - jogador.getFichas());
	}

	private void desistirDoJogo (Jogador jogador, Jogo jogo) {
		comunicador.comunicar("{0} não cobiu a aposta, então não participa mais da rodada", jogador.getNome());
		jogo.removerDaRodada(jogador);
	}

	@Override
	public String getNome () {
		return "Correção das apostas";
	}

}
