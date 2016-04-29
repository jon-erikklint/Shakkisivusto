package tkht.shakkisivusto.logiikka;

import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;

public class Vuorotarkastaja {
    
    private Vuoro vuoro;
    private Peli peli;
    
    public void asetaPelitilanne(Vuoro vuoro, Peli peli){
        this.vuoro = vuoro;
        this.peli = peli;
    }
    
    public Vuoro toteutaSiirto(String siirto){
        return null;
    }
    
    public boolean voittiko(){
        return false;
    }
}
