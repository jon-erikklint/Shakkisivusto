package tkht.shakkisivusto.kontrollerit.omatpelit;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class OmatpelitController extends AbstraktiController{
    
    public OmatpelitController(PeliDao peliDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/omatpelit", new OmatpelitGet(peliDao, vuoroDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
}
