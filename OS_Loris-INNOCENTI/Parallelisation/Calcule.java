public class Calcule extends Thread {
    private final int valeur;
    private final int multiplicateur;
    private final int index;
    private long resultat;

    public Calcule(int valeur, int multiplicateur, int index) {
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
    }

    @Override
    public void run() {
        for (int x = 0; x < multiplicateur; x++) {
            resultat += (long) valeur * valeur + valeur;
        }
    }

    public long getResultat() {
        return resultat;
    }
}
