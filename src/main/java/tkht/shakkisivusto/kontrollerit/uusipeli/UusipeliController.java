package tkht.shakkisivusto.kontrollerit.uusipeli;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class UusipeliController extends AbstraktiController{
    
    public UusipeliController(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/uusipeli", new UusipeliGet(sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        sets.put("/uusipeli", new LuoPeli(peliDao, pelinPelaajaDao, vuoroDao, sm));
        
        super.initialize(gets, sets);
    }
}
