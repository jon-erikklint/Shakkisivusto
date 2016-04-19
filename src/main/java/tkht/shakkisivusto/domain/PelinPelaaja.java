package tkht.shakkisivusto.domain;

public class PelinPelaaja {
    
    private int pelaaja;
    private int peli;
    private boolean valkoinen;

    public PelinPelaaja(int pelaaja, int peli, boolean valkoinen) {
        this.pelaaja = pelaaja;
        this.peli = peli;
        this.valkoinen = valkoinen;
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

    public boolean isValkoinen() {
        return valkoinen;
    }

    public void setValkoinen(boolean valkoinen) {
        this.valkoinen = valkoinen;
    }
}
