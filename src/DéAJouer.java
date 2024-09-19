package src;

import java.util.Random;

public class DéAJouer {
    private int nbrFaces;
    private int faceVisible;

    /**
     * Construit un dé à jouer avec le nombre de face donné, la face visible donnée et la couleur donnée
     * @param nbrFaces Nombre de face du dé
     */
    public DéAJouer(int nbrFaces) {
        setNbreFaces(nbrFaces);
    }

    public int getNbrFaces() {
        return nbrFaces;
    }

    public int getFaceVisible() {
        return faceVisible;
    }

    public void setFaceVisible(int faceVisible) {
        this.faceVisible = faceVisible;
    }

    public void setNbreFaces(int nbreFaces) {
        if (nbreFaces >= 2) {
            this.nbrFaces = nbreFaces;
        }
    }

    /**
     * lance ce dé
     *
     * @return la face visible de ce dé
     */
    private int lancer() {
        Random random = new Random();
        faceVisible = random.nextInt(nbrFaces) + 1;
        return faceVisible;
    }
}
