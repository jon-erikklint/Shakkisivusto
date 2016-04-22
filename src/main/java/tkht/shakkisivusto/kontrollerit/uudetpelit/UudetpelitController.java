package tkht.shakkisivusto.kontrollerit.uudetpelit;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;

public class UudetpelitController extends AbstraktiController{
    
    public UudetpelitController(PeliDao peliDao, PelaajaDao pelaajaDao, PelinPelaajaDao pelinPelaajaDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("uudetpelit", new UudetpelitGet(peliDao, pelaajaDao, pelinPelaajaDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        initialize(gets, sets);
    }
}
