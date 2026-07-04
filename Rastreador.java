import java.util.concurrent.Callable;

/**
 * Explorador especializado em mapear rotas, identificar trilhas e localizar pontos estratégicos.
 * Implementa Callable para retornar a pontuação obtida ao final da missão.
 */
public class Rastreador extends Explorador implements Callable<Double> {

    /**
     * Construtor do rastreador.
     *
     * @param nome nome do explorador
     * @param nivel nível do explorador
     * @param energia energia disponível
     * @param missao missão imutável atribuída
     */
    public Rastreador(String nome, int nivel, int energia, Missao missao) {
        super(nome, "Rastreador", nivel, energia, missao);
    }

    /**
     * Executa a missão com foco em reconhecimento de área e geração de vantagem tática.
     *
     * @return pontos obtidos pelo rastreador
     */
    @Override
    public Double executarMissao() {
        exibirStatus();
        System.out.println("Status: Rastreando trilhas e mapeando a área com precisão.");

        try {
            Thread.sleep(700L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: " + getNome() + " teve a missão de rastreamento interrompida.\n");
            return 0.0;
        }

        double pontosBase = getMissao().getDificuldade() * 18.0;
        double bonusDeNivel = getNivel() * 12.0;
        double bonusDeEnergia = getEnergia() * 1.5;
        double total = pontosBase + bonusDeNivel + bonusDeEnergia;

        System.out.println("Resultado: " + getNome() + " concluiu o reconhecimento e acumulou " + total + " pontos.\n");
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

