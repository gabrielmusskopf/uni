package br.com.gabrielgmusskopf.unisinos.dominio;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque implements Serializable {

    private static final long serialVersionUID = 1518914179246622597L;
    private final Map<String, Integer> ingredientes = new HashMap<>();

    public void abastecer(String... novosIngredientes) {
        for (String ingrediente: novosIngredientes) {
            var estoque = ingredientes.get(ingrediente);
            if (estoque != null) {
                ingredientes.put(ingrediente, ++estoque);
                continue;
            }

            ingredientes.put(ingrediente, 1);
        }
    }

    public boolean contem(List<String> i) {
        return i.stream().allMatch(ingredientes::containsKey);
    }

    public void retirar(List<String> paraRemover) {
        paraRemover.forEach(i ->
            ingredientes.computeIfPresent(i, (ing, qtd) -> --qtd)
        );
    }

    public Map<String, Integer> getIngredientes() {
        return ingredientes;
    }

}
