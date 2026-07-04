/**
 * Classe abstrata que representa um explorador na Caça ao Tesouro Paralela.
 * Define a estrutura básica para diferentes tipos de exploradores.
 */
public abstract class Explorador {
    /**
     * Nome do explorador.
     */
    private final String nome;

    /**
     * Tipo do explorador, como Rápido ou Cuidadoso.
     */
    private final String tipo;

    /**
     * Prioridade lógica do explorador e valor usado na configuração da thread.
     */
    private final int prioridade;

    /**
     * Missão atribuída ao explorador.
     */
    private final String tarefa;

    /**
     * Construtor que inicializa todos os atributos do explorador.
     *
     * @param nome nome do explorador
     * @param tipo tipo do explorador
     * @param prioridade prioridade da thread associada
     * @param tarefa tarefa a ser executada
     */
    public Explorador(String nome, String tipo, int prioridade, String tarefa) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses.
     * Define como cada tipo de explorador executa sua tarefa.
     * @throws TarefaInvalidaException Se a tarefa for inválida
     */
    public abstract void executarTarefa() throws TarefaInvalidaException;
    
    /**
     * Exibe o status completo do explorador com formatação clara.
     */
    public void exibirStatus() {
        System.out.println("Explorador: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Tarefa: " + tarefa);
    }

    // Getters para acesso aos atributos encapsulados
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getTarefa() {
        return tarefa;
    }
}

