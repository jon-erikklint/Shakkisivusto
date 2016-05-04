package tkht.shakkisivusto.kontrollerit.asetukset;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class AsetuksetController extends AbstraktiController{
    
    public AsetuksetController(PelaajaDao pelaajaDao, PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("asetukset", new AsetuksetGet(sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        sets.put("asetukset", new PaivitaNimi(pelaajaDao, sm));
        sets.put("poistakayttaja", new PoistaKayttaja(pelaajaDao, pelinPelaajaDao, peliDao, vuoroDao, sm));
        
        super.initialize(gets, sets);
    }
    
}
