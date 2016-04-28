package tkht.shakkisivusto.domain;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.tietokanta.luojat.Yhdistettava;

public class Pelaaja implements Yhdistettava{
    
    private int indeksi;
    private String nimi;
    private String kayttajanimi;
    private String salasana;
    private boolean admin;
    
    private int voittoja;
    private int tappioita;

    private List<PelinPelaaja> pelit;
    private List<Vuoro> vuorot;
    
    public Pelaaja(int indeksi, String nimi, String kayttajanimi, String salasana, boolean admin) {
        this();
        this.indeksi = indeksi;
        this.nimi = nimi;
        this.kayttajanimi = kayttajanimi;
        this.admin = admin;
        this.salasana = salasana;
    }
    
    public Pelaaja(){
        pelit = new ArrayList<>();
        vuorot = new ArrayList<>();
    }
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKayttajanimi() {
        return kayttajanimi;
    }

    public void setKayttajanimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getIndeksi() {
        return indeksi;
    }

    public void setIndeksi(int indeksi) {
        this.indeksi = indeksi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public int getVoittoja() {
        return voittoja;
    }

    public void setVoittoja(int voittoja) {
        this.voittoja = voittoja;
    }

    public int getTappioita() {
        return tappioita;
    }

    public void setTappioita(int tappioita) {
        this.tappioita = tappioita;
    }
    
    public String getLinkki(){
        return "/pelaaja/"+kayttajanimi;
    }
    
    public double getVoittoratio(){
        return ((double)voittoja) / tappioita;
    }

    @Override
    public boolean yhdista(Object o) {
        if(o == null){
            return false;
        }
        
        if(o.getClass() == PelinPelaaja.class){
            PelinPelaaja pp = (PelinPelaaja) o;
            
            if(pp.getPelaajaid() == this.indeksi){
                pelit.add(pp);
                return true;
            }
            
            return false;
        }
        
        if(o.getClass() == Vuoro.class){
            Vuoro v = (Vuoro) o;
            
            if(v.getPelaajaid() == this.indeksi){
                vuorot.add(v);
                return true;
            }
            
            return false;
        }
        
        return false;
    }
}
