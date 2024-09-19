package src;
import java.util.*;

public class Main {
    // Paramètres du jeu
    private static final int NOMBRE_FACES = 6;
    private static final int NOMBRE_DES = 5;
    private static final int NOMBRE_TOURS = 3;
    private static final int NOMBRE_MANCHE = 5;

    // Paramètres des combinaisons
    private static final int NBR_DES_BRELAN = 3;
    private static final int NBR_DES_CARRE = 4;
    private static final int NBR_DES_FULL = 4;

    // Paramètres Des points
    private static final int NBR_POINT_YAMS = 50;
    private static final int NBR_POINT_FULL = 25;
    private static final int NBR_POINT_GRANDE_SUITE = 40;
    private static final int NBR_POINT_PETITE_SUITE = 30;

    // Les couleurs d'affichage
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RESET = "\u001B[0m";



    /**
     * Prend plusieurs nombres aléatoire en fonction du nombre donné en paramètre (simulation de plusieurs dés lancé)
     * @param nbrDes   Le nombre de chiffre à tirer
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
            System.out.println(GREEN + "Dé [" + (i + 1) + "] : " + RESET + tableauEntiers[i]);
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
        System.out.println(PURPLE + "Dés obtenues : " + RESET);
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

    /**
     * vérifie si le résultats de nos lancés de dés sont Yams (tous équaux)
     * @param tableauDes le tableau contenant les valeurs des dés
     * @return si les dés ont tous la même valeurs ou non ("Yams")
     */
    private static boolean estYams(int[] tableauDes) {
        return tableauDes[0] == tableauDes[1] &&
                tableauDes[1] == tableauDes[2] &&
                tableauDes[2] == tableauDes[3] &&
                tableauDes[3] == tableauDes[4];
    }

    /**
     * Calcule le nombre de des de même valeur dans un tableau de dés donné
     * @param tableauDes le tableau contenant les valeurs des dés
     * @param nbrDes     Le nombre de chiffre à tirer
     * @return le nombre de dés identique
     */
    private static int nbrDesIdentique(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        int desIdentique = 0;
        for (int indexActuel = 0; indexActuel < nbrDes; indexActuel++) {
            for (int indexSuivants = indexActuel + 1; indexSuivants < nbrDes; indexSuivants++) {
                if (tableauDes[indexActuel] == tableauDes[indexSuivants]) {
                    desIdentique++;
                    faceIdentique[0] = tableauDes[indexActuel];
                }
            }
        }
        return desIdentique;
    }

    /**
     * vérifie si le résultats de nos lancés de dés sont Brelan (3 dés de même valeurs)
     * @param tableauDes le tableau contenant les valeurs des dés
     * @param nbrDes     Le nombre de chiffre à tirer
     * @return s'il y a 3 dés de même la valeurs ou non ("Brelan")
     */
    private static boolean estBrelan(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        return nbrDesIdentique(tableauDes, nbrDes, faceIdentique) >= NBR_DES_BRELAN;
    }

    /**
     * vérifie si le résultats de nos lancés de dés sont Carre (4 dés de même valeurs)
     * @param tableauDes le tableau contenant les valeurs des dés
     * @param nbrDes     Le nombre de chiffre à tirer
     * @return s'il y a 4 dés de la même valeurs ou non ("Carre")
     */
    private static boolean estCarre(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        return nbrDesIdentique(tableauDes, nbrDes, faceIdentique) > NBR_DES_CARRE;
    }

    /**
     * vérifie si le résultats de nos lancés de dés sont Full (un brelan + 2 dés de la même valeurs)
     * @param tableauDes le tableau contenant les valeurs des dés
     * @param nbrDes     Le nombre de chiffre à tirer
     * @return s'il y a un brelan + 2 dés de la même valeurs ou non ("Full")
     */
    private static boolean estFull(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        return (nbrDesIdentique(tableauDes, nbrDes, faceIdentique) == NBR_DES_FULL);
    }

    /**
     * vérifie si le résultats de nos lancés de dés sont une petite suite
     * @param tableauDes le tableau contenant les valeurs des dés
     * @return s'il y a une petite suite ou non
     */
    private static boolean estPetiteSuite(int[] tableauDes) {
        Arrays.sort(tableauDes);
        return (tableauDes[0] + 1 == tableauDes[1] &&
                tableauDes[1] + 1 == tableauDes[2] &&
                tableauDes[2] + 1 == tableauDes[3])
                ||
                (tableauDes[1] + 1 == tableauDes[2] &&
                        tableauDes[2] + 1 == tableauDes[3] &&
                        tableauDes[3] + 1 == tableauDes[4]);
    }

    /**
     * vérifie si le résultats de nos lancés de dés sont une grande suite
     * @param tableauDes le tableau contenant les valeurs des dés
     * @return s'il y a une grande suite ou non
     */
    private static boolean estGrandeSuite(int[] tableauDes) {
        Arrays.sort(tableauDes);
        return tableauDes[0] + 1 == tableauDes[1] &&
                tableauDes[1] + 1 == tableauDes[2] &&
                tableauDes[2] + 1 == tableauDes[3] &&
                tableauDes[3] + 1 == tableauDes[4];
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

    /**
     * Donne le nom de la combinaison obtenue, plus le nombre de points
     * et empeche le faite d'obtenir deux fois la même combiaison
     * @param tabDes le tableau conteneant les dés
     * @param combinaisonDejaObtenu Tableau des comninaison déja obtenu
     * @return le nombre de points obtenue
     */
    private static int resultatPoint(int[] tabDes , boolean[] combinaisonDejaObtenu) {
        int nbrPoints = 0;
        int[] faceIdentique = new int[1];

        System.out.print("Vous avez eu un");
        if (estYams(tabDes) && !combinaisonDejaObtenu[0]) {
            System.out.print(" Yams.");
            nbrPoints = NBR_POINT_YAMS;
            combinaisonDejaObtenu[0] = true;

        } else if (estFull(tabDes, NOMBRE_DES, faceIdentique) && !combinaisonDejaObtenu[1]) {
            System.out.print(" Full.");
            nbrPoints = NBR_POINT_FULL;
            combinaisonDejaObtenu[1] = true;

        } else if (estCarre(tabDes, NOMBRE_DES, faceIdentique) && !combinaisonDejaObtenu[2]) {
            System.out.print(" Carre.");
            nbrPoints = faceIdentique[0] * NBR_DES_CARRE;
            combinaisonDejaObtenu[2] = true;

        } else if (estBrelan(tabDes, NOMBRE_DES, faceIdentique) && !combinaisonDejaObtenu[3]) {
            System.out.print(" Brelan.");
            nbrPoints = faceIdentique[0] * NBR_DES_BRELAN;
            combinaisonDejaObtenu[3] = true;

        } else if (estGrandeSuite(tabDes) && !combinaisonDejaObtenu[4]) {
            System.out.print("e grande suite.");
            nbrPoints = NBR_POINT_GRANDE_SUITE;
            combinaisonDejaObtenu[4] = true;

        } else if (estPetiteSuite(tabDes) && !combinaisonDejaObtenu[5]) {
            System.out.print("e petite suite.");
            nbrPoints = NBR_POINT_PETITE_SUITE;
            combinaisonDejaObtenu[5] = true;

        } else if (!combinaisonDejaObtenu[6]){
            System.out.print("e chance");
            nbrPoints = calculeSommeTableauEntier(tabDes);
            combinaisonDejaObtenu[6] = true;
        } else {
            System.out.print("e combinaison déja obtenu");
        }
        System.out.println();
        System.out.println("Vous recevez " + nbrPoints + " points");

        return nbrPoints;
    }

    public static void main(String[] args) {
        afficheDebutJeu();
        boolean[] combinaisonDejaObtenu = new boolean[7];
        int nbrPoints = 0;

        for (int i = 0; i < NOMBRE_MANCHE; i++) {
            System.out.println(CYAN + "Manche " + (i + 1) + RESET);
            int[] tabDes = lancerXDe(NOMBRE_DES, NOMBRE_FACES);
            affichageComplet(tabDes);
            relancerDes(tabDes, NOMBRE_TOURS, NOMBRE_FACES);
            nbrPoints += resultatPoint(tabDes, combinaisonDejaObtenu);
            System.out.println(RED + "Fin de la mache " + (i + 1)+ RESET);
            System.out.println();

            // Affichage fin de jeu
            if (i == NOMBRE_MANCHE - 1) {
                System.out.println(CYAN + "Total des points obtenus : " + nbrPoints + RESET);
            }
        }

    }
}