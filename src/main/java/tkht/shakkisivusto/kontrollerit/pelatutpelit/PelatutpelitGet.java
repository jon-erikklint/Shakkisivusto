package tkht.shakkisivusto.kontrollerit.pelatutpelit;

import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelatutpelitGet extends KirjautunutHelper{

    private PeliDao peliDao;
    
    public PelatutpelitGet(PeliDao peliDao, SessionManager sm) {
        super(sm, "pelatutpelit");
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        List<Peli> pelatutPelit = peliDao.pelatutPelit(kirjautunut.getIndeksi());
        
        map.put("pelit", pelatutPelit);
    }
    
}
