package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import br.com.gabrielgmusskopf.unisinos.dominio.Dominio;
import br.com.gabrielgmusskopf.unisinos.dominio.pedido.EstadoPedido;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class RepositorioArquivos<T> {

    private static final String RESOURCES = "src/main/resources/";

    protected abstract String caminhoData();

    protected abstract List<T> buscarTodos();

    protected void carregar(List<T> s) {
        var arquivo = new File(RESOURCES + caminhoData());
        if (!arquivo.exists()) {
            return;
        }
        var csv = caminhoData(); //validar csv

        try (BufferedReader reader = new BufferedReader(new FileReader(RESOURCES + csv))) {
            String line;
            reader.readLine(); //cabeçalho

            while ((line = reader.readLine()) != null) {
                recuperarElemento(line.split(","));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void recuperarElemento(String[] valores);

    protected <T extends Dominio> T buscarElemento(String id, List<T> s) {
        return s.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    protected void escreverAoFinal(String cabecalho) {
        ContextoRepositorio.adicionarThread(new Thread(() -> {
            try (PrintWriter writer = new PrintWriter(RESOURCES + caminhoData())) {
                writer.println(cabecalho);
                buscarTodos().forEach(e -> writer.println(formatarDados(e)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }));

    }

    private String formatarDados(T e) {
        return escreverValores(e).stream()
                .map(c -> {
                    if (c instanceof Dominio d) {
                        return d.getId();
                    }
                    if (c instanceof Collection<?> col) {
                        return new StringBuilder("[")
                                .append(col.stream()
                                        .filter(Objects::nonNull)
                                        .map(x -> x instanceof Dominio dom ? dom.getId() : x.toString())
                                        .collect(Collectors.joining(",")))
                                .append("]");
                    }
                    if (c instanceof Map<?,?> map) {
                        return new StringBuilder("[")
                                .append(map.entrySet().stream()
                                        .map(entry -> String.format("{%s:%s}", entry.getKey(), entry.getValue()))
                                        .collect(Collectors.joining(",")))
                                .append("]");
                    }
                    if (c instanceof EstadoPedido estado) {
                        //TODO implementar mapeamento de EstadoPedido
                    }
                    return c.toString();
                })
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    protected abstract List<?> escreverValores(T t);

    /**
     * @param m Formato esperado: [{chave:valor}, {chave:valor}]
     * @return
     */
    protected <K,V> Map<K,V> lerMap(String m, Function<String, K> keyMapper, Function<String, V> valueMapper) {
        return Arrays.stream(m.substring(1, m.length() - 1)
                        .split(","))
                .map(p -> p.substring(1, p.length() - 1))
                .map(p -> p.split(":"))
                .collect(Collectors.toMap(p -> keyMapper.apply(p[0]), p -> valueMapper.apply(p[1])));
    }

    protected List<String> stringToList(String[] valores, int indice) {
        return Arrays.stream(valores[indice]
                        .substring(1, valores.length - 1)
                        .split(","))
                .toList();
    }

}
