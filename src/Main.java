package src;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int NOMBRE_FACES = 6;
    private static final int NOMBRE_DES = 5;
    private static final int NOMBRE_TOURS = 3; // (le nombre de jet de dés dispo)

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

    /**
     * demande à l'utilisateur un message donné, puis retourne sa réponse
     * @param prompt le message à demander
     * @return la réponse de l'utilisateur
     */
    private static String demandeUneReponse(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine().toLowerCase();
    }

    /**
     * demande à l'utilisateur s'il veut relancer ses dés
     * @return la réponse de l'utilisateur (oui ou non)
     */
    private static String demandeRelancerDes() {
        return demandeUneReponse("Voulez-vous relancer les dés ? [oui/non]");
    }

    /**
     * demande à l'utilisateur quelles sont les dés à relancer
     * @return le/les numéro(s) des dés à relancer
     */
    private static String[] retourneValeurEcrit() {
        String reponseDesRelance = demandeUneReponse("Quelles dés voulez-vous relancer ? [1|2|3|4|5]");
        String[] values = reponseDesRelance.split("\\|");
        return values;
    }

    /**
     * Demande la confirmation de l'utlisateur de relancer les dés et les quelles
     *@param tableauEntiers le tableau contenant des valeurs entières
     * @param nbrJetDe le nombre de tours disponible
     * @param nbrFaces le nombre max que l'on peut tirer
     */
    private static void relancerDes(int[] tableauEntiers, int nbrJetDe, int nbrFaces) {
        int nbrLancer = 1;
        String reponseOuiOuNon = "";
        boolean estOui = false;

        do {
            reponseOuiOuNon = demandeRelancerDes();
            estOui = reponseOuiOuNon.equals("oui");
            nbrLancer++;

            if (estOui) {
                for (String element : retourneValeurEcrit()) {
                    tableauEntiers[Integer.parseInt(element) - 1] = lancerUnDe(nbrFaces);
                }
                affichageComplet(tableauEntiers);
            }
        } while (estOui && nbrLancer < nbrJetDe);
    }

    public static void main(String[] args) {
        // Premier Jet de dés
        int[] tabDes = lancerXDe(NOMBRE_DES, NOMBRE_FACES);
        affichageComplet(tabDes);

        relancerDes(tabDes, NOMBRE_TOURS, NOMBRE_FACES);
    }
}
