package src;

import java.util.Random;

public class Main {
    private static final int NOMBRE_FACES = 6;
    private static final int NOMBRE_DES = 5;

    /**
     * Prend un nombre aléatoire entre 1 et le nombre donné (simulation d'un dé)
     * @param nbrFace le nombre max que l'on peut tirer
     * @return le nombre aléatoire obtenu
     */
    private static int lancerUnDe(int nbrFace) {
        Random random = new Random();
        return random.nextInt(nbrFace) + 1;
    }

    /**
     * Prend plusieurs nombres aléatoire en fonction du nombre donné en paramètre (simulation de plusieurs dés lancé)
     * @param nbrDes Le nombre de chiffre à tirer
     * @param nbrFaces le nombre max que l'on peut tirer
     * @return un tableau avec les valeurs des nombres aléatoires obtenus
     */
    private static int[] lancerXDe(int nbrDes, int nbrFaces) {
        int[] tableauEntiers = new int[nbrDes];
        for (int i = 0; i < nbrDes; i++) {
            tableauEntiers[i] += lancerUnDe(nbrFaces);
        }
        return tableauEntiers;
    }

    /**
     * affiche le contenu d'un tableau donné
     * @param tableauEntiers le table à afficher
     */
    private static void affichageTableau(int[] tableauEntiers) {
        for (int i = 0; i < tableauEntiers.length; i++) {
            System.out.println("Dé [" + (i + 1) + "] : " + tableauEntiers[i]);
        }
    }

    /**
     * calcule le total des valeurs entiers d'un tableau
     * @param tableauEntiers le tableau contenant des valeurs entières
     */
    private static int calculeSommeTableauEntier(int[] tableauEntiers) {
        int totalDes = 0;
        for (int valeur : tableauEntiers) {
            totalDes += valeur;
        }
        return totalDes;
    }

    /**
     * Affiche le total des valeurs entier d'un tableau
     * @param tableauEntiers le tableau contenant des valeurs entières
     */
    private static void afficheSommeTableauEntier(int[] tableauEntiers) {
        System.out.println("Toltal des dés : " + calculeSommeTableauEntier(tableauEntiers));
    }

    /**
     * Fait un affichage complet pour tous nos dés (leur face visible + la somme de ces faces)
     * @param tableauEntiers le tableau contenant des valeurs entières
     */
    private static void affichageComplet(int[] tableauEntiers) {
        System.out.println("Dés obtenues : ");
        affichageTableau(tableauEntiers);
        afficheSommeTableauEntier(tableauEntiers);
    }

    public static void main(String[] args) {
        int[] tabDes = lancerXDe(NOMBRE_DES, NOMBRE_FACES);
        affichageComplet(tabDes);

    }
}
