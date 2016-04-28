package tkht.shakkisivusto.kontrollerit.uudetpelit;

import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;

public class UudetpelitGet extends KirjautunutHelper{

    private PeliDao peliDao;
    private PelaajaDao pelaajaDao;
    private PelinPelaajaDao pelinPelaajaDao;
    
    public UudetpelitGet(PeliDao peliDao, PelaajaDao pelaajaDao, PelinPelaajaDao pelinPelaajaDao, SessionManager sm) {
        super(sm, "uudetpelit");
        
        this.peliDao = peliDao;
        this.pelaajaDao = pelaajaDao;
        this.pelinPelaajaDao = pelinPelaajaDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        List<Peli> liityttavatPelit = peliDao.liityttavatPelit(kirjautunut.getIndeksi());
        
        map.put("pelit", liityttavatPelit);
    }
    
}
