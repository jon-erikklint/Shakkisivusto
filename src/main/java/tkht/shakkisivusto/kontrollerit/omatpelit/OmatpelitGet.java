package tkht.shakkisivusto.kontrollerit.omatpelit;

import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class OmatpelitGet extends KirjautunutHelper{

    private PeliDao peliDao;
    private VuoroDao vuoroDao;
    
    public OmatpelitGet(PeliDao peliDao, VuoroDao vuoroDao, SessionManager sm) {
        super(sm, "omatpelit");
        
        this.peliDao = peliDao;
        this.vuoroDao = vuoroDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        System.out.println("UUDETPELIT ALOITETTU");
        
        List<Peli> pelit = peliDao.findByPelaajaRambling(kirjautunut.getIndeksi());
        
        System.out.println("UUDETPELIT LOPETETTU");
        
        map.put("pelit", pelit);
    }
    
}
