package tkht.shakkisivusto.kontrollerit;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.Serveri;
import tkht.shakkisivusto.domain.Pelaaja;

public abstract class KirjautunutHelper implements TemplateViewRoute{
    
    protected SessionManager sm;
    private String sivu;
    
    public KirjautunutHelper(SessionManager sm, String sivu){
        this.sm = sm;
        this.sivu = sivu;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        Map map = new HashMap<>();
        
        Pelaaja kirjautunut = haeKirjautunut(rqst, rspns);
        
        if(kirjautunut == null){
            rspns.redirect("/");
            
            return new ModelAndView(map, sivu);
        }
        
        lisaaSivulinkit(map, kirjautunut);
        
        handle(rqst, rspns, map, kirjautunut);
        
        return new ModelAndView(map, sivu);
    }
    
    private Pelaaja haeKirjautunut(Request rq, Response rs){
        String sessionName = rq.cookie("session");
        
        return sm.getPelaaja(sessionName);
    }
    
    void lisaaSivulinkit(Map map, Pelaaja pelaaja){
        map.put("pelaaja", pelaaja);
        map.put("etsipelaajia", Serveri.osoite+"/pelaajahaku");
        map.put("kirjauduulos", Serveri.osoite+"/ulos");
    }
    
    public abstract void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception;

}
