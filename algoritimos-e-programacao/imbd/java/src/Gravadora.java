import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Gravadora {

    private final String nome;
    private List<Filme> filmes;

    public Gravadora(String nome){
        this.nome = nome;
    }

    public Gravadora(String nome, List<Filme> filmes) {
        this.nome = nome;
        this.filmes = filmes;
    }

    public List<Filme> buscar(){
        return filmes;
    }

    public List<Filme> buscar(TipoGravacao tipoGravacao){
        if (Objects.isNull(tipoGravacao)) {
            return Collections.emptyList();
        }

        return filmes.stream()
                .filter(filme -> tipoGravacao.equals(filme.getTipoGravacao()))
                .toList();
    }

    public boolean adicionar(Filme filme){
        return TarefaBooleana.executar(() -> filmes.add(filme));
    }

    public boolean adicionar(Filme filme, int indice){
        return TarefaBooleana.executar(() -> filmes.add(indice, filme));
    }

    public boolean remover(Filme filme){
        return TarefaBooleana.executar(() -> filmes.remove(filme));
    }

    public boolean remover(int indice){
        return TarefaBooleana.executar(() -> filmes.remove(indice));
    }

}
