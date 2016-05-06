package tkht.shakkisivusto.kontrollerit.poistosivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.pelisivu.PeliHelper;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PoistoGet extends PeliHelper{

    public PoistoGet(PeliDao peliDao, SessionManager sm) {
        super("poistosivu", false, true, peliDao, sm);
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {}
    
}
