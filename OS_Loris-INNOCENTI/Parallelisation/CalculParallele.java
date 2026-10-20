public class CalculParallele {

    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;

    public static void main(String[] args) {
        Calcule[] threads = new Calcule[DONNEES.length];

        long debut = System.nanoTime();

        for (int y = 0; y < DONNEES.length; y++) {
            threads[y] = new Calcule(DONNEES[y], MULTIPLICATEUR, y);
        }

        for (int j = 0; j < threads.length; j++) {
            threads[j].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrompu : " + e.getMessage());
            }
        }

        long sommeTotal = 0;
        for (int i = 0; i < threads.length; i++) {
            sommeTotal += threads[i].getResultat();
        }

        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + sommeTotal);
        System.out.println("Durée : " + duree + " ms");
    }
}
