package src;

import java.util.Arrays;

public class Combinaison {
    private static final int NBR_DES_BRELAN = 3;
    private static final int NBR_DES_CARRE = 4;
    private static final int NBR_DES_FULL = 4;

    private static final int NBR_COMBI_POSSIBLE = 7;

    public DesEnMain desEnMain;
    boolean[] combinaisonDejaObtenu = new boolean[NBR_COMBI_POSSIBLE];

    public Combinaison(DesEnMain desEnMain) {
        this.desEnMain = desEnMain;
    }

    public int calculerSommeDes() {
        int totalDes = 0;
        for (int valeur : desEnMain.faceVisible) {
            totalDes += valeur;
        }
        return totalDes;
    }

    public int nbrDesIdentique() {
        int desIdentique = 0;
        for (int indexActuel = 0; indexActuel < desEnMain.nbrDes; indexActuel++) {
            for (int indexSuivants = indexActuel + 1; indexSuivants < desEnMain.nbrDes; indexSuivants++) {
                if (desEnMain.faceVisible[indexActuel] == desEnMain.faceVisible[indexSuivants]) {
                    desIdentique++;
                }
            }
        }
        return desIdentique;
    }

    public boolean estYams() {
        return desEnMain.faceVisible[0] == desEnMain.faceVisible[1] &&
                desEnMain.faceVisible[1] == desEnMain.faceVisible[2] &&
                desEnMain.faceVisible[2] == desEnMain.faceVisible[3] &&
                desEnMain.faceVisible[3] == desEnMain.faceVisible[4];
    }

    public boolean estBrelan() {
        return nbrDesIdentique() >= NBR_DES_BRELAN;
    }

    public boolean estCarre() {
        return nbrDesIdentique() > NBR_DES_CARRE;
    }

    public boolean estFull() {
        return nbrDesIdentique() == NBR_DES_FULL;
    }

    public boolean estPetiteSuite() {
        Arrays.sort(desEnMain.faceVisible);
        return (desEnMain.faceVisible[0] + 1 == desEnMain.faceVisible[1] &&
                desEnMain.faceVisible[1] + 1 == desEnMain.faceVisible[2] &&
                desEnMain.faceVisible[2] + 1 == desEnMain.faceVisible[3])
                ||
                (desEnMain.faceVisible[1] + 1 == desEnMain.faceVisible[2] &&
                        desEnMain.faceVisible[2] + 1 == desEnMain.faceVisible[3] &&
                        desEnMain.faceVisible[3] + 1 == desEnMain.faceVisible[4]);
    }

    public boolean estGrandeSuite() {
        Arrays.sort(desEnMain.faceVisible);
        return desEnMain.faceVisible[0] + 1 == desEnMain.faceVisible[1] &&
                desEnMain.faceVisible[1] + 1 == desEnMain.faceVisible[2] &&
                desEnMain.faceVisible[2] + 1 == desEnMain.faceVisible[3] &&
                desEnMain.faceVisible[3] + 1 == desEnMain.faceVisible[4];
    }

}
