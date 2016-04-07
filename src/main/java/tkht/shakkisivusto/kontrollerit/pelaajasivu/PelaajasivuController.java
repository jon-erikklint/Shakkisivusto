package tkht.shakkisivusto.kontrollerit.pelaajasivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.NakymaController;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class PelaajasivuController implements NakymaController{

    private PelaajasivuGet get;
    
    public PelaajasivuController(PelaajaDao pelaajaDao){
        get = new PelaajasivuGet(pelaajaDao);
    }
    
    @Override
    public Map<String, TemplateViewRoute> gets() {
        Map<String, TemplateViewRoute> map = new HashMap<>();
        
        map.put("/pelaajasivu/:pelaaja", get);
        
        return map;
    }

    @Override
    public Map<String, TemplateViewRoute> posts() {
        return new HashMap<>();
    }
    
}
