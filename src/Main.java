package src;

import java.util.*;

public class Main {
    // Paramètres du jeu
    private static final int NOMBRE_FACES = 6;
    private static final int NOMBRE_DES = 5;
    private static final int NOMBRE_TOURS = 3;
    private static final int NOMBRE_MANCHE = 5;


    // Les couleurs d'affichage
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RESET = "\u001B[0m";

    /**
     * affiche le contenu d'un tableau donné
     * @param tableauEntiers le table à afficher
     */
    private static void affichageTableau(int[] tableauEntiers) {
        for (int i = 0; i < tableauEntiers.length; i++) {
            System.out.println(GREEN + "Dé [" + (i + 1) + "] : " + RESET + tableauEntiers[i]);
        }
    }

    /**
     * Affiche le total des valeurs entier d'un tableau
     * @param tableauEntiers le tableau contenant des valeurs entières
     */
    private static void afficheSommeTableauEntier(int[] tableauEntiers, Combinaison combinaison) {
        System.out.println("Toltal des dés : " + combinaison.calculerSommeDes());
    }

    /**
     * Fait un affichage complet pour tous nos dés (leur face visible + la somme de ces faces)
     * @param tableauEntiers le tableau contenant des valeurs entières
     */
    private static void affichageComplet(int[] tableauEntiers, Combinaison combinaison) {
        System.out.println(PURPLE + "Dés obtenues : " + RESET);
        affichageTableau(tableauEntiers);
        afficheSommeTableauEntier(tableauEntiers, combinaison);
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
        String reponseDesRelance = demandeUneReponse("Quelles dés voulez-vous relancer [1 à " + NOMBRE_DES
                + "] ? Séparez les par des espaces");
        String[] values = reponseDesRelance.split(" ");
        return values;
    }

    /**
     * Demande la confirmation de l'utlisateur de relancer les dés et les quelles
     * @param tableauEntiers le tableau contenant des valeurs entières
     * @param nbrJetDe       le nombre de tours disponible
     * @param nbrFaces       le nombre max que l'on peut tirer
     */
    private static void relancerDes(int[] tableauEntiers, int nbrJetDe, int nbrFaces, DesEnMain desEnMain, Combinaison combinaison) {
        int nbrLancer = 1;
        String reponseOuiOuNon = "";
        boolean estOui = false;

        do {
            reponseOuiOuNon = demandeRelancerDes();
            estOui = reponseOuiOuNon.equals("oui");
            nbrLancer++;

            if (estOui) {
                for (String element : retourneValeurEcrit()) {
                    tableauEntiers[Integer.parseInt(element) - 1] = desEnMain.lancerUnDe();
                }
                affichageComplet(tableauEntiers, combinaison);
            }
        } while (estOui && nbrLancer < nbrJetDe);
    }

    /**
     * Affiche l'intro du jeu
     */
    private static void afficheDebutJeu() {
        System.out.println(RED + "Jeu du " + CYAN + "Yahtzee" + RESET);
        System.out.println(RED + "Par " + CYAN+ "Rédouane Drici" + RESET);
        Scanner debutJeu = new Scanner(System.in);
        System.out.println("Appuyer sur entrée pour lancer le jeu ...");
        debutJeu.nextLine();
    }

    public static void main(String[] args) {
        afficheDebutJeu();

        int nbrPoints = 0;

        for (int i = 0; i < NOMBRE_MANCHE; i++) {
            DesEnMain mainDeDes = new DesEnMain();
            Combinaison combinaison = new Combinaison(mainDeDes);
            Résultat resultat = new Résultat();
            System.out.println(CYAN + "Manche " + (i + 1) + RESET);
            mainDeDes.lancerXDe();
            affichageComplet(mainDeDes.faceVisible, combinaison);
            relancerDes(mainDeDes.faceVisible, NOMBRE_TOURS, NOMBRE_FACES, mainDeDes, combinaison);
            nbrPoints += resultat.resultatPoint();
            System.out.println(RED + "Fin de la mache " + (i + 1)+ RESET);
            System.out.println();

            // Affichage fin de jeu
            if (i == NOMBRE_MANCHE - 1) {
                System.out.println(CYAN + "Total des points obtenus : " + nbrPoints + RESET);
            }
        }

    }
}