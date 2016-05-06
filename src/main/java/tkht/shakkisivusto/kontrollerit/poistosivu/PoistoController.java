package tkht.shakkisivusto.kontrollerit.poistosivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class PoistoController extends AbstraktiController{
    
    public PoistoController(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, PelaajaDao pelaajaDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("/peli/:peli/poista", new PoistoGet(peliDao, sm));
        
        Map<String, TemplateViewRoute> puts = new HashMap<>();
        puts.put("/peli/:peli/poista", new PoistaPeli(peliDao, pelinPelaajaDao, pelaajaDao, vuoroDao, sm));
        
        super.initialize(gets, puts);
    }
}
