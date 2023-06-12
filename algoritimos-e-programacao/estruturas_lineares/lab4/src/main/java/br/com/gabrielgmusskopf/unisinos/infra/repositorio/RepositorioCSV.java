package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import br.com.gabrielgmusskopf.unisinos.dominio.Dominio;
import com.sun.source.tree.IfTree;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class RepositorioCSV<T> {

    private static final String RESOURCES = "src/main/resources/";
    private static final String INICIO_COLECAO = "[";
    private static final String FIM_COLECAO = "]";
    private static final String SEPARADOR_ELEMENTO = ";";
    private static final String SEPARADOR_CHAVE_VALOR = ":";
    private static final String SEPARADOR_CSV = ",";

    protected void inicializar() {
        carregar();
        escreverAoFinal();
    }

    protected abstract String cabecalho();

    protected abstract String caminhoData();

    protected abstract List<T> buscarTodos();

    protected void carregar() {
        var arquivo = new File(RESOURCES + caminhoData());
        if (!arquivo.exists()) {
            return;
        }
        var csv = caminhoData();
        if (arquivoNaoCsv(csv)) {
            throw new IllegalArgumentException("O arquivo informado para o armazenamento em arquivos deve ser do formato CSV");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES + csv))) {
            String line;
            reader.readLine(); // cabeçalho

            while ((line = reader.readLine()) != null) {
                recuperarElemento(line.split(SEPARADOR_CSV));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean arquivoNaoCsv(String csv) {
        return !"csv".equals(csv.substring(csv.lastIndexOf('.') + 1 ));
    }

    protected abstract void recuperarElemento(String[] valores);

    protected void escreverAoFinal() {
        ContextoRepositorio.adicionarThread(new Thread(() -> {
            try (FileWriter writer = new FileWriter(RESOURCES + caminhoData())) {
                writer.write(cabecalho());
                writer.write(System.lineSeparator());
                for (T t: buscarTodos()) {
                    writer.write(formatarDados(t));
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, caminhoData()));

    }

    private String formatarDados(T e) {
        return escreverValores(e).stream()
                .map(c -> {
                    if (c instanceof Dominio d) return d.getId();
                    if (c instanceof Collection<?> col) return escreverCollection(col);
                    if (c instanceof Map<?, ?> map) return escreverMap(map);
                    return c.toString();
                })
                .map(Object::toString)
                .collect(Collectors.joining(SEPARADOR_CSV));
    }

    protected abstract List<?> escreverValores(T t);

    private StringBuilder escreverCollection(Collection<?> col) {
        return new StringBuilder(INICIO_COLECAO)
                .append(col.stream()
                        .filter(Objects::nonNull)
                        .map(x -> x instanceof Dominio dom ? dom.getId() : x.toString())
                        .collect(Collectors.joining(SEPARADOR_ELEMENTO)))
                .append(FIM_COLECAO);
    }

    private StringBuilder escreverMap(Map<?, ?> map) {
        return new StringBuilder(INICIO_COLECAO)
                .append(map.entrySet().stream()
                        .map(entry -> String.format("{%s%s%s}", entry.getKey(), SEPARADOR_CHAVE_VALOR, entry.getValue()))
                        .collect(Collectors.joining(SEPARADOR_ELEMENTO)))
                .append(FIM_COLECAO);
    }

    /**
     *  Método responsável pela leitura de Map armazenado em CSV
     * @param m String contendo o elemento a ser transformado. Deve estar no formato  [{chave:valor}, {chave:valor}]
     * @param keyMapper Function<String, V> Conversão do texto lido para o tipo de valor do Map
     * @param valueMapper Function<String, V> Conversão do texto lido para o tipo de chave do Map
     * @return Map<K,V> Sendo os tipos obtidos através das functions
     * @param <K> Tipo da chave
     * @param <V> Tipo do valr
     */
    protected <K,V> Map<K,V> lerMap(String m, Function<String, K> keyMapper, Function<String, V> valueMapper) {
        if (m.length() < 3) {
            return new HashMap<>();
        }
        return Arrays.stream(m.substring(1, m.length() - 1)
                        .split(SEPARADOR_ELEMENTO))
                .map(p -> p.substring(1, p.length() - 1))
                .map(p -> p.split(SEPARADOR_CHAVE_VALOR))
                .collect(Collectors.toMap(p -> keyMapper.apply(p[0]), p -> valueMapper.apply(p[1])));
    }

    /**
     *  Método responsável pela leitura de List armazenada em CSV
     * @param elemento String Elemento CSV. Deve estar no formato [e1, e2]
     * @return List<String> contendo elementos
     */
    protected List<String> lerList(String elemento) {
       return lerList(elemento, String::toString);
    }

    /**
     *  Método responsável pela leitura de List armazenada em CSV
     * @param elemento String Elemento CSV. Deve estar no formato [e1, e2]
     * @param conversao Function<String, T> Função para converter String para o tipo da lista
     * @return List<String> contendo elementos
     */
    protected <T> List<T> lerList(String elemento, Function<String, T> conversao) {
        if (elemento.length() < 3) {
            return Collections.emptyList();
        }
        return Arrays.stream(elemento.substring(1, elemento.length() - 1).split(SEPARADOR_ELEMENTO))
                .map(conversao)
                .toList();
    }

}
