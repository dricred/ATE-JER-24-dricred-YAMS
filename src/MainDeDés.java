package src;

public class MainDeDés extends DéAJouer {
    private int nbrDesAJouer = 5;
    private int[] tabDesEnMain = new int [nbrDesAJouer];

    public int[] lancerDesEnMain () {
        for (int i = 0; i < nbrDesAJouer; i++) {
            tabDesEnMain[i] = lancer();
        }
        return lesDes;
    }

    public void affchiageMainDeDés() {
        int[] tabFaceVisible = lancerDesEnMain();
        for (int i = 0; i < tabFaceVisible.length; i++) {
            System.out.println("Dé [" + (i + 1) + "] : " + tabFaceVisible[i]);
        }
    }
}
