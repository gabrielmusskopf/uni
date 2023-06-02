package br.com.gabrielgmusskopf.unisinos.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Estoque implements Dominio {

    private final UUID id;
    private final Map<String, Integer> ingredientes;

    public Estoque() {
        id = UUID.randomUUID();
        ingredientes = new HashMap<>();
    }

    private Estoque(String id, Map<String, Integer> ingredientes) {
        this.id = UUID.fromString(id);
        this.ingredientes = ingredientes;
    }

    public static Estoque recuperar(String id, Map<String, Integer> ingredientes) {
        return new Estoque(id, ingredientes);
    }

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
        return i.stream().allMatch(ing -> ingredientes.containsKey(ing) && ingredientes.get(ing) > 0);
    }

    public void retirar(List<String> paraRemover) {
        paraRemover.forEach(i ->
            ingredientes.computeIfPresent(i, (ing, qtd) -> --qtd)
        );
    }

    public Map<String, Integer> getIngredientes() {
        return ingredientes;
    }

    public String getId() {
        return id.toString();
    }
}
