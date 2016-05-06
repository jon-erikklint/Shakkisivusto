package tkht.shakkisivusto.kontrollerit.katselusivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class KatselusivuGet extends VuoroHelper{

    public KatselusivuGet(PeliDao peliDao, VuoroDao vuoroDao, SessionManager sm) {
        super("katselusivu", vuoroDao, peliDao, sm);
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli, Vuoro vuoro) throws Exception {
        if(!super.tarkistaPaasy(kirjautunut, peli, map)){
            return;
        }
        
        map.put("kartta", vuoro.getRuudut());
        
        boolean ekavuoro = vuoro.getVuoro() == 0;
        boolean vikavuoro = vuoro.getVuoro() == peli.getVuoroja()-1;
        
        map.put("ekavuoro", ekavuoro);
        map.put("vikavuoro", vikavuoro);
    }
    
}
