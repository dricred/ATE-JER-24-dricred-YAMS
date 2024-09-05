package src;

import java.util.Random;

public class Main {
    private static final int NOMBRE_FACES = 6;

    /**
     * Prend un nombre aléatoire entre 1 et 6 (simulation d'un dé)
     * @return le nombre aléatoire
     */
    private static int lancerUnDe(int nbrFace) {
        Random random = new Random();
        return random.nextInt(nbrFace) + 1;
    }

    public static void main(String[] args) {
        System.out.println(lancerUnDe(NOMBRE_FACES));
    }
}
