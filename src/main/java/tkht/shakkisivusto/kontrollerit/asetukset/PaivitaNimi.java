package tkht.shakkisivusto.kontrollerit.asetukset;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class PaivitaNimi extends KirjautunutHelper{

    private PelaajaDao pelaajaDao;
    
    public PaivitaNimi(PelaajaDao pelaajaDao, SessionManager sm) {
        super(sm, "asetukset");
        
        this.pelaajaDao = pelaajaDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        String uusiNimi = rqst.queryParams("uusinimi");
        
        pelaajaDao.paivitaNimi(kirjautunut, uusiNimi);
    }
    
}
