import java.util.concurrent.RecursiveTask;

/**
 * Tarefa de Fork/Join responsável por consolidar os pontos dos exploradores em paralelo.
 */
public class SomaPontos extends RecursiveTask<Double> {
    // Faixa inicial do array a ser somada.
    private final int inicio;

    // Faixa final do array a ser somada.
    private final int fim;

    // Pontos obtidos pelos exploradores.
    private final double[] pontos;

    /**
     * Construtor da tarefa recursiva.
     *
     * @param pontos array de pontos
     * @param inicio índice inicial
     * @param fim índice final
     */
    public SomaPontos(double[] pontos, int inicio, int fim) {
        this.pontos = pontos;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Soma diretamente intervalos pequenos e divide intervalos maiores em subtarefas.
     *
     * @return soma total da faixa processada
     */
    @Override
    protected Double compute() {
        if (fim - inicio <= 2) {
            double soma = 0.0;

            for (int indice = inicio; indice < fim; indice++) {
                soma += pontos[indice];
            }

            return soma;
        }

        int meio = (inicio + fim) / 2;
        SomaPontos esquerda = new SomaPontos(pontos, inicio, meio);
        SomaPontos direita = new SomaPontos(pontos, meio, fim);

        esquerda.fork();
        double somaDireita = direita.compute();
        double somaEsquerda = esquerda.join();

        return somaEsquerda + somaDireita;
    }
}
