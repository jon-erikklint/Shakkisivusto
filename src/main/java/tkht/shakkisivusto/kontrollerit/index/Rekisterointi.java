package tkht.shakkisivusto.kontrollerit.index;

import java.util.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.SessionGenerator;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class Rekisterointi implements TemplateViewRoute{

    private PelaajaDao pelaajaDao;
    
    private SessionGenerator sg;

    public Rekisterointi(PelaajaDao pelaajaDao, SessionManager sm) {
        this.pelaajaDao = pelaajaDao;
        sg = new SessionGenerator(sm);
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        Map map = new HashMap();
        
        String kayttajatunnus = rqst.queryParams("kayttaja");
        String salasana = rqst.queryParams("salasana");
        
        Pelaaja pelaaja = pelaajaDao.findByKayttajatunnus(kayttajatunnus);
        
        if(pelaaja != null){
            rspns.redirect("/");
        }
        
        else{
            pelaaja = new Pelaaja();
            
            pelaaja.setKayttajanimi(kayttajatunnus);
            pelaaja.setNimi(kayttajatunnus);
        
            pelaaja.setSalasana(salasana);
            pelaaja.setAdmin(false);

            pelaajaDao.add(pelaaja);
            
            sg.generateSession(pelaaja, rspns);
            rspns.redirect("/pelaaja/"+pelaaja.getKayttajanimi());
        }
        
        return new ModelAndView(new HashMap(), "index");
    }
    
}
