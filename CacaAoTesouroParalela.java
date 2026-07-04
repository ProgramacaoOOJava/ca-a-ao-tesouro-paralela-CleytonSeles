import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe principal que simula a Caça ao Tesouro Paralela.
 * Demonstra o uso de ExecutorService, Callable, Future, ScheduledExecutorService e Fork/Join.
 */
public class CacaAoTesouroParalela {

    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA - NÍVEL MESTRE ===");
        System.out.println("Missões paralelas com ExecutorService, Future e consolidação com Fork/Join.\n");

        // Missões imutáveis compartilhadas pelos exploradores.
        Missao missaoCavernas = new Missao("Mapear cavernas", "Caverna de Cristal", 7);
        Missao missaoArtefatos = new Missao("Recuperar artefatos", "Templo Submerso", 8);
        Missao missaoTrilhas = new Missao("Rastrear trilhas antigas", "Floresta Nebulosa", 6);
        Missao missaoReliquias = new Missao("Saquear relíquias protegidas", "Ruínas Antigas", 9);

        // Lista polimórfica com pelo menos quatro exploradores.
        ArrayList<Explorador> exploradores = new ArrayList<>();
        exploradores.add(new Rastreador("Lina", 5, 80, missaoCavernas));
        exploradores.add(new Saqueador("Drogan", 6, 90, missaoArtefatos));
        exploradores.add(new Rastreador("Kael", 4, 75, missaoTrilhas));
        exploradores.add(new Saqueador("Mira", 5, 85, missaoReliquias));

        // Executor com duas threads para processar as missões em paralelo.
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Executor agendado para acompanhar o andamento das missões.
        ScheduledExecutorService monitorExecutor = Executors.newSingleThreadScheduledExecutor();

        // Lista para armazenar os resultados assíncronos das missões.
        ArrayList<Future<Double>> futuros = new ArrayList<>();

        System.out.println("=== EXPLORADORES CADASTRADOS ===");
        for (Explorador explorador : exploradores) {
            explorador.exibirStatus();
            System.out.println();
        }

        System.out.println("=== SUBMETENDO MISSÕES AO EXECUTOR ===");
        for (Explorador explorador : exploradores) {
            Future<Double> futuro = executorService.submit(explorador::executarMissao);
            futuros.add(futuro);
        }

        monitorExecutor.scheduleAtFixedRate(() -> {
            int concluidas = 0;

            for (Future<Double> futuro : futuros) {
                if (futuro.isDone()) {
                    concluidas++;
                }
            }

            System.out.println("Monitor: " + concluidas + " de " + futuros.size() + " missões concluídas.");
        }, 0, 400, TimeUnit.MILLISECONDS);

        // Coleta dos pontos obtidos por cada explorador.
        double[] pontos = new double[exploradores.size()];

        for (int indice = 0; indice < futuros.size(); indice++) {
            try {
                double pontosObtidos = futuros.get(indice).get();
                pontos[indice] = pontosObtidos;

                System.out.println(
                    "Explorador: " + exploradores.get(indice).getNome() +
                    " | Especialidade: " + exploradores.get(indice).getEspecialidade() +
                    " | Missão: " + exploradores.get(indice).getMissao().getDescricao() +
                    " | Pontos obtidos: " + pontosObtidos
                );
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("Aviso: A thread principal foi interrompida ao aguardar os resultados.");
                break;
            } catch (ExecutionException exception) {
                System.out.println("Erro ao obter o resultado da missão: " + exception.getCause().getMessage());
            }
        }

        monitorExecutor.shutdownNow();
        executorService.shutdown();

        // Consolidação paralela dos pontos com Fork/Join.
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SomaPontos tarefaDeSoma = new SomaPontos(pontos, 0, pontos.length);
        double somaTotal = forkJoinPool.invoke(tarefaDeSoma);
        forkJoinPool.shutdown();

        System.out.println("\nSoma total dos pontos: " + somaTotal);
        System.out.println("\n=== MISSÃO MESTRE FINALIZADA ===");
        System.out.println("A expedição consolidou seus resultados com paralelismo avançado.");
    }
}
