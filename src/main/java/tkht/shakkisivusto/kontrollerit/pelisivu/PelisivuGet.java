package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelisivuGet extends KirjautunutHelper{

    private PeliDao peliDao;
    
    public PelisivuGet(PeliDao peliDao, SessionManager sm) {
        super(sm, "pelisivu");
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        int peliid = Integer.parseInt(rqst.params(":peli"));
        
        Peli peli = peliDao.findOneRambling(peliid);
        
        map.put("kartta", peli.getUusinVuoro().getRuudut());
    }
    
}
