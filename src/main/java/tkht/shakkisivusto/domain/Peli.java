package tkht.shakkisivusto.domain;

import java.util.List;

public class Peli {
    
    private int id;
    private String nimi;
    private String status;
    
    private List<Vuoro> vuorot;
    
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    
    public Peli(int id, String nimi, String status){
        this.id = id;
        this.nimi = nimi;
        this.status = status;
    }
    
    public void alustaPelaajat(Pelaaja pelaaja1, Pelaaja pelaaja2){
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }

    public void setPelaaja1(Pelaaja pelaaja1) {
        this.pelaaja1 = pelaaja1;
    }

    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }

    public void setPelaaja2(Pelaaja pelaaja2) {
        this.pelaaja2 = pelaaja2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vuoro> getVuorot() {
        return vuorot;
    }

    public void setVuorot(List<Vuoro> vuorot) {
        this.vuorot = vuorot;
    }
}
