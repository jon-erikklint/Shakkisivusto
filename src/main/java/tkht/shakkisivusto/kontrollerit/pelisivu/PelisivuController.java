package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class PelisivuController extends AbstraktiController{
    
    public PelisivuController(PeliDao peliDao, PelaajaDao pelaajaDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/peli/:peli", new PelisivuGet(peliDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        sets.put("/peli/:peli", new SuoritaVuoro(peliDao, pelinPelaajaDao, vuoroDao, sm));
        sets.put("/peli/:peli/luovuta", new Luovuta(peliDao, pelaajaDao, pelinPelaajaDao, vuoroDao, sm));
        
        super.initialize(gets, sets);
    }
    
}
