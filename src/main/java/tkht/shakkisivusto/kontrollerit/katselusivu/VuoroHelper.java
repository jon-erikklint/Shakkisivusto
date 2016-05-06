package tkht.shakkisivusto.kontrollerit.katselusivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.pelisivu.PeliHelper;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public abstract class VuoroHelper extends PeliHelper{

    protected VuoroDao vuoroDao;
    
    public VuoroHelper(String sivu, VuoroDao vuoroDao, PeliDao peliDao, SessionManager sm) {
        super(sivu, peliDao, sm);
        
        this.vuoroDao = vuoroDao;
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(!peli.getStatus().equals("LOPPUNUT")){
            ilmoitaVirhe("Pelin tila väärä", peli);
            return;
        }
        
        String vuoroString = rqst.params(":vuoro");
        
        int vuoronumero;
        try{
            vuoronumero = Integer.parseInt(vuoroString);
        }catch(Exception e){
            super.ilmoitaVirhe("Vuoronumero virheellinen", peli);
            return;
        }
        
        Vuoro vuoro = vuoroDao.findVuoroRambling(peli.getId(), vuoronumero);
        
        if(vuoro == null){
            super.ilmoitaVirhe("Vuoronumero virheellinen", peli);
            return;
        }
        
        map.put("vuoro", vuoro);
        
        handle(rqst, rspns, map, kirjautunut, peli, vuoro);
    }
    
    protected abstract void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli, Vuoro vuoro) throws Exception;
    
}
