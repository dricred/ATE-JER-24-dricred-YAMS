package src;
import java.util.Arrays;

public class Combinaison {
    // Paramètres des combinaisons
    private static final int NBR_DES_BRELAN = 3;
    private static final int NBR_DES_CARRE = 4;
    private static final int NBR_DES_FULL = 4;

    private DesEnMain desEnMain;
    private int[] faceIdentique = new int[1];

    /**
     * Construit une combinaison en fonction de la main de des
     * @param desEnMain la main de dés
     */
    public Combinaison(DesEnMain desEnMain) {
        this.desEnMain = desEnMain;
    }

    public DesEnMain getDesEnMain() {
        return desEnMain;
    }

    public int[] getFaceIdentique() {
        return faceIdentique;
    }

    public void setDesEnMain(DesEnMain desEnMain) {
        this.desEnMain = desEnMain;
    }

    public void setFaceIdentique(int[] faceIdentique) {
        this.faceIdentique = faceIdentique;
    }

    /**
     * calcule la somme des des de cette combinaison
     * @return la valeur total des des de cette combinaison
     */
    public int calculerSommeDes() {
        int totalDes = 0;
        for (int valeur : desEnMain.getFaceVisible()) {
            totalDes += valeur;
        }
        return totalDes;
    }

    /**
     * Regarde le nombre de des identique de cette combinaison
     * @return le nombre de des identique de cette combianaison
     */
    public int nbrDesIdentique() {
        int desIdentique = 0;
        for (int indexActuel = 0; indexActuel < desEnMain.getNbrDes(); indexActuel++) {
            for (int indexSuivants = indexActuel + 1; indexSuivants < desEnMain.getNbrDes(); indexSuivants++) {
                if (desEnMain.getFaceVisible()[indexActuel] == desEnMain.getFaceVisible()[indexSuivants]) {
                    desIdentique++;
                    faceIdentique[0] = desEnMain.getFaceVisible()[indexActuel];
                }
            }
        }
        return desIdentique;
    }

    /**
     * Vérifie si cette combianaison est Yams ou non
     * @return si cette combinaison est Yams ou non
     */
    public boolean estYams() {
        return desEnMain.getFaceVisible()[0] == desEnMain.getFaceVisible()[1] &&
                desEnMain.getFaceVisible()[1] == desEnMain.getFaceVisible()[2] &&
                desEnMain.getFaceVisible()[2] == desEnMain.getFaceVisible()[3] &&
                desEnMain.getFaceVisible()[3] == desEnMain.getFaceVisible()[4];
    }

    /**
     * Vérifie si cette combianaison est brelan ou non
     * @return si cette combinaison est brelan ou non
     */
    public boolean estBrelan() {
        return nbrDesIdentique() >= NBR_DES_BRELAN;
    }

    /**
     * Vérifie si cette combianaison est carre ou non
     * @return si cette combinaison est carre ou non
     */
    public boolean estCarre() {
        return nbrDesIdentique() > NBR_DES_CARRE;
    }

    /**
     * Vérifie si cette combianaison est full ou non
     * @return si cette combinaison est full ou non
     */
    public boolean estFull() {
        return nbrDesIdentique() == NBR_DES_FULL;
    }

    /**
     * Vérifie si cette combianaison est une petite suite ou non
     * @return si cette combinaison est une petite suite ou non
     */
    public boolean estPetiteSuite() {
        Arrays.sort(desEnMain.getFaceVisible());
        return (desEnMain.getFaceVisible()[0] + 1 == desEnMain.getFaceVisible()[1] &&
                desEnMain.getFaceVisible()[1] + 1 == desEnMain.getFaceVisible()[2] &&
                desEnMain.getFaceVisible()[2] + 1 == desEnMain.getFaceVisible()[3])
                ||
                (desEnMain.getFaceVisible()[1] + 1 == desEnMain.getFaceVisible()[2] &&
                        desEnMain.getFaceVisible()[2] + 1 == desEnMain.getFaceVisible()[3] &&
                        desEnMain.getFaceVisible()[3] + 1 == desEnMain.getFaceVisible()[4]);
    }

    /**
     * Vérifie si cette combianaison est une grande suite ou non
     * @return si cette combinaison est une grande suite ou non
     */
    public boolean estGrandeSuite() {
        Arrays.sort(desEnMain.getFaceVisible());
        return desEnMain.getFaceVisible()[0] + 1 == desEnMain.getFaceVisible()[1] &&
                desEnMain.getFaceVisible()[1] + 1 == desEnMain.getFaceVisible()[2] &&
                desEnMain.getFaceVisible()[2] + 1 == desEnMain.getFaceVisible()[3] &&
                desEnMain.getFaceVisible()[3] + 1 == desEnMain.getFaceVisible()[4];
    }

}
