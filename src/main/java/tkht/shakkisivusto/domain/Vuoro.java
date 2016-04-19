package tkht.shakkisivusto.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import tkht.shakkisivusto.domain.sisainen.*;

public class Vuoro {
    
    private int vuoro;
    private int peliid;
    private int pelaaja;
    private Timestamp tekoaika;
    
    private Peli peli;
    private Pelaaja kenenVuoro;
    
    private List<Nappula> nappulat;
    private Erikoistilanteet erikoistilanteet;
    
    public Vuoro(int vuoro, int peliid, int pelaaja, Timestamp tekoaika, String lauta, String erikoistilanteet){
        this.vuoro = vuoro;
        this.peliid = peliid;
        this.pelaaja = pelaaja;
        this.tekoaika = tekoaika;
        
        this.erikoistilanteet = new Erikoistilanteet(erikoistilanteet);
        
        alustaLauta(lauta);
    }
    
    public Vuoro(int peliid, int pelaaja){
        this.vuoro = 0;
        this.peliid = peliid;
        this.pelaaja = pelaaja;
        this.tekoaika = Timestamp.from(Instant.now());
        
        this.erikoistilanteet = new Erikoistilanteet(Alku.alkutilanteet);
        
        alustaLauta(Alku.alkulauta);
    }
    
    public void alustaLauta(String lauta){
        nappulat = new ArrayList<>();
        
        int alku = 0;
        for(int i = 0 ; i < lauta.length() ; i++){
            if(lauta.charAt(i) == ','){
                
                Nappula uusiNappula = new Nappula(lauta.substring(alku, i));
                nappulat.add(uusiNappula);
                
                alku = i+1;
            }
        }
    }
    
    public String getErikoistilanteetString(){
        return "";
    }
    
    public String getNappulatString(){
        return "";
    }

    public int getVuoro() {
        return vuoro;
    }

    public void setVuoro(int vuoro) {
        this.vuoro = vuoro;
    }

    public Timestamp getTekoaika() {
        return tekoaika;
    }

    public void setTekoaika(Timestamp tekoaika) {
        this.tekoaika = tekoaika;
    }

    public Peli getPeli() {
        return peli;
    }

    public void setPeli(Peli peli) {
        this.peli = peli;
    }

    public Pelaaja getKenenVuoro() {
        return kenenVuoro;
    }

    public void setKenenVuoro(Pelaaja kenenVuoro) {
        this.kenenVuoro = kenenVuoro;
    }

    public List<Nappula> getNappulat() {
        return nappulat;
    }

    public void setNappulat(List<Nappula> nappulat) {
        this.nappulat = nappulat;
    }

    public int getPeliid() {
        return peliid;
    }

    public void setPeliid(int peliid) {
        this.peliid = peliid;
    }

    public int getPelaaja() {
        return pelaaja;
    }

    public void setPelaaja(int pelaaja) {
        this.pelaaja = pelaaja;
    }

    public Erikoistilanteet getErikoistilanteet() {
        return erikoistilanteet;
    }

    public void setErikoistilanteet(Erikoistilanteet erikoistilanteet) {
        this.erikoistilanteet = erikoistilanteet;
    }
    
    
}
