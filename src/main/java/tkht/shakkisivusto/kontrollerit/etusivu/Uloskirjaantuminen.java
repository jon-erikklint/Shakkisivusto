package tkht.shakkisivusto.kontrollerit.etusivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;

public class Uloskirjaantuminen extends KirjautunutHelper{

    public Uloskirjaantuminen(SessionManager sm) {
        super(sm, "/ulos");
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        String sessionCookie = rqst.cookie("session");
        
        sm.removeSession(sessionCookie);
        rspns.cookie("session", sessionCookie, 0);
        
        rspns.redirect("/");
    }
    
}
