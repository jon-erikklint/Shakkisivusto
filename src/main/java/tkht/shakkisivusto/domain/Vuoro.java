package tkht.shakkisivusto.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import tkht.shakkisivusto.domain.sisainen.*;
import tkht.shakkisivusto.tietokanta.luojat.Yhdistettava;

public class Vuoro implements Yhdistettava{
    
    private int vuoro;
    private int peliid;
    private int pelaajaid;
    private Timestamp tekoaika;
    
    private Peli peli;
    private Pelaaja pelaaja;
    
    private List<Nappula> nappulat;
    private Erikoistilanteet erikoistilanteet;
    
    public Vuoro(int vuoro, int peliid, int pelaaja, Timestamp tekoaika, String lauta, String erikoistilanteet){
        this.vuoro = vuoro;
        this.peliid = peliid;
        this.pelaajaid = pelaaja;
        this.tekoaika = tekoaika;
        
        this.erikoistilanteet = new Erikoistilanteet(erikoistilanteet);
        
        alustaLauta(lauta);
    }
    
    public Vuoro(int peliid, int pelaaja){
        this.vuoro = 0;
        this.peliid = peliid;
        this.pelaajaid = pelaaja;
        this.tekoaika = Timestamp.from(Instant.now());
        
        this.erikoistilanteet = new Erikoistilanteet(Alku.alkutilanteet);
        
        System.out.println(Alku.alkulauta);
        
        alustaLauta(Alku.alkulauta);
    }
    
    public Vuoro(){}
    
    public void alustaLauta(String lauta){
        nappulat = new ArrayList<>();
        
        System.out.println(lauta.length());
        
        int alku = 0;
        for(int i = 0 ; i < lauta.length() ; i++){
            System.out.println(lauta.charAt(i));
            if(lauta.charAt(i) == ','){
                System.out.println("UUSI NAPPULA LUODAAN");
                Nappula uusiNappula = new Nappula(lauta.substring(alku, i));
                nappulat.add(uusiNappula);
                System.out.println("UUSI NAPPULA: "+uusiNappula);
                
                alku = i+1;
            }
        }
    }
    
    public List<List<String>> getRuudut(){
        List<List<String>> kartta = new ArrayList<>();
        
        for(int i = 0 ; i < 8 ; i++){
            List<String> rivi = new ArrayList<>();
            for(int j = 0 ; j < 8 ; j++){
                rivi.add(" 0 ");
            }
            kartta.add(rivi);
        }
        
        for(Nappula nappula : nappulat){
            Ruutu sijainti = nappula.getSijainti();
            
            int x = sijainti.getX();
            int y = sijainti.getY();
            
            List<String> oikeaRivi = kartta.get(y);
            oikeaRivi.set(x, nappula.karttaString());
        }
        
        return kartta;
    }
    
    public String getErikoistilanteetString(){
        return erikoistilanteet.toString();
    }
    
    public String getNappulatString(){
        String koodattu = "";
        
        for(int i = 0 ; i < nappulat.size() ; i++){
            Nappula nappula = nappulat.get(i);
            koodattu += nappula.toString();
            
            if(i < nappulat.size() - 1){
                koodattu += ":";
            }
        }
        
        return koodattu;
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

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
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

    public int getPelaajaid() {
        return pelaajaid;
    }

    public void setPelaajaid(int pelaajaid) {
        this.pelaajaid = pelaajaid;
    }

    public Erikoistilanteet getErikoistilanteet() {
        return erikoistilanteet;
    }

    public void setErikoistilanteet(Erikoistilanteet erikoistilanteet) {
        this.erikoistilanteet = erikoistilanteet;
    }

    @Override
    public boolean yhdista(Object o) {
        if(o == null){
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
        
        if(o.getClass() == Pelaaja.class){
            Pelaaja p = (Pelaaja) o;
            
            if(p.getIndeksi() == this.pelaajaid){
                this.pelaaja = p;
                
                return true;
            }
            
            return false;
        }
        
        return false;
    }
    
}
