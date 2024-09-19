package src;

public class DéAJouer {
    private int nbrFace = 6;
    private int faceVisible;

    public DéAJouer() {

    }

    public int lancer() {
        faceVisible = (int)(Math.random() * nbrFace + 1);
        return faceVisible;
    }

    @Override
    public boolean equals(Object autreObj) {
            return autreObj instanceof DéAJouer &&
                    this.nbrFace == ((DéAJouer)autreObj).nbrFace &&
                    this.faceVisible == ((DéAJouer)autreObj).faceVisible;
    }
}
