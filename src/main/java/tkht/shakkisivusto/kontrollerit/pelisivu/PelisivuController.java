package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelisivuController extends AbstraktiController{
    
    public PelisivuController(PeliDao peliDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/peli/:peli", new PelisivuGet(peliDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
    
}
