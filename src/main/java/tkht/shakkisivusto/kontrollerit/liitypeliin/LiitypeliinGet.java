package tkht.shakkisivusto.kontrollerit.liitypeliin;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.pelisivu.PeliHelper;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class LiitypeliinGet extends PeliHelper{

    public LiitypeliinGet(PeliDao peliDao, SessionManager sm) {
        super("liitypeliin", false, false, peliDao, sm);
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {}

}
