package br.com.gabrielgmusskopf.unisinos.infra.cli;

import br.com.gabrielgmusskopf.unisinos.dominio.util.IntUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ConsoleAbstrato implements Console {

    protected static final Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    protected final ConsoleManager consoleManager;

    protected ConsoleAbstrato(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    protected <T extends Enum<?> & ComandoCli> Map<Integer, T> exibirOpcoes(T... comandoClis) {
        Collections.sort(new ArrayList<>(List.of(comandoClis)), Comparator.comparing(ComandoCli::getOrdem));

        return IntStream.range(0, comandoClis.length)
                .boxed()
                .peek(i -> System.out.printf("%d %s%n", i + 1, comandoClis[i].getDescricao()))
                .collect(Collectors.toMap(i -> i + 1, i -> comandoClis[i]));
    }

    protected <T> Map<Integer, T> montarOpcoes(List<T> produtos) {
        return IntStream.range(0, produtos.size())
                .boxed()
                .collect(Collectors.toMap(i -> i+1, produtos::get));
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
