/**
 * Explorador rápido que executa tarefas com alta velocidade e eficiência.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorRapido extends Explorador implements Runnable {
    /**
     * Construtor do explorador rápido.
     *
     * @param nome nome do explorador
     * @param prioridade prioridade da thread
     * @param tarefa tarefa atribuída ao explorador
     */
    public ExploradorRapido(String nome, int prioridade, String tarefa) {
        super(nome, "Rápido", prioridade, tarefa);
    }

    /**
     * Implementação específica da execução de tarefa para exploradores rápidos.
     * Exploradores rápidos executam tarefas com maior agilidade.
     * @throws TarefaInvalidaException Se a tarefa for nula ou vazia
     */
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (getTarefa() == null || getTarefa().isBlank()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }

        exibirStatus();
        System.out.println("Status: Iniciando exploração com velocidade máxima...");
        System.out.println("Status: " + getNome() + " está executando a tarefa: " + getTarefa());

        try {
            Thread.sleep(500L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: A exploração rápida de " + getNome() + " foi interrompida.");
            return;
        }

        System.out.println("Status: " + getNome() + " concluiu a exploração rapidamente.\n");
    }

    /**
     * Método run() executado quando a thread é iniciada.
     * Trata exceções e chama executarTarefa().
     */
    @Override
    public void run() {
        try {
            executarTarefa();
        } catch (TarefaInvalidaException exception) {
            System.out.println("Erro: " + exception.getMessage() + "\n");
        }
    }
}

