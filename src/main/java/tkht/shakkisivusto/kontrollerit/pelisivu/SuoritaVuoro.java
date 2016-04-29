package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.*;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.logiikka.Vuorotarkastaja;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class SuoritaVuoro extends PeliHelper{

    private VuoroDao vuoroDao;
    private PelinPelaajaDao ppDao;
    
    private Vuorotarkastaja vuorotarkastaja;
    
    public SuoritaVuoro(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm) {
        super(peliDao, sm);
        
        this.vuoroDao = vuoroDao;
        this.ppDao = pelinPelaajaDao;
        this.vuorotarkastaja = new Vuorotarkastaja();
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(!tarkistaPaasy(kirjautunut, peli, map)){
            return;
        }
        
        if(onkoVuorossa(kirjautunut, peli)){
            tarkistaVuoro(rqst, rspns, map, kirjautunut, peli);
            return;
        }
        
        ilmoitaVirhe("Ei ole vuorosi", map);
    }
    
    private boolean onkoVuorossa(Pelaaja pelaaja, Peli peli){
        Vuoro uusin = peli.getUusinVuoro();
        
        return uusin.getPelaajaid() == pelaaja.getIndeksi();
    }
    
    private void tarkistaVuoro(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception{
        Vuoro uusiTilanne = evaluoiVuoro(rqst, peli);

        if(uusiTilanne == null){
            ilmoitaVirhe("Laiton siirto", map);
            return;
        }

        vuoroDao.add(uusiTilanne);

        if(vuorotarkastaja.voittiko()){
            asetaVoittaja(peli, kirjautunut);
            rspns.redirect("/peli/"+peli.getId()+"/voitit");
        }else{
            rspns.redirect("/peli"+peli.getId());
        }
    }
    
    private Vuoro evaluoiVuoro(Request rqst, Peli peli){
        String siirto = rqst.queryParams("siirto");
        Vuoro uusinVuoro = peli.getUusinVuoro();

        vuorotarkastaja.asetaPelitilanne(uusinVuoro, peli);
        return vuorotarkastaja.toteutaSiirto(siirto);
    }
    
    private void asetaVoittaja(Peli peli, Pelaaja pelaaja) throws Exception{
        PelinPelaaja pp;
        if(peli.getPelaaja1().getPelaajaid() == pelaaja.getIndeksi()){
            pp = peli.getPelaaja1();
        }else{
            pp = peli.getPelaaja2();
        }
        
        pp.setVoittaja(true);
        
        ppDao.paivitaVoittaja(pp);
        peliDao.paivitaPelistatus(peli.getId(), "LOPPUNUT");
    }
    
}
