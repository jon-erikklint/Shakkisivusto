package tkht.shakkisivusto.domain;

import java.sql.Timestamp;

public class Peli {
    
    private String nimi;
    private String status;
    
    private int vuoro;
    private Timestamp edellisenVuoronPelaamisaika;
    private Pelaaja kenenVuoro;
    
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    
    public Peli(String nimi, String status){
        this.nimi = nimi;
        this.status = status;
    }
    
    public void alustaPelaajat(Pelaaja pelaaja1, Pelaaja pelaaja2){
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }
    
    public void alustaVuoro(int vuoro, Timestamp vuorontekoaika, int pelaajaindeksi){
        this.vuoro = vuoro;
        this.edellisenVuoronPelaamisaika = vuorontekoaika;
        
        if(pelaajaindeksi == pelaaja1.getIndeksi()){
            kenenVuoro = pelaaja1;
        }else{
            kenenVuoro = pelaaja2;
        }
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

    public int getVuoro() {
        return vuoro;
    }

    public void setVuoro(int vuoro) {
        this.vuoro = vuoro;
    }

    public Timestamp getEdellisenVuoronPelaamisaika() {
        return edellisenVuoronPelaamisaika;
    }

    public void setEdellisenVuoronPelaamisaika(Timestamp edellisenVuoronPelaamisaika) {
        this.edellisenVuoronPelaamisaika = edellisenVuoronPelaamisaika;
    }

    public Pelaaja getKenenVuoro() {
        return kenenVuoro;
    }

    public void setKenenVuoro(Pelaaja kenenVuoro) {
        this.kenenVuoro = kenenVuoro;
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
}
