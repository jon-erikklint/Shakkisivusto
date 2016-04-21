package tkht.shakkisivusto.kontrollerit.uudetpelit;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class UudetpelitController extends AbstraktiController{
    
    public UudetpelitController(PeliDao peliDao, PelaajaDao pelaajaDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("", new UudetpelitGet(peliDao, pelaajaDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        initialize(gets, sets);
    }
}
