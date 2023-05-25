package br.com.gabrielgmusskopf.unisinos.infra.repositorio;

import java.io.*;
import java.util.List;

public abstract class ArquivoRepositorio {

    private static final String RESOURCES = "src/main/resources/";

    protected abstract String caminhoData();

    protected abstract List<?> buscarTodos();

    protected <T> void carregar(List<T> s) {
        var arquivo = new File(RESOURCES + caminhoData());
        if (!arquivo.exists()) {
            return;
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/" + caminhoData()))) {
            s.addAll((List<T>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void escreverAoFinal() {
        ContextoRepositorio.adicionarThread(new Thread(() -> {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/" + caminhoData()))) {
                outputStream.writeObject(buscarTodos());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        /*
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/" + caminhoData()))) {
                outputStream.writeObject(buscarTodos());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
         */
    }


}
