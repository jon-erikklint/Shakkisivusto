package tkht.shakkisivusto.domain;

import tkht.shakkisivusto.tietokanta.luojat.Yhdistettava;

public class PelinPelaaja implements Yhdistettava{
    
    private int pelaajaid;
    private int peliid;
    private boolean valkoinen;
    private boolean voittaja;
    
    private Pelaaja pelaaja;
    private Peli peli;

    public PelinPelaaja(int pelaajaid, int peli, boolean valkoinen, boolean voittaja) {
        this.pelaajaid = pelaajaid;
        this.peliid = peli;
        this.valkoinen = valkoinen;
        this.voittaja = voittaja;
    }
    
    public PelinPelaaja(int pelaajaid, int peli, boolean valkoinen){
        this(pelaajaid, peli, valkoinen, false);
    }

    public int getPelaajaid() {
        return pelaajaid;
    }

    public void setPelaajaid(int pelaajaid) {
        this.pelaajaid = pelaajaid;
    }

    public int getPeliid() {
        return peliid;
    }

    public void setPeliid(int peliid) {
        this.peliid = peliid;
    }

    public boolean isValkoinen() {
        return valkoinen;
    }

    public void setValkoinen(boolean valkoinen) {
        this.valkoinen = valkoinen;
    }

    public boolean isVoittaja() {
        return voittaja;
    }

    public void setVoittaja(boolean voittaja) {
        this.voittaja = voittaja;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    public Peli getPeli() {
        return peli;
    }

    public void setPeli(Peli peli) {
        this.peli = peli;
    }

    @Override
    public boolean yhdista(Object o) {
        if(o == null){
            return false;
        }
        
        if(o.getClass() == Pelaaja.class){
            Pelaaja p = (Pelaaja) o;
            
            if(p.getIndeksi() == this.pelaajaid){
                this.pelaaja = p;
                
                return true;
            }
            
            return false;
        }
        
        if(o.getClass() == Peli.class){
            Peli p = (Peli) o;
            
            if(p.getId() == this.peliid){
                this.peli = p;
                
                return true;
            }
            
            return false;
        }
        
        return false;
    }
}
