import java.util.concurrent.Semaphore;

/**
 * Explorador rápido que executa tarefas com alta velocidade e eficiência.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorRapido extends Explorador implements Runnable {
    /**
     * Construtor do explorador rápido.
     *
     * @param nome nome do explorador
     * @param nivel nível do explorador
     * @param prioridade prioridade da thread
     * @param tarefa tarefa atribuída ao explorador
     * @param semaphore semáforo compartilhado entre as threads
     */
    public ExploradorRapido(String nome, int nivel, int prioridade, Tarefa tarefa, Semaphore semaphore) {
        super(nome, "Rápido", nivel, prioridade, tarefa, semaphore);
    }

    /**
     * Implementação específica da execução de tarefa para exploradores rápidos.
     * Exploradores rápidos executam tarefas com maior agilidade.
     */
    @Override
    public void executarTarefa() {
        exibirStatus();
        System.out.println("Status: Executando tarefa rapidamente!");
        System.out.println(
            "Ação: " + getNome() + " avançou com agilidade até " +
            getTarefa().getLocal() + " para " + getTarefa().getDescricao() + "."
        );

        try {
            Thread.sleep(600L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: A exploração rápida de " + getNome() + " foi interrompida.");
            return;
        }

        System.out.println("Resultado: " + getNome() + " concluiu a missão com velocidade.\n");
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

