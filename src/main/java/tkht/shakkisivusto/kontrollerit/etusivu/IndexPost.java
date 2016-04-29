package tkht.shakkisivusto.kontrollerit.etusivu;

import java.util.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.SessionGenerator;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class IndexPost implements TemplateViewRoute{

    private SessionGenerator sg;
    
    private PelaajaDao pelaajaDao;
    
    public IndexPost(PelaajaDao pelaajaDao, SessionManager sm){
        sg = new SessionGenerator(sm);
        this.pelaajaDao = pelaajaDao;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        String kayttajatunnus = rqst.queryParams("kayttaja");
        String salasana = rqst.queryParams("salasana");
        
        Pelaaja pelaaja = pelaajaDao.findByKayttajatunnus(kayttajatunnus);
        
        if(pelaaja == null){
            rspns.redirect("/");
        }else{
            
            if(!pelaaja.getSalasana().equals(salasana)){
                rspns.redirect("/");
            }else{
                sg.generateSession(pelaaja, rspns);
                
                rspns.redirect("/pelaaja/"+pelaaja.getKayttajanimi());
            }
            
        }
        
        return new ModelAndView(new HashMap(), "index");
    }
    
}
