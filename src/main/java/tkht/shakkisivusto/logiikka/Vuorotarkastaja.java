package tkht.shakkisivusto.logiikka;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        
        if(siirrettava.isValkoinen() != valkoinen){
            return null;
        }
        
        if(!tarkistaSiirto(siirrettava, minne)){
            return null;
        }
        
        return luoUusiVuoro(siirrettava, minne);
    }
    
    private Vuoro luoUusiVuoro(Nappula siirrettava, Ruutu minne){
        siirraNappula(siirrettava, minne);
        int pelaajaid;
        System.out.println(vuoro.getVuoro());
        if((vuoro.getVuoro()+1) % 2 == 0){
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
    
    private boolean tarkistaSiirto(Nappula siirrettava, Ruutu minne){
        if(!pystyySiirtymaan(siirrettava, minne)){
            return false;
        }
        
        return !itseaiheutettuShakki(siirrettava, minne);
    }
    
    private boolean pystyySiirtymaan(Nappula siirrettava, Ruutu minne){
        Set<Ruutu> siirryttavat;
        
        Ruutu sijainti = siirrettava.getSijainti();
        
        switch(siirrettava.getTyyppi()){
            case SOTILAS:
                siirryttavat = sotilas(sijainti);
                break;
            case LAHETTI:
                siirryttavat = lahetti(sijainti);
                break;
            case RATSU:
                siirryttavat = ratsu(sijainti);
                break;
            case TORNI:
                siirryttavat = torni(sijainti);
                break;
            case KUNINGATAR:
                siirryttavat = kuningatar(sijainti);
                break;
            case KUNINGAS:
                siirryttavat = kuningas(sijainti);
                break;
            default:
                siirryttavat = new HashSet<>();
        }
        
        return siirryttavat.contains(minne);
    }
    
    private Set<Ruutu> sotilas(Ruutu sijainti){
        Set<Ruutu> liikuttavat = new HashSet<>();
        
        Suunta liikkuminen;
        List<Suunta> hyokkaykset;
        if(valkoinen){
            liikkuminen = Suunta.valkoinenLiike();
            hyokkaykset = Suunta.valkoisenHyokkaykset();
        }else{
            liikkuminen = Suunta.mustaLiike();
            hyokkaykset = Suunta.mustanHyokkaykset();
        }
        
        liikuttavat.addAll(sotilaanLiikkeet(sijainti, liikkuminen));
        liikuttavat.addAll(sotilaanSyonnit(sijainti, hyokkaykset));
        
        return liikuttavat;
    }
    
    private Set<Ruutu> sotilaanSyonnit(Ruutu mista, List<Suunta> suunnat){
        Set<Ruutu> syotavat = new HashSet<>();
        
        for(Suunta suunta : suunnat){
            Ruutu hyokattava = mista.getSuunnasta(suunta);
            
            Siirtotyyppi tyyppi = tarkistaPaasy(hyokattava);
            if(tyyppi == Siirtotyyppi.SYONTI){
                syotavat.add(hyokattava);
            }
        }
        
        return syotavat;
    }
    
    private Set<Ruutu> sotilaanLiikkeet(Ruutu mista, Suunta suunta){
        Set<Ruutu> liikuttavat = new HashSet<>();
        
        liikuttavat.addAll(sotilaanLiike(mista, suunta));
        
        if(sotilaanKaksoisliikeMahdollinen(mista) && !liikuttavat.isEmpty()){
            liikuttavat.addAll(sotilaanLiike(mista.getSuunnasta(suunta), suunta));
        }
        
        return liikuttavat;
    }
    
    private Set<Ruutu> sotilaanLiike(Ruutu mista, Suunta suunta){
        Set<Ruutu> liikuttava = new HashSet<>();
        
        Ruutu minne = mista.getSuunnasta(suunta);
        Siirtotyyppi siirto = tarkistaPaasy(minne);
        
        if(siirto == Siirtotyyppi.SIIRTO){
            liikuttava.add(minne);
        }
        
        return liikuttava;
    }
    
    private boolean sotilaanKaksoisliikeMahdollinen(Ruutu ruutu){
        if(valkoinen){
            return ruutu.getY() == 7;
        }
        
        return ruutu.getY() == 2;
    }
    
    private Set<Ruutu> lahetti(Ruutu sijainti){
        Set<Ruutu> siirrettavat = new HashSet<>();
        
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAOIKEA, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAVASEN, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLAOIKEA, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLAVASEN, 10));
        
        return siirrettavat;
    }
    
    private Set<Ruutu> ratsu(Ruutu sijainti){
        List<Ruutu> teoreettiset = new ArrayList<>();
        teoreettiset.add(sijainti.getVerraten(2, 1));
        teoreettiset.add(sijainti.getVerraten(2, -1));
        teoreettiset.add(sijainti.getVerraten(-2, -1));
        teoreettiset.add(sijainti.getVerraten(-2, 1));
        teoreettiset.add(sijainti.getVerraten(1, 2));
        teoreettiset.add(sijainti.getVerraten(1, -2));
        teoreettiset.add(sijainti.getVerraten(-1, -2));
        teoreettiset.add(sijainti.getVerraten(-1, 2));
        
        Set<Ruutu> oikeat = new HashSet<>();
        for(Ruutu teoreettinen : teoreettiset){
            if(tarkistaPaasy(teoreettinen) != Siirtotyyppi.EISIIRTO){
                oikeat.add(teoreettinen);
            }
        }
        
        return oikeat;
    }
    
    private Set<Ruutu> torni(Ruutu sijainti){
        Set<Ruutu> siirrettavat = new HashSet<>();
        
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAS, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.OIKEA, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.VASEN, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLOS, 10));
        
        return siirrettavat;
    }
    
    private Set<Ruutu> kuningas(Ruutu sijainti){
        Set<Ruutu> siirrettavat = new HashSet<>();
        
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAS, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.OIKEA, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.VASEN, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLOS, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAOIKEA, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAVASEN, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLAOIKEA, 1));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLAVASEN, 1));
        
        return siirrettavat;
    }
    
    private Set<Ruutu> kuningatar(Ruutu sijainti){
        Set<Ruutu> siirrettavat = new HashSet<>();
        
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAS, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.OIKEA, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.VASEN, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLOS, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAOIKEA, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.ALAVASEN, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLAOIKEA, 10));
        siirrettavat.addAll(tarkistaSuunta(sijainti, Suunta.YLAVASEN, 10));
        
        return siirrettavat;
    }
    
    private Set<Ruutu> tarkistaSuunta(Ruutu mista, Suunta suunta, int maksimimaara){
        if(maksimimaara == 0){
            return new HashSet<>();
        }
        
        Ruutu suunnassa = mista.getSuunnasta(suunta);
        
        Siirtotyyppi siirto = tarkistaPaasy(suunnassa);
        
        if(siirto == Siirtotyyppi.EISIIRTO){
            return new HashSet<>();
        }
        
        Set<Ruutu> siirrot;
        
        if(siirto == Siirtotyyppi.SYONTI){
            siirrot = new HashSet<>();
        }else{
            siirrot = tarkistaSuunta(suunnassa, suunta, maksimimaara-1);
        }
        
        siirrot.add(suunnassa);
        return siirrot;
    }
    
    private Siirtotyyppi tarkistaPaasy(Ruutu kohde){
        if(kohde == null){
            return Siirtotyyppi.EISIIRTO;
        }
        
        Nappula kohdenappula = kartta.get(kohde);
        
        if(kohdenappula == null){
            return Siirtotyyppi.SIIRTO;
        }
        
        if(kohdenappula.isValkoinen() == valkoinen){
            return Siirtotyyppi.EISIIRTO;
        }
        
        return Siirtotyyppi.SYONTI;
    }
    
    private boolean itseaiheutettuShakki(Nappula nappula, Ruutu kohde){
        Nappula kohdenappula = kartta.get(kohde);
        kartta.put(kohde, nappula);
        kartta.remove(nappula.getSijainti());
        
        Ruutu kuninkaanSijainti = omaKuningas.getSijainti();
        
        boolean itseaiheutettuShakki = false;
        
        for(Nappula kokeiltava : kartta.values()){
            if(kokeiltava.isValkoinen() == valkoinen){
                continue;
            }
            if(pystyySiirtymaan(kokeiltava, kuninkaanSijainti)){
                itseaiheutettuShakki = true;
                break;
            }
        }
        
        kartta.put(nappula.getSijainti(), nappula);
        if(kohdenappula != null){
            kartta.put(kohde, kohdenappula);
        }
        
        return itseaiheutettuShakki;
    }
    
    public boolean voittiko(){
        return false;
    }
}
