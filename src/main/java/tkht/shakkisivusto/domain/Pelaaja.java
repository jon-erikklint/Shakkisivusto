package tkht.shakkisivusto.domain;

public class Pelaaja {
    
    private int indeksi;
    private String nimi;
    private String kayttajanimi;
    private String salasana;
    private boolean admin;
    
    private int voittoja;
    private int tappioita;

    public Pelaaja(int indeksi, String nimi, String kayttajanimi, String salasana, boolean admin) {
        this.indeksi = indeksi;
        this.nimi = nimi;
        this.kayttajanimi = kayttajanimi;
        this.admin = admin;
        this.salasana = salasana;
    }
    
    public Pelaaja(){}
    
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
}
