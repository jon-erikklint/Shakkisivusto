package tkht.shakkisivusto.domain;

public class Pelaaja {
    
    private int indeksi;
    private String nimi;
    private String kayttajanimi;
    private boolean admin;

    public Pelaaja(int indeksi, String nimi, String kayttajanimi, boolean admin) {
        this.indeksi = indeksi;
        this.nimi = nimi;
        this.kayttajanimi = kayttajanimi;
        this.admin = admin;
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
}