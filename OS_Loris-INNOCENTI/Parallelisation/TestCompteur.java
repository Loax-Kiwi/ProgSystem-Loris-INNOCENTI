public class TestCompteur {
    public static void main(String[] args) {
        int nbTests = 50;
        int nbThreads = 9;
        int nbIncrements = 1_111_111;

        System.out.println("Test de Race Condition");

        // Étape 1 : Boucle pour répéter le test 50 fois
        for (int test = 1; test <= nbTests; test++) {
			
			// Étape 2
            CompteurDangereux.resetCompteur();

            Thread[] threads = new Thread[nbThreads];
            for (int i = 0; i < nbThreads; i++) {
                threads[i] = new Thread(
                    new CompteurDangereux.Incrementeur("Thread-" + i, nbIncrements)
                );
            }

            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }

            for (int i = 0; i < threads.length; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    System.err.println("Thread interrompu : " + e.getMessage());
                }
            }
        }
    }
}