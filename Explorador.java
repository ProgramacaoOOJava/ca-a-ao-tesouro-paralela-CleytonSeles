import java.util.concurrent.Semaphore;

/**
 * Classe abstrata que representa um explorador na Caça ao Tesouro Paralela.
 * Define a estrutura básica para diferentes tipos de exploradores do nível Aventureiro.
 */
public abstract class Explorador {
    // Nome do explorador.
    private final String nome;

    // Especialidade do explorador, como Rápido ou Cuidadoso.
    private final String especialidade;

    // Nível de experiência do explorador.
    private final int nivel;

    // Prioridade da thread associada ao explorador.
    private final int prioridade;

    // Tarefa imutável compartilhada com segurança entre threads.
    private final Tarefa tarefa;

    // Semáforo compartilhado que limita o acesso simultâneo à região crítica.
    private final Semaphore semaphore;

    /**
     * Construtor que inicializa todos os atributos do explorador.
     *
     * @param nome nome do explorador
     * @param especialidade especialidade do explorador
     * @param nivel nível do explorador
     * @param prioridade prioridade da thread associada
     * @param tarefa tarefa imutável atribuída ao explorador
     * @param semaphore semáforo compartilhado entre os exploradores
     */
    public Explorador(String nome, String especialidade, int nivel, int prioridade, Tarefa tarefa, Semaphore semaphore) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
        this.semaphore = semaphore;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses.
     * Define como cada tipo de explorador executa sua tarefa.
     */
    public abstract void executarTarefa();

    /**
     * Exibe o status completo do explorador com formatação clara.
     */
    public void exibirStatus() {
        System.out.println("Nome: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Nível: " + nivel);
        System.out.println("Prioridade: " + prioridade);
        System.out.println(
            "Tarefa: " + tarefa.getDescricao() +
            " | Local: " + tarefa.getLocal() +
            " | Dificuldade: " + tarefa.getDificuldade()
        );
    }

    // Getters para acesso aos atributos encapsulados.
    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
}

