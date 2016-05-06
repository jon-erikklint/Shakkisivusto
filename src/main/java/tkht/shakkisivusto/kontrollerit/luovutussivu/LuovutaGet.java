package tkht.shakkisivusto.kontrollerit.luovutussivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.pelisivu.PeliHelper;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class LuovutaGet extends PeliHelper{

    public LuovutaGet(PeliDao peliDao, SessionManager sm) {
        super("luovutussivu", peliDao, sm);
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        super.tarkistaPaasy(kirjautunut, peli, map);
    }
    
}
