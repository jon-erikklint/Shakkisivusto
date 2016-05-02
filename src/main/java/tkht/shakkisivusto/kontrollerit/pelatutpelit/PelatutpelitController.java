package tkht.shakkisivusto.kontrollerit.pelatutpelit;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelatutpelitController extends AbstraktiController{
    
    public PelatutpelitController(PeliDao peliDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("/pelatutpelit", new PelatutpelitGet(peliDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
    
}
