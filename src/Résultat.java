package src;

public class Résultat {
    // Paramètres Des points
    private static final int NBR_POINT_YAMS = 50;
    private static final int NBR_POINT_FULL = 25;
    private static final int NBR_POINT_GRANDE_SUITE = 40;
    private static final int NBR_POINT_PETITE_SUITE = 30;

    private static final int NBR_DES_BRELAN = 3;
    private static final int NBR_DES_CARRE = 4;

    public DesEnMain desEnMain;
    public Combinaison combi = new Combinaison(desEnMain);

    public int resultatPoint() {
        int nbrPoints = 0;

        System.out.print("Vous avez eu un");
        if (combi.estYams() && !combi.combinaisonDejaObtenu[0]) {
            System.out.print(" Yams.");
            nbrPoints = NBR_POINT_YAMS;
            combi.combinaisonDejaObtenu[0] = true;

        } else if (combi.estFull() && !combi.combinaisonDejaObtenu[1]) {
            System.out.print(" Full.");
            nbrPoints = NBR_POINT_FULL;
            combi.combinaisonDejaObtenu[1] = true;

        } else if (combi.estCarre() && !combi.combinaisonDejaObtenu[2]) {
            System.out.print(" Carre.");
            nbrPoints = combi.faceIdentique[0] * NBR_DES_CARRE;
            combi.combinaisonDejaObtenu[2] = true;

        } else if (combi.estBrelan() && !combi.combinaisonDejaObtenu[3]) {
            System.out.print(" Brelan.");
            nbrPoints = combi.faceIdentique[0] * NBR_DES_BRELAN;
            combi.combinaisonDejaObtenu[3] = true;

        } else if (combi.estGrandeSuite() && !combi.combinaisonDejaObtenu[4]) {
            System.out.print("e grande suite.");
            nbrPoints = NBR_POINT_GRANDE_SUITE;
            combi.combinaisonDejaObtenu[4] = true;

        } else if (combi.estPetiteSuite() && !combi.combinaisonDejaObtenu[5]) {
            System.out.print("e petite suite.");
            nbrPoints = NBR_POINT_PETITE_SUITE;
            combi.combinaisonDejaObtenu[5] = true;

        } else if (!combi.combinaisonDejaObtenu[6]){
            System.out.print("e chance");
            nbrPoints = combi.calculerSommeDes();
            combi.combinaisonDejaObtenu[6] = true;
        } else {
            System.out.print("e combinaison déja obtenu");
        }
        System.out.println();
        System.out.println("Vous recevez " + nbrPoints + " points");

        return nbrPoints;
    }
}
