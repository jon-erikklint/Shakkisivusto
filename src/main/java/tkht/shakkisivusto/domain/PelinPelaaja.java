package tkht.shakkisivusto.domain;

public class PelinPelaaja {
    
    private int pelaaja;
    private int peli;

    public PelinPelaaja(int pelaaja, int peli) {
        this.pelaaja = pelaaja;
        this.peli = peli;
    }

    public int getPelaaja() {
        return pelaaja;
    }

    public void setPelaaja(int pelaaja) {
        this.pelaaja = pelaaja;
    }

    public int getPeli() {
        return peli;
    }

    public void setPeli(int peli) {
        this.peli = peli;
    }
}
