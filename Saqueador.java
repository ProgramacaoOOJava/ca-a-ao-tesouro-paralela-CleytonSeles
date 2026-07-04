import java.util.concurrent.Callable;

/**
 * Explorador especializado em recuperar artefatos e maximizar os ganhos da expedição.
 * Implementa Callable para retornar a pontuação obtida ao final da missão.
 */
public class Saqueador extends Explorador implements Callable<Double> {

    /**
     * Construtor do saqueador.
     *
     * @param nome nome do explorador
     * @param nivel nível do explorador
     * @param energia energia disponível
     * @param missao missão imutável atribuída
     */
    public Saqueador(String nome, int nivel, int energia, Missao missao) {
        super(nome, "Saqueador", nivel, energia, missao);
    }

    /**
     * Executa a missão com foco em recuperação de tesouros e obtenção de recursos valiosos.
     *
     * @return pontos obtidos pelo saqueador
     */
    @Override
    public Double executarMissao() {
        exibirStatus();
        System.out.println("Status: Recuperando artefatos e protegendo os achados da equipe.");

        try {
            Thread.sleep(900L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: " + getNome() + " teve a missão de saque interrompida.\n");
            return 0.0;
        }

        double pontosBase = getMissao().getDificuldade() * 22.0;
        double bonusDeNivel = getNivel() * 10.0;
        double bonusDeEnergia = getEnergia() * 1.8;
        double total = pontosBase + bonusDeNivel + bonusDeEnergia;

        System.out.println("Resultado: " + getNome() + " garantiu os espólios e acumulou " + total + " pontos.\n");
        return total;
    }

    /**
     * Método exigido por Callable para submeter a missão ao ExecutorService.
     *
     * @return pontuação final da missão
     */
    @Override
    public Double call() {
        return executarMissao();
    }
}
