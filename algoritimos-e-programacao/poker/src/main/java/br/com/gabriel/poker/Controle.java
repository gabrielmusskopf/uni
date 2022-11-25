package br.com.gabriel.poker;

import java.util.List;

import br.com.gabriel.poker.comunicacao.ComunicacaoLog;
import br.com.gabriel.poker.comunicacao.Comunicador;
import br.com.gabriel.poker.comunicacao.Cor;
import br.com.gabriel.poker.etapa.ApostaInicial;
import br.com.gabriel.poker.etapa.Apostas;
import br.com.gabriel.poker.etapa.DistribuicaoCartas;
import br.com.gabriel.poker.etapa.Etapa;
import br.com.gabriel.poker.etapa.TrocaDeCartas;
import lombok.Getter;

public class Controle {

	private Jogo jogo;
	private List<Etapa> etapas;
	@Getter
	private Etapa etapaAtual;
	private Comunicador comunicador;

	public static boolean isPlayer(Jogador jogador) {
		return TipoJogador.USUARIO.equals(jogador.getTipoJogador());
	}

	public void iniciarJogo(int quantidadeJogadores) {
		comunicador = new ComunicacaoLog();
		jogo = new Jogo(quantidadeJogadores);
		exibirPosicoes();
		adicionarEtapas();
		etapaAtual = etapas.get(0);
		novaEtapa();
	}

	public void adicionarEtapas() {
		etapas = List.of(new DistribuicaoCartas(comunicador), new ApostaInicial(comunicador), new TrocaDeCartas(comunicador), new Apostas(comunicador));
	}

	public void executarEtapa() {
		etapaAtual.executar(jogo);

		if (etapaAtual.isCompelta())
			atualizarEtapa();
	}

	private void atualizarEtapa() {
		var indexEtapaAtual = etapas.indexOf(etapaAtual);

		var isUltimaEtapa = indexEtapaAtual >= etapas.size() - 1;

		if (isUltimaEtapa && jogo.getJogadoresRestantes().size() > 1) {
			comunicador.comunicar(Cor.AZUL, "Rodada finalizada.");
			jogo.novaRodada();
			adicionarEtapas();
			exibirPosicoes();
		}

		etapaAtual = etapas.get(isUltimaEtapa ? 0 : ++indexEtapaAtual);
		novaEtapa();
	}

	private void exibirPosicoes() {
		comunicador.comunicar(Cor.AZUL, "Posições atuais:");

		var posicoes = new StringBuilder();
		jogo.getJogadoresRestantes().stream()
				.map(jogador -> "[" + jogador.getPosicao().getDescricao() + "] " + jogador.getNome() + " ")
				.forEach(posicoes::append);

		comunicador.comunicar(posicoes + "\n");
	}

	private void novaEtapa() {
		comunicador.comunicar(Cor.VERDE, "\n" + etapaAtual.getNome());
	}

}
