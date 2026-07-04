import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Classe principal que simula a Caça ao Tesouro Paralela.
 * Demonstra o uso de threads, prioridades, daemon, tarefas imutáveis e semáforo compartilhado.
 */
public class CacaAoTesouroParalela {

    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA - NÍVEL AVENTUREIRO ===");
        System.out.println("Exploradores em missão cooperativa com controle de acesso por semáforo.\n");

        // Semáforo com duas permissões para limitar a duas execuções simultâneas.
        Semaphore semaphore = new Semaphore(2);

        // Lista para gerenciar as threads dos exploradores.
        ArrayList<Thread> threads = new ArrayList<>();

        // Criação das quatro tarefas imutáveis compartilhadas pelas threads.
        Tarefa tarefaCaverna = new Tarefa("Mapear a caverna", "Caverna de Cristal", 7);
        Tarefa tarefaRuinas = new Tarefa("Explorar as ruínas", "Ruínas Antigas", 6);
        Tarefa tarefaFloresta = new Tarefa("Catalogar trilhas", "Floresta Nebulosa", 5);
        Tarefa tarefaTemplo = new Tarefa("Recuperar artefatos", "Templo Submerso", 8);

        // Criação de dois exploradores rápidos e dois cuidadosos.
        ExploradorRapido alice = new ExploradorRapido("Alice", 5, Thread.MAX_PRIORITY, tarefaCaverna, semaphore);
        ExploradorRapido clara = new ExploradorRapido("Clara", 4, 8, tarefaRuinas, semaphore);
        ExploradorCuidadoso bob = new ExploradorCuidadoso("Bob", 4, 5, tarefaFloresta, semaphore);
        ExploradorCuidadoso diana = new ExploradorCuidadoso("Diana", 6, Thread.MIN_PRIORITY, tarefaTemplo, semaphore);

        // Encapsulando exploradores em threads.
        Thread threadAlice = new Thread(alice, alice.getNome());
        Thread threadClara = new Thread(clara, clara.getNome());
        Thread threadBob = new Thread(bob, bob.getNome());
        Thread threadDiana = new Thread(diana, diana.getNome());

        // Configurando prioridades das threads.
        threadAlice.setPriority(alice.getPrioridade());
        threadClara.setPriority(clara.getPrioridade());
        threadBob.setPriority(bob.getPrioridade());
        threadDiana.setPriority(diana.getPrioridade());

        // Uma thread daemon representa uma tarefa de apoio que não bloqueia o encerramento do programa.
        threadDiana.setDaemon(true);

        // Adicionando as threads à lista de gerenciamento.
        threads.add(threadAlice);
        threads.add(threadClara);
        threads.add(threadBob);
        threads.add(threadDiana);

        // Exibindo informações das threads antes da execução.
        System.out.println("\n=== INFORMAÇÕES DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread: " + thread.getName());
            System.out.println("Prioridade real: " + thread.getPriority());
            System.out.println("É daemon? " + thread.isDaemon());
            System.out.println("Estado inicial: " + thread.getState() + "\n");
        }

        // Iniciando a execução paralela dos exploradores.
        System.out.println("\n=== INICIANDO MISSÕES ===");
        for (Thread thread : threads) {
            thread.start();
        }

        // Pausa breve para observar a mudança de estados após o start().
        try {
            Thread.sleep(200L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: A thread principal foi interrompida ao observar os estados.");
        }

        System.out.println("\n=== ESTADOS DURANTE A EXECUÇÃO ===");
        for (Thread thread : threads) {
            System.out.println("Thread " + thread.getName() + " em estado: " + thread.getState());
        }

        // A thread principal aguarda apenas as threads de usuário.
        System.out.println("\n=== AGUARDANDO CONCLUSÃO DOS EXPLORADORES PRINCIPAIS ===");
        for (Thread thread : threads) {
            if (!thread.isDaemon()) {
                try {
                    thread.join();
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    System.out.println("Aviso: A thread principal foi interrompida durante o join de " + thread.getName() + ".");
                }
            }
        }

        // Verificando o estado final das threads após a sincronização.
        System.out.println("\n=== ESTADO FINAL DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread " + thread.getName() + " finalizou em estado: " + thread.getState());
        }

        System.out.println("\n=== MISSÃO COOPERATIVA FINALIZADA ===");
        System.out.println("Os exploradores principais concluíram suas tarefas com acesso controlado por semáforo.");
    }
}

