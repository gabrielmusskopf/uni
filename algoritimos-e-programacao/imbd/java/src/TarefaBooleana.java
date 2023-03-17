import java.util.function.Function;

interface TarefaBooleana extends Function<Runnable, Boolean> {

    static Boolean executar(Runnable acao){
        return TarefaSuporte.executar().apply(acao);
    }

    class TarefaSuporte {
        private static final TarefaBooleana TAREFA_BOOLEAN = runnable -> {
            runnable.run();
            return true;
        };

        static TarefaBooleana executar(){
            return TAREFA_BOOLEAN;
        }
    }

}
