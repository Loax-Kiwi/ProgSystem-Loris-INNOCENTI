public class TestMutex {
    public static void main(String[] args) {
        int nbTests = 50;
        int nbThreads = 9;
        int nbIncrements = 1_111_111;
		int valeurAttendue = nbThreads * nbIncrements;
		
		int succet = 0;
		int minObtenue = Integer.MAX_VALUE;
		int maxObtenue = Integer.MIN_VALUE;

        System.out.println("Test de Mutex");

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
			
			// Étape 3
            int resultatFinal = CompteurDangereux.getCompteur();
            System.out.println("Test " + test + " | Obtenu : " + resultatFinal + " | Attendu : " + valeurAttendue);
			
			// Étape 4
			if (resultatFinal == valeurAttendue) {
				succet++;
			}
			
			if (resultatFinal < minObtenue) {
				resultatFinal = minObtenue;
			}
			
			if (resultatFinal > maxObtenue) {
				resultatFinal = maxObtenue;
			}
			
			double pourcentageDeReussite;
        }
		System.out.println("Valeur attendue : " +valeurAttendue);
		System.out.println("Pourcentage de réussite : " + ((double)succet / nbTests * 100) + "%");
		System.out.println("Valeur minimale obtenue : " + minObtenue);
		System.out.println("Valeur maximale obtenue : " + maxObtenue);
    }
}