package src;

import java.util.List;
import java.util.Random;

public class DesEnMain {
    private int nbrFace = 6;
    private int nbrDes = 5;
    private int[] faceVisible = new int[nbrDes];

    /**
     * Construit une main de dés avec des valeurs par défauts
     */
    public DesEnMain() {
    }

    public int getNbrFace() {
        return nbrFace;
    }

    public int getNbrDes() {
        return nbrDes;
    }

    public int[] getFaceVisible() {
        return faceVisible;
    }

    public void setNbrFace(int nbrFace) {
        this.nbrFace = nbrFace;
    }

    public void setNbrDes(int nbrDes) {
        this.nbrDes = nbrDes;
    }

    public void setFaceVisible(int[] faceVisible) {
        for (int face : faceVisible) {
            if (face > nbrFace) {
                return;
            }
        }
        this.faceVisible = faceVisible;
    }

    /**
     * lance un dé de cette main
     * @return la face visible du dé de cette main
     */
    public int lancerUnDe() {
        Random random = new Random();
        return random.nextInt(nbrFace) + 1;
    }

    /**
     * lance un ou plusieurs dés de cette main
     * @return les de faces dés de cette main
     */
    public int[] lancerXDe() {
        int[] desFaceVisible = new int[nbrDes];
        for (int i = 0; i < nbrDes; i++) {
            desFaceVisible[i] += lancerUnDe();
        }
        setFaceVisible(desFaceVisible);
        return faceVisible;
    }
}
