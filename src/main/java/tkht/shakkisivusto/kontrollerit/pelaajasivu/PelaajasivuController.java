package tkht.shakkisivusto.kontrollerit.pelaajasivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.NakymaController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelaajasivuController implements NakymaController{

    private PelaajasivuGet get;
    
    public PelaajasivuController(PelaajaDao pelaajaDao, PeliDao peliDao, SessionManager sm){
        get = new PelaajasivuGet(pelaajaDao, peliDao, sm);
    }
    
    @Override
    public Map<String, TemplateViewRoute> gets() {
        Map<String, TemplateViewRoute> map = new HashMap<>();
        
        map.put("/pelaaja/:pelaaja", get);
        
        return map;
    }

    @Override
    public Map<String, TemplateViewRoute> posts() {
        return new HashMap<>();
    }
    
}
