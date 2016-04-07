package tkht.shakkisivusto.kontrollerit;

import java.util.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class Rekisterointi implements TemplateViewRoute{

    private PelaajaDao pelaajaDao;

    public Rekisterointi(PelaajaDao pelaajaDao) {
        this.pelaajaDao = pelaajaDao;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        Map map = new HashMap();
        
        String kayttajatunnus = rqst.queryParams("kayttaja");
        String salasana = rqst.queryParams("salasana");
        
        Pelaaja pelaaja = pelaajaDao.findByKayttajatunnus(kayttajatunnus);
        
        if(pelaaja == null){
            rspns.redirect("/");
            return null;
        }
        
        pelaaja.setNimi(kayttajatunnus);
        pelaaja.setSalasana(salasana);
        pelaaja.setAdmin(false);
        
        return null;
    }
    
}
