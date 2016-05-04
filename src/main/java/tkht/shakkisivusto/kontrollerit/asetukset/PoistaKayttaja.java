package tkht.shakkisivusto.kontrollerit.asetukset;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.Poistaja;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class PoistaKayttaja extends KirjautunutHelper{

    private Poistaja poistaja;
    
    public PoistaKayttaja(PelaajaDao pelaajaDao, PelinPelaajaDao pelinPelaajaDao, PeliDao peliDao, VuoroDao vuoroDao, SessionManager sm) {
        super(sm, "asetukset");
        
        poistaja = new Poistaja(pelaajaDao, peliDao, pelinPelaajaDao, vuoroDao);
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        String nimitarkistus = rqst.queryParams("nimitarkistus");
        String salasana = rqst.queryParams("salasana");
        
        if(kirjautunut.getSalasana().equals(salasana) && kirjautunut.getKayttajanimi().equals(nimitarkistus)){
            poistaja.poistaKayttaja(kirjautunut);
            
            String session = rqst.cookie("session");
            sm.removeSession(session);
            rspns.cookie("session", "", 0);
            
            rspns.redirect("/");
        }else{
            rspns.redirect("asetukset");
        }
    }
    
}
