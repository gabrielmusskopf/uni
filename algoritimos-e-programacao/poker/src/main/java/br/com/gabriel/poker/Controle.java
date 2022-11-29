package br.com.gabriel.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.etapa.ApostaInicial;
import br.com.gabriel.poker.etapa.Apostas;
import br.com.gabriel.poker.etapa.DistribuicaoCartas;
import br.com.gabriel.poker.etapa.Etapa;
import br.com.gabriel.poker.etapa.Resultados;
import br.com.gabriel.poker.etapa.TrocaDeCartas;
import br.com.gabriel.poker.jogador.Jogador;
import lombok.Getter;

public class Controle {

	private final Configuracoes configuracoes = new Configuracoes();
	private Jogo jogo;
	private List<Etapa> etapas;
	@Getter
	private Etapa etapaAtual;
	private Comunicador comunicador;
	private int rodada = 1;
	@Getter
	private boolean acontecendo = Boolean.TRUE;

	public Controle (Consumer<Configuracoes> configuracoesConsumer, Supplier<Comunicador> comunicadorSupplier) {
		configuracoesConsumer.accept(this.configuracoes);
		comunicador = comunicadorSupplier.get();
	}

	public static boolean isPlayer (Jogador jogador) {
		return TipoJogador.USUARIO.equals(jogador.getTipoJogador());
	}

	public List<String> buscarNomesJogadores () {
		comunicador.comunicar(Cor.VERDE, "Digite a quantidade de jogadores (máximo = 5):");
		var scanner = new Scanner(System.in);

		int quantidadeDeJogadores = 6;
		while (quantidadeDeJogadores > 5)
			quantidadeDeJogadores = scanner.nextInt();

		List<String> nomes = new ArrayList<>();
		for (int i = 0; i < quantidadeDeJogadores; i++) {
			comunicador.comunicar("Nome do jogador {0}:", i + 1);
			nomes.add(scanner.next());
		}

		return nomes;
	}

	public void iniciarJogo (List<String> nomesJogadores) {
		jogo = new Jogo(nomesJogadores);
		exibirPosicoes();
		adicionarEtapas();
		etapaAtual = etapas.get(0);
		novaEtapa();
	}

	public void adicionarEtapas () {
		etapas = List.of(new DistribuicaoCartas(comunicador), new ApostaInicial(comunicador), new TrocaDeCartas(comunicador), new Apostas(comunicador), new Resultados(comunicador));
	}

	public void executarEtapa () {
		etapaAtual.executar(jogo);

		if (etapaAtual.isCompelta())
			atualizarEtapa();
	}

	private void atualizarEtapa () {
		var indexEtapaAtual = etapas.indexOf(etapaAtual);

		var isUltimaEtapa = indexEtapaAtual >= etapas.size() - 1;

		if (isVencedor()) {
			final var vencedor = jogo.getJogadoresRestantes().get(0);
			comunicador.comunicar(Cor.VERDE, "O jogador {0} venceu!", vencedor.getNome());
			acontecendo = Boolean.FALSE;
		}

		if (isUltimaEtapa && jogo.getJogadoresRestantes().size() > 1) {
			comunicador.comunicar(Cor.AZUL, "Rodada finalizada.\n");
			aguardarDelay(configuracoes.getSegundosDelayAposRodada());
			rodada++;
			jogo.novaRodada();
			adicionarEtapas();
			exibirPosicoes();
		}

		etapaAtual = etapas.get(isUltimaEtapa ? 0 : ++indexEtapaAtual);
		novaEtapa();
	}

	private boolean isVencedor () {
		return jogo.getJogadoresRestantes().size() == 1;
	}

	private void exibirPosicoes () {
		comunicador.comunicar(Cor.AZUL, "Rodada " + rodada + "\nPosições atuais:");

		jogo.getJogadoresRestantes().stream()
				.map(Jogador::informacoes)
				.forEach(comunicador::comunicar);

	}

	private void novaEtapa () {
		comunicador.comunicar(Cor.VERDE, "\n" + etapaAtual.getNome());
		aguardarDelay(configuracoes.getSegundosDelayEntreRodadas());
	}

	private void aguardarDelay (long delay) {
		try {
			Thread.sleep(delay * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
