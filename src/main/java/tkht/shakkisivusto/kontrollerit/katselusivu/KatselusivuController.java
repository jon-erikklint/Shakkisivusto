package tkht.shakkisivusto.kontrollerit.katselusivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class KatselusivuController extends AbstraktiController{
    
    public KatselusivuController(PeliDao peliDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("/peli/:peli/vuoro/:vuoro", new KatselusivuGet(peliDao, vuoroDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
}
