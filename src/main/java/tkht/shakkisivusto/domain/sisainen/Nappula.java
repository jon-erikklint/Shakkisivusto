package tkht.shakkisivusto.domain.sisainen;

public class Nappula {
    
    private Nappulatyyppi tyyppi;
    private Ruutu sijainti;
    private int indeksi;
    private boolean valkoinen;

    public Nappula(Nappulatyyppi tyyppi, int indeksi, boolean valkoinen) {
        this.tyyppi = tyyppi;
        this.indeksi = indeksi;
        this.valkoinen = valkoinen;
    }
    
    public Nappula(String teksti){
        if(teksti.charAt(0) == 'm'){
            valkoinen = false;
        }else{
            valkoinen = true;
        }
        
        tyyppi = Nappulatyyppi.getTyyppi(teksti.charAt(1));
        indeksi = Integer.parseInt(""+teksti.charAt(2));
        
        sijainti = new Ruutu(teksti.substring(4));
    }

    public Nappulatyyppi getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(Nappulatyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }

    public int getIndeksi() {
        return indeksi;
    }

    public void setIndeksi(int indeksi) {
        this.indeksi = indeksi;
    }

    public boolean isValkoinen() {
        return valkoinen;
    }

    public void setValkoinen(boolean valkoinen) {
        this.valkoinen = valkoinen;
    }
}
