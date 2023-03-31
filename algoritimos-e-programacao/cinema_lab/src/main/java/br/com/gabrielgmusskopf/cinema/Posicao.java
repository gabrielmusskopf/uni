package br.com.gabrielgmusskopf.cinema;

class Posicao {

    private final Character vertical;
    private final Integer horizontal;
	private StatusPosicao statusPosicao;

    public Posicao(Integer x, Character y){
        horizontal = x;
        vertical = y;
		statusPosicao = StatusPosicao.LIVRE;
    }

	public String informacoes(){
		return "[%s]".formatted(statusPosicao.getRepresentacao());
	}

	public StatusPosicao getStatusPosicao() {
		return statusPosicao;
	}

	public void setStatusPosicao(StatusPosicao statusPosicao) {
		this.statusPosicao = statusPosicao;
	}

}
