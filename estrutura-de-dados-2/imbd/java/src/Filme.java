import java.util.List;

public class Filme {

    private final String nome;
    private final String codigo;
    private final int ano;
    private List<Ator> principaisAtores;
    private final TipoGravacao tipoGravacao;

    public Filme(String nome, String codigo, int ano, TipoGravacao tipoGravacao) {
        this.nome = nome;
        this.codigo = codigo;
        this.ano = ano;
        this.tipoGravacao = tipoGravacao;
    }

    public boolean adicionarAtores(Ator... atores){
       return adicionarAtores(List.of(atores));
    }

    public boolean adicionarAtores(List<Ator> atores){
        principaisAtores = atores;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getAno() {
        return ano;
    }

    public List<Ator> getPrincipaisAtores() {
        return principaisAtores;
    }

    public void setPrincipaisAtores(List<Ator> principaisAtores) {
        this.principaisAtores = principaisAtores;
    }

    public TipoGravacao getTipoGravacao() {
        return tipoGravacao;
    }
}
