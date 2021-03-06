package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.*;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.Validoija;
import tkht.shakkisivusto.logiikka.Vuorotarkastaja;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class SuoritaVuoro extends PeliHelper{

    private VuoroDao vuoroDao;
    private PelinPelaajaDao ppDao;
    
    private Vuorotarkastaja vuorotarkastaja;
    
    public SuoritaVuoro(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm) {
        super("pelisivu", true, false, peliDao, sm);
        
        this.vuoroDao = vuoroDao;
        this.ppDao = pelinPelaajaDao;
        this.vuorotarkastaja = new Vuorotarkastaja();
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(onkoVuorossa(kirjautunut, peli)){
            String mista = rqst.queryParams("mista");
            String minne = rqst.queryParams("minne");

            if(!Validoija.tarkistaKoordinaatti(mista) || !Validoija.tarkistaKoordinaatti(minne)){
                ilmoitaVirhe("Virheellinen koordinaatti", peli);
                return;
            }
            
            tarkistaVuoro(rqst, rspns, map, kirjautunut, peli);
            return;
        }
        
        ilmoitaVirhe("Ei ole vuorosi", peli);
    }
    
    private boolean onkoVuorossa(Pelaaja pelaaja, Peli peli){
        if(peli.getPelaaja2() == null){
            return false;
        }
        
        Vuoro uusin = peli.getUusinVuoro();
        
        return uusin.getPelaajaid() == pelaaja.getIndeksi();
    }
    
    private void tarkistaVuoro(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception{
        Vuoro uusiTilanne = evaluoiVuoro(rqst, peli);

        if(uusiTilanne == null){
            ilmoitaVirhe("Laiton siirto", peli);
            return;
        }

        vuoroDao.add(uusiTilanne);

        if(vuorotarkastaja.voittiko()){
            asetaVoittaja(peli, kirjautunut);
            rspns.redirect(peli.getVoittosivu());
        }else{
            rspns.redirect(peli.getPelisivu());
        }
    }
    
    private Vuoro evaluoiVuoro(Request rqst, Peli peli){
        String mista = rqst.queryParams("mista");
        String minne = rqst.queryParams("minne");
        
        Vuoro uusinVuoro = peli.getUusinVuoro();

        vuorotarkastaja.asetaPelitilanne(uusinVuoro, peli);
        return vuorotarkastaja.toteutaSiirto(mista, minne);
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

    @Override
    protected String virheSivu(Peli peli) {
        return peli.getPelisivu();
    }
    
}
