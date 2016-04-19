package tkht.shakkisivusto.kontrollerit.uusipeli;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;

public class UusipeliGet extends KirjautunutHelper{

    public UusipeliGet(SessionManager sm) {
        super(sm, "luopeli");
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {}
    
}
