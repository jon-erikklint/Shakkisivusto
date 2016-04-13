package tkht.shakkisivusto.kontrollerit.pelaajasivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelaajasivuController extends AbstraktiController{

    public PelaajasivuController(PelaajaDao pelaajaDao, PeliDao peliDao, SessionManager sm){
        PelaajasivuGet get = new PelaajasivuGet(pelaajaDao, peliDao, sm);
        
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/pelaaja/:pelaaja", get);
        
        Map<String, TemplateViewRoute> posts = new HashMap<>();
        
        super.initialize(gets, posts);
    }
    
}
