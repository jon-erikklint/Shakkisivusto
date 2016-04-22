package tkht.shakkisivusto.kontrollerit.asetukset;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class PoistaKayttaja extends KirjautunutHelper{

    private PelaajaDao pelaajaDao;
    
    public PoistaKayttaja(PelaajaDao pelaajaDao, SessionManager sm) {
        super(sm, "asetukset");
        
        this.pelaajaDao = pelaajaDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        String nimitarkistus = rqst.queryParams("nimitarkistus");
        String salasana = rqst.queryParams("salasana");
        
        if(kirjautunut.getSalasana().equals(salasana) && kirjautunut.getKayttajanimi().equals(nimitarkistus)){
            pelaajaDao.deleteCascade(kirjautunut);
            
            String session = rqst.cookie("session");
            sm.removeSession(session);
            rspns.cookie("session", "", 0);
            
            rspns.redirect("/");
        }else{
            rspns.redirect("asetukset");
        }
    }
    
}
