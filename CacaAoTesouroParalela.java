import java.util.ArrayList;

/**
 * Classe principal que simula a Caça ao Tesouro Paralela.
 * Demonstra o uso de threads, prioridades, tipos (user e daemon) e exceções personalizadas
 * através de diferentes tipos de exploradores.
 */
public class CacaAoTesouroParalela {
    
    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA ===");
        System.out.println("Exploradores em ação: threads, prioridades e exceções em Java\n");
        
        // Lista para gerenciar as threads dos exploradores
        ArrayList<Thread> threads = new ArrayList<>();
        
        // Criando exploradores rápidos
        ExploradorRapido alice = new ExploradorRapido("Alice", Thread.MAX_PRIORITY, "Vasculhar a caverna");
        ExploradorRapido clara = new ExploradorRapido("Clara", Thread.MAX_PRIORITY, "");
        
        // Criando exploradores cuidadosos
        ExploradorCuidadoso bob = new ExploradorCuidadoso("Bob", Thread.MIN_PRIORITY, "Mapear a floresta");
        ExploradorCuidadoso diana = new ExploradorCuidadoso("Diana", Thread.MIN_PRIORITY, "Analisar as ruínas");
        
        // Criando um explorador com tarefa inválida para demonstrar exceção
        // A exploradora Clara já foi criada com tarefa vazia para acionar a exceção personalizada.
        
        // Encapsulando exploradores em threads
        Thread threadAlice = new Thread(alice, alice.getNome());
        Thread threadClara = new Thread(clara, clara.getNome());
        Thread threadBob = new Thread(bob, bob.getNome());
        Thread threadDiana = new Thread(diana, diana.getNome());
        
        // Configurando prioridades das threads
        threadAlice.setPriority(alice.getPrioridade());
        threadClara.setPriority(clara.getPrioridade());
        threadBob.setPriority(bob.getPrioridade());
        threadDiana.setPriority(diana.getPrioridade());
        
        // Configurando algumas threads como daemon (tarefas secundárias)
        threadBob.setDaemon(true);
        
        // Adicionando threads à lista
        threads.add(threadAlice);
        threads.add(threadClara);
        threads.add(threadBob);
        threads.add(threadDiana);
        
        // Exibindo informações das threads antes da execução
        System.out.println("\n=== INFORMAÇÕES DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread: " + thread.getName());
            System.out.println("Prioridade real: " + thread.getPriority());
            System.out.println("É daemon? " + thread.isDaemon());
            System.out.println("Estado inicial: " + thread.getState() + "\n");
        }
        
        // Iniciando todas as threads
        System.out.println("\n=== INICIANDO EXPLORAÇÃO ===");
        for (Thread thread : threads) {
            thread.start();
        }

        // Pequena pausa para observar a transição de estado após o start().
        try {
            Thread.sleep(150L);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Aviso: A thread principal foi interrompida ao observar os estados.");
        }

        System.out.println("\n=== ESTADOS DURANTE A EXECUÇÃO ===");
        for (Thread thread : threads) {
            System.out.println("Thread " + thread.getName() + " em estado: " + thread.getState());
        }
        
        // Aguardando conclusão das threads não-daemon
        System.out.println("\n=== AGUARDANDO CONCLUSÃO DOS EXPLORADORES ===");
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
        
        // Verificando estado final das threads
        System.out.println("\n=== ESTADO FINAL DAS THREADS ===");
        for (Thread thread : threads) {
            System.out.println("Thread " + thread.getName() + " finalizou em estado: " + thread.getState());
        }
        
        System.out.println("\n=== CAÇA AO TESOURO PARALELA FINALIZADA ===");
        System.out.println("Todos os exploradores principais completaram suas missões!");
    }
}


