package src;

import java.util.List;
import java.util.Random;

public class DesEnMain {
    public int nbrFace = 6;
    public int nbrDes = 5;
    public int[] faceVisible = new int[nbrDes];


    public DesEnMain() {
    }

    public int lancerUnDe() {
        Random random = new Random();
        return random.nextInt(nbrFace) + 1;
    }

    public int[] lancerXDe() {
        for (int i = 0; i < nbrDes; i++) {
            faceVisible[i] += lancerUnDe();
        }
        return faceVisible;
    }
}
