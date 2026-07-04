/**
 * Classe abstrata que representa um explorador na Caça ao Tesouro Paralela.
 * Define a estrutura básica para diferentes tipos de exploradores do nível Mestre.
 */
public abstract class Explorador {
    // Nome do explorador.
    private final String nome;

    // Especialidade do explorador, como Rastreador ou Saqueador.
    private final String especialidade;

    // Nível de experiência do explorador.
    private final int nivel;

    // Energia disponível para cumprir a missão.
    private final int energia;

    // Missão imutável compartilhada com segurança entre tarefas concorrentes.
    private final Missao missao;

    /**
     * Construtor que inicializa todos os atributos do explorador.
     *
     * @param nome nome do explorador
     * @param especialidade especialidade do explorador
     * @param nivel nível do explorador
     * @param energia energia disponível para a missão
     * @param missao missão imutável atribuída ao explorador
     */
    public Explorador(String nome, String especialidade, int nivel, int energia, Missao missao) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.energia = energia;
        this.missao = missao;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses.
     * Define como cada tipo de explorador executa sua missão e retorna a pontuação obtida.
     */
    public abstract Double executarMissao();

    /**
     * Exibe o status completo do explorador com formatação clara.
     */
    public void exibirStatus() {
        System.out.println("Nome: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Nível: " + nivel);
        System.out.println("Energia: " + energia);
        System.out.println(
            "Missão: " + missao.getDescricao() +
            " | Local: " + missao.getLocal() +
            " | Dificuldade: " + missao.getDificuldade()
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

    public int getEnergia() {
        return energia;
    }

    public Missao getMissao() {
        return missao;
    }
}

