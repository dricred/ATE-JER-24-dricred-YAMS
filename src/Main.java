package src;

public class Main {

    // Paramètres du jeu
    private static final int NOMBRE_FACES = 6;
    private static final int NOMBRE_DES = 5;
    private static final int NOMBRE_TOURS = 3;
    private static final int NOMBRE_MANCHE = 5;

/*
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

    private static int calculeSommeTableauEntier(int[] tableauEntiers) {
        int totalDes = 0;
        for (int valeur : tableauEntiers) {
            totalDes += valeur;
        }
        return totalDes;
    }

    private static void afficheSommeTableauEntier(int[] tableauEntiers) {
        System.out.println("Toltal des dés : " + calculeSommeTableauEntier(tableauEntiers));
    }

    private static void affichageComplet(int[] tableauEntiers) {
        System.out.println(PURPLE + "Dés obtenues : " + RESET);
        affichageTableau(tableauEntiers);
        afficheSommeTableauEntier(tableauEntiers);
    }

    private static String demandeUneReponse(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine().toLowerCase();
    }

    private static String demandeRelancerDes() {
        return demandeUneReponse("Voulez-vous relancer les dés ? [oui/non]");
    }

    private static String[] retourneValeurEcrit() {
        String reponseDesRelance = demandeUneReponse("Quelles dés voulez-vous relancer [1 à " + NOMBRE_DES
                + "] ? Séparez les par des espaces");
        String[] values = reponseDesRelance.split(" ");
        return values;
    }

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

    private static boolean estYams(int[] tableauDes) {
        return tableauDes[0] == tableauDes[1] &&
                tableauDes[1] == tableauDes[2] &&
                tableauDes[2] == tableauDes[3] &&
                tableauDes[3] == tableauDes[4];
    }

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

    private static boolean estBrelan(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        return nbrDesIdentique(tableauDes, nbrDes, faceIdentique) >= NBR_DES_BRELAN;
    }

    private static boolean estCarre(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        return nbrDesIdentique(tableauDes, nbrDes, faceIdentique) > NBR_DES_CARRE;
    }

    private static boolean estFull(int[] tableauDes, int nbrDes, int[] faceIdentique) {
        return (nbrDesIdentique(tableauDes, nbrDes, faceIdentique) == NBR_DES_FULL);
    }

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

    private static boolean estGrandeSuite(int[] tableauDes) {
        Arrays.sort(tableauDes);
        return tableauDes[0] + 1 == tableauDes[1] &&
                tableauDes[1] + 1 == tableauDes[2] &&
                tableauDes[2] + 1 == tableauDes[3] &&
                tableauDes[3] + 1 == tableauDes[4];
    }

    private static void afficheDebutJeu() {
        System.out.println(RED + "Jeu du " + CYAN + "Yahtzee" + RESET);
        System.out.println(RED + "Par " + CYAN+ "Rédouane Drici" + RESET);
        Scanner debutJeu = new Scanner(System.in);
        System.out.println("Appuyer sur entrée pour lancer le jeu ...");
        debutJeu.nextLine();
    }

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
    */
   public static void main(String[] args) {
       MainDeDés mainDeDés = new MainDeDés();
       mainDeDés.lancerDesEnMain();
       mainDeDés.affchiageMainDeDés();
   }
}