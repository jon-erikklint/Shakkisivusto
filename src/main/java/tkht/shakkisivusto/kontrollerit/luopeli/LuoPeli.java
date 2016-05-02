package tkht.shakkisivusto.kontrollerit.luopeli;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.PelinPelaaja;
import tkht.shakkisivusto.domain.Vuoro;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.Validoija;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class LuoPeli extends KirjautunutHelper{

    PeliDao peliDao;
    PelinPelaajaDao ppDao;
    VuoroDao vuoroDao;
    
    public LuoPeli(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm) {
        super(sm, "luopeli");
        
        this.peliDao = peliDao;
        this.ppDao = pelinPelaajaDao;
        this.vuoroDao = vuoroDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        String pelinNimi = rqst.queryParams("nimi");
        
        if(!Validoija.tarkistaPelinNimi(pelinNimi)){
            rspns.redirect("/uusipeli");
            return;
        }
        
        Peli uusiPeli = luoPeli(pelinNimi);
        
        luoPelinPelaaja(uusiPeli, kirjautunut);
        luoEnsimmainenVuoro(uusiPeli, kirjautunut);
        
        rspns.redirect("/peli/"+uusiPeli.getId());
    }
    
    private Peli luoPeli(String pelinNimi) throws Exception{
        Peli uusiPeli = new Peli(pelinNimi);
        peliDao.add(uusiPeli);
        
        return peliDao.findNewest();
    }
    
    private void luoPelinPelaaja(Peli uusiPeli, Pelaaja kirjautunut) throws Exception{
        PelinPelaaja pp = new PelinPelaaja(kirjautunut.getIndeksi(), uusiPeli.getId(), true);
        
        ppDao.add(pp);
    }
    
    private void luoEnsimmainenVuoro(Peli peli, Pelaaja kirjautunut) throws Exception{
        Vuoro vuoro = new Vuoro(peli.getId(), kirjautunut.getIndeksi());
        
        vuoroDao.add(vuoro);
    }
}
