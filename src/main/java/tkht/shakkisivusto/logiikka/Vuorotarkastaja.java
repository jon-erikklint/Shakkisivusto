package tkht.shakkisivusto.logiikka;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;
import tkht.shakkisivusto.domain.sisainen.*;

public class Vuorotarkastaja {
    
    private Nappula omaKuningas;
    private boolean valkoinen;
    
    private Map<Ruutu, Nappula> kartta;
    private Erikoistilanteet uusiTilanne;
    
    private Vuoro vuoro;
    private Peli peli;
    
    public void asetaPelitilanne(Vuoro vuoro, Peli peli){
        this.vuoro = vuoro;
        this.peli = peli;
        this.uusiTilanne = new Erikoistilanteet(vuoro.getErikoistilanteet().toString());
        
        valkoinen = vuoro.getVuoro() % 2 == 0;
        
        kartta = new HashMap<>();
        for(Nappula nappula : vuoro.getNappulat()){
            if(nappula.getTyyppi() == Nappulatyyppi.KUNINGAS && nappula.isValkoinen() == valkoinen){
                omaKuningas = nappula;
            }
            
            kartta.put(nappula.getSijainti(), nappula);
        }
    }
    
    public Vuoro toteutaSiirto(String mist, String mihi){
        Ruutu mista = new Ruutu(mist);
        Ruutu minne = new Ruutu(mihi);
        Nappula siirrettava = kartta.get(mista);
        
        if(siirrettava == null){
            return null;
        }
        
        if(!voikoSiirtya(siirrettava, minne)){
            return null;
        }
        
        return luoUusiVuoro(siirrettava, minne);
    }
    
    private Vuoro luoUusiVuoro(Nappula siirrettava, Ruutu minne){
        siirraNappula(siirrettava, minne);
        int pelaajaid;
        if(vuoro.getVuoro()+1 % 2 == 0){
            pelaajaid = peli.getPelaaja1().getPelaajaid();
        }else{
            pelaajaid = peli.getPelaaja2().getPelaajaid();
        }
        
        Vuoro uusiVuoro = new Vuoro();
        uusiVuoro.setPeliid(vuoro.getPeliid());
        uusiVuoro.setVuoro(vuoro.getVuoro()+1);
        uusiVuoro.setNappulat(new ArrayList<>(kartta.values()));
        uusiVuoro.setPelaajaid(pelaajaid);
        uusiVuoro.setErikoistilanteet(uusiTilanne);
        uusiVuoro.setTekoaika(Timestamp.from(Instant.now()));
        
        return uusiVuoro;
    }
    
    private void siirraNappula(Nappula siirrettava, Ruutu minne){
        kartta.remove(siirrettava.getSijainti());
        kartta.put(minne, siirrettava);
        siirrettava.setSijainti(minne);
    }
    
    private boolean voikoSiirtya(Nappula siirrettava, Ruutu minne){
        if(!pystyySiirtymaan(siirrettava, minne)){
            return false;
        }
        
        return eiItseaiheutettuShakki(siirrettava, minne);
    }
    
    private boolean pystyySiirtymaan(Nappula siirrettava, Ruutu minne){
        boolean voikoSiirtya = false;
        
        switch(siirrettava.getTyyppi()){
            case SOTILAS:
                voikoSiirtya = sotilas(siirrettava, minne);
                break;
            case LAHETTI:
                voikoSiirtya = lahetti(siirrettava, minne);
                break;
            case RATSU:
                voikoSiirtya = ratsu(siirrettava, minne);
                break;
            case TORNI:
                voikoSiirtya = torni(siirrettava, minne);
                break;
            case KUNINGATAR:
                voikoSiirtya = kuningatar(siirrettava, minne);
                break;
            case KUNINGAS:
                voikoSiirtya = kuningas(siirrettava, minne);
                break;
        }
        
        return voikoSiirtya;
    }
    
    private boolean sotilas(Nappula nappula, Ruutu kohde){
        return false;
    }
    
    private boolean lahetti(Nappula nappula, Ruutu kohde){
        return false;
    }
    
    private boolean ratsu(Nappula nappula, Ruutu kohde){
        return false;
    }
    
    private boolean torni(Nappula nappula, Ruutu kohde){
        return false;
    }
    
    private boolean kuningas(Nappula nappula, Ruutu kohde){
        return false;
    }
    
    private boolean kuningatar(Nappula nappula, Ruutu kohde){
        return false;
    }
    
    private boolean eiItseaiheutettuShakki(Nappula nappula, Ruutu kohde){
        Nappula kohdenappula = kartta.get(kohde);
        kartta.put(kohde, nappula);
        kartta.put(nappula.getSijainti(), null);
        
        boolean eiItseaiheutettuShakki = true;
        
        for(Nappula kokeiltava : kartta.values()){
            if(pystyySiirtymaan(kokeiltava, kohde)){
                eiItseaiheutettuShakki = false;
                break;
            }
        }
        
        kartta.put(nappula.getSijainti(), nappula);
        if(kohdenappula != null){
            kartta.put(kohde, kohdenappula);
        }else{
            kartta.put(kohde, null);
        }
        
        return eiItseaiheutettuShakki;
    }
    
    public boolean voittiko(){
        return false;
    }
}
