package br.com.gabrielgmusskopf.unisinos.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {

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
        return i.stream()
                .allMatch(ingrediente -> ingredientes.containsKey(ingrediente));
    }

}