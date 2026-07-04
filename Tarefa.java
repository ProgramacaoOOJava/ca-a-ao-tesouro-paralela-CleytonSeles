/**
 * Classe imutável que representa uma tarefa compartilhada entre exploradores.
 * Como seus atributos são finais e só existem getters, ela pode ser usada com segurança por várias threads.
 */
public final class Tarefa {
    // Descrição da atividade que será executada.
    private final String descricao;

    // Local da missão no mapa da expedição.
    private final String local;

    // Grau de dificuldade da tarefa.
    private final int dificuldade;

    /**
     * Construtor que inicializa todos os atributos imutáveis da tarefa.
     *
     * @param descricao descrição da tarefa
     * @param local local da tarefa
     * @param dificuldade dificuldade da tarefa
     */
    public Tarefa(String descricao, String local, int dificuldade) {
        this.descricao = descricao;
        this.local = local;
        this.dificuldade = dificuldade;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public int getDificuldade() {
        return dificuldade;
    }
}
