import java.util.concurrent.Semaphore;

/**
 * Explorador cuidadoso que executa tarefas com precisão e atenção aos detalhes.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {
    /**
     * Construtor do explorador cuidadoso.
     *
     * @param nome nome do explorador
     * @param nivel nível do explorador
     * @param prioridade prioridade da thread
     * @param tarefa tarefa atribuída ao explorador
     * @param semaphore semáforo compartilhado entre as threads
     */
    public ExploradorCuidadoso(String nome, int nivel, int prioridade, Tarefa tarefa, Semaphore semaphore) {
        super(nome, "Cuidadoso", nivel, prioridade, tarefa, semaphore);
    }

    /**
     * Implementação específica da execução de tarefa para exploradores cuidadosos.
     * Exploradores cuidadosos executam tarefas com mais cautela e precisão.
     */
    @Override
    public void executarTarefa() {
        exibirStatus();
        System.out.println("Status: Realizando tarefa com cautela!");
        System.out.println(
            "Ação: " + getNome() + " está analisando cuidadosamente a área de " +
            getTarefa().getLocal() + " para " + getTarefa().getDescricao() + "."
        );

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: A exploração cuidadosa de " + getNome() + " foi interrompida.");
            return;
        }

        System.out.println("Resultado: " + getNome() + " concluiu a missão com precisão.\n");
    }

    /**
     * Método run() executado quando a thread é iniciada.
     * Controla o acesso ao recurso compartilhado com um semáforo de duas permissões.
     */
    @Override
    public void run() {
        boolean permissaoAdquirida = false;

        try {
            System.out.println(getNome() + " aguardando permissão do semáforo...");
            getSemaphore().acquire();
            permissaoAdquirida = true;
            System.out.println(getNome() + " recebeu permissão e iniciou a missão.");
            executarTarefa();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: " + getNome() + " foi interrompido antes de concluir a missão.");
        } finally {
            if (permissaoAdquirida) {
                System.out.println(getNome() + " liberou a permissão do semáforo.");
                getSemaphore().release();
            }
        }
    }
}

