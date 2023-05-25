package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.dominio.util.IntUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ConsoleAbstrato implements Console {

    protected static final Scanner scanner = new Scanner(System.in);

    protected <T> Map<Integer, T> montarOpcoes(List<T> produtos) {
        return IntStream.rangeClosed(1, produtos.size())
                .boxed()
                .collect(Collectors.toMap(Function.identity(), produtos::get));
    }

    protected <T> List<T> selecionarOpcoes(Map<Integer, T> opcoes) {
        return Arrays.stream(scanner.next()
                .split(" "))
                .map(IntUtils::converter)
                .map(IntUtils.Response::num)
                .filter(opcoes::containsKey)
                .map(opcoes::get)
                .toList();
    }

    protected <T> T selecionarOpcao(Map<Integer, T> opcoes) {
        return Arrays.stream(scanner.next().trim().split(" "))
                .map(IntUtils::converter)
                .map(IntUtils.Response::num)
                .filter(opcoes::containsKey)
                .map(opcoes::get)
                .findFirst()
                .orElseThrow();
    }

    protected static Integer buscarInteiro() {
        Integer escolha;
        boolean sucesso;
        do {
            var res = IntUtils.converter(scanner.next());
            sucesso = res.res();
            escolha = res.num();
        } while (!sucesso);
        return escolha;
    }

}
