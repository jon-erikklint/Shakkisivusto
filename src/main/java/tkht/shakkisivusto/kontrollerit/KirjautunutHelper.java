package tkht.shakkisivusto.kontrollerit;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.Serveri;

public abstract class KirjautunutHelper implements TemplateViewRoute{
    
    private String sivu;
    
    public KirjautunutHelper(String sivu){
        this.sivu = sivu;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        Map map = new HashMap<>();
        
        lisaaSivulinkit(map);
        
        handle(rqst, rspns, map);
        
        return new ModelAndView(map, sivu);
    }
    
    void lisaaSivulinkit(Map map){
        map.put("etsipelaaja", Serveri.osoite+"/pelaajahaku");
    }
    
    public abstract void handle(Request rqst, Response rspns, Map map) throws Exception;

}
