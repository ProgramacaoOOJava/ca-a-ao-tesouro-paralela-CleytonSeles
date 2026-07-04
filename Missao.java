/**
 * Classe imutável que representa uma missão compartilhada entre exploradores.
 * Seus dados são definidos no construtor e permanecem estáveis durante toda a execução.
 */
public final class Missao {
    // Descrição da missão.
    private final String descricao;

    // Local em que a missão será realizada.
    private final String local;

    // Grau de dificuldade da missão.
    private final int dificuldade;

    /**
     * Construtor da missão imutável.
     *
     * @param descricao descrição da missão
     * @param local local da missão
     * @param dificuldade dificuldade da missão
     */
    public Missao(String descricao, String local, int dificuldade) {
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
