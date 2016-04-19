package tkht.shakkisivusto.kontrollerit;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PoistaPeli extends KirjautunutHelper{

    private PeliDao peliDao;
    
    public PoistaPeli(PeliDao peliDao, SessionManager sm) {
        super(sm, "omatpelit");
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        
        
        
    }
    
}
