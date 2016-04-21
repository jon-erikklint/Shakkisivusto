package tkht.shakkisivusto.kontrollerit.asetukset;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class AsetuksetController extends AbstraktiController{
    
    public AsetuksetController(PelaajaDao pelaajaDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("asetukset", new AsetuksetGet(sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        sets.put("asetukset", new PaivitaNimi(pelaajaDao, sm));
        sets.put("poistakayttaja", new PoistaKayttaja(pelaajaDao, sm));
        
        super.initialize(gets, sets);
    }
    
}
