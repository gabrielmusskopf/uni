package br.com.gabrielgmusskopf.unisinos;

import java.util.List;

public class Focaccia implements Produto {

    @Override
    public String getNome() {
        return "Focaccia";
    }

    @Override
    public Double getValor() {
        return 10.0;
    }

    @Override
    public List<String> getIngredientes() {
        return List.of("Tomate", "Farinha", "Sal");
    }
}
