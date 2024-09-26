package src;

public class Résultat {

    // Paramètres Des points
    private static final int NBR_POINT_YAMS = 50;
    private static final int NBR_POINT_FULL = 25;
    private static final int NBR_POINT_GRANDE_SUITE = 40;
    private static final int NBR_POINT_PETITE_SUITE = 30;

    // Paramètres des combinaisons
    private static final int NBR_DES_BRELAN = 3;
    private static final int NBR_DES_CARRE = 4;
    private static final int NBR_COMBI_POSSIBLE = 7;

    private boolean[] combinaisonDejaObtenu = new boolean[NBR_COMBI_POSSIBLE];
    private Combinaison combi;

    /**
     * Construit un résultat de point
     * @param combi
     */
    public Résultat(Combinaison combi) {
        this.combi = combi;
    }

    public boolean[] getCombinaisonDejaObtenu() {
        return combinaisonDejaObtenu;
    }

    public Combinaison getCombi() {
        return combi;
    }

    public void setCombinaisonDejaObtenu(boolean[] combinaisonDejaObtenu) {
        this.combinaisonDejaObtenu = combinaisonDejaObtenu;
    }

    public void setCombi(Combinaison combi) {
        this.combi = combi;
    }

    /**
     * Cacule le nombre de point de ce résulat
     * @return le nombre de point de ce résultat
     */
    public int resultatPoint() {
        int nbrPoints = 0;
        int[] faceIdentique = new int[1];

        System.out.print("Vous avez eu un");
        if (combi.estYams() && !combinaisonDejaObtenu[0]) {
            System.out.print(" Yams.");
            nbrPoints = NBR_POINT_YAMS;
            combinaisonDejaObtenu[0] = true;

        } else if (combi.estFull() && !combinaisonDejaObtenu[1]) {
            System.out.print(" Full.");
            nbrPoints = NBR_POINT_FULL;
            combinaisonDejaObtenu[1] = true;

        } else if (combi.estCarre() && !combinaisonDejaObtenu[2]) {
            System.out.print(" Carre.");
            nbrPoints = combi.getFaceIdentique()[0] * NBR_DES_CARRE;
            combinaisonDejaObtenu[2] = true;

        } else if (combi.estBrelan() && !combinaisonDejaObtenu[3]) {
            System.out.print(" Brelan.");
            nbrPoints = combi.getFaceIdentique()[0] * NBR_DES_BRELAN;
            combinaisonDejaObtenu[3] = true;

        } else if (combi.estGrandeSuite() && !combinaisonDejaObtenu[4]) {
            System.out.print("e grande suite.");
            nbrPoints = NBR_POINT_GRANDE_SUITE;
            combinaisonDejaObtenu[4] = true;

        } else if (combi.estPetiteSuite() && !combinaisonDejaObtenu[5]) {
            System.out.print("e petite suite.");
            nbrPoints = NBR_POINT_PETITE_SUITE;
            combinaisonDejaObtenu[5] = true;

        } else if (!combinaisonDejaObtenu[6]){
            System.out.print("e chance");
            nbrPoints = combi.calculerSommeDes();
            combinaisonDejaObtenu[6] = true;
        } else {
            System.out.print("e combinaison déja obtenu");
        }
        System.out.println();
        System.out.println("Vous recevez " + nbrPoints + " points");

        return nbrPoints;
    }
}
