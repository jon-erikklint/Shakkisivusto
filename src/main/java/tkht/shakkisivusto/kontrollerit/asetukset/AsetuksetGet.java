package tkht.shakkisivusto.kontrollerit.asetukset;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;

public class AsetuksetGet extends KirjautunutHelper{

    public AsetuksetGet(SessionManager sm) {
        super(sm, "asetukset");
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {}
    
}
