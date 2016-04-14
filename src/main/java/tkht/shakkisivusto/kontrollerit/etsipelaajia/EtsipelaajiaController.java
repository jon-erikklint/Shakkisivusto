package tkht.shakkisivusto.kontrollerit.etsipelaajia;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class EtsipelaajiaController extends AbstraktiController{

    public EtsipelaajiaController(PelaajaDao pelaajaDao, SessionManager sm) {
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/pelaajahaku", new Etsipelaajia(pelaajaDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
    
}
