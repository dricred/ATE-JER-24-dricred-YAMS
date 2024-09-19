package src;

public class MainDeDés extends DéAJouer {
    private int nbrDesAJouer = 5;
    private int[] tabDesEnMain = new int [nbrDesAJouer];

    public int[] lancerDesEnMain () {
        for (int i = 0; i < nbrDesAJouer; i++) {
            tabDesEnMain[i] = lancer();
        }
        return tabDesEnMain;
    }

    public void affchiageMainDeDés() {
        for (int i = 0; i < tabDesEnMain.length; i++) {
            System.out.println("Dé [" + (i + 1) + "] : " + tabDesEnMain[i]);
        }
    }
}
