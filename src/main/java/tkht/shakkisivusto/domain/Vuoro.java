package tkht.shakkisivusto.domain;

import java.sql.Timestamp;
import java.util.*;
import tkht.shakkisivusto.domain.sisainen.*;

public class Vuoro {
    
    private int vuoro;
    private Timestamp tekoaika;
    
    private Peli peli;
    private Pelaaja kenenVuoro;
    
    private List<Nappula> nappulat;
    
    public Vuoro(int vuoro, Timestamp tekoaika, String lauta){
        this.vuoro = vuoro;
        this.tekoaika = tekoaika;
        
        alustaLauta(lauta);
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
    
}
