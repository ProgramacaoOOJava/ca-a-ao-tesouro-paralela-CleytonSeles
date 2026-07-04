/**
 * Explorador cuidadoso que executa tarefas com precisão e atenção aos detalhes.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {
    /**
     * Construtor do explorador cuidadoso.
     *
     * @param nome nome do explorador
     * @param prioridade prioridade da thread
     * @param tarefa tarefa atribuída ao explorador
     */
    public ExploradorCuidadoso(String nome, int prioridade, String tarefa) {
        super(nome, "Cuidadoso", prioridade, tarefa);
    }

    /**
     * Implementação específica da execução de tarefa para exploradores cuidadosos.
     * Exploradores cuidadosos executam tarefas com mais cautela e precisão.
     * @throws TarefaInvalidaException Se a tarefa for nula ou vazia
     */
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (getTarefa() == null || getTarefa().isBlank()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }

        exibirStatus();
        System.out.println("Status: Iniciando exploração com cautela...");
        System.out.println("Status: " + getNome() + " está analisando cada detalhe da tarefa: " + getTarefa());

        try {
            Thread.sleep(900L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: A exploração cuidadosa de " + getNome() + " foi interrompida.");
            return;
        }

        System.out.println("Status: " + getNome() + " concluiu a exploração com precisão.\n");
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

