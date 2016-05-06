package tkht.shakkisivusto.kontrollerit.luovutussivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.pelisivu.PeliHelper;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.Poistaja;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class Luovuta extends PeliHelper{

    private Poistaja poistaja;
    
    public Luovuta(PeliDao peliDao, PelaajaDao pelaajaDao, PelinPelaajaDao pelinpelaajaDao, VuoroDao vuoroDao, SessionManager sm) {
        super("pelisivu", peliDao, sm);
        
        poistaja = new Poistaja(pelaajaDao, peliDao, pelinpelaajaDao, vuoroDao);
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(!super.tarkistaPaasy(kirjautunut, peli, map)){
            return;
        }
        
        poistaja.lopetaPeli(kirjautunut, false, peli);
        rspns.redirect("/omatpelit");
    }
    
}
