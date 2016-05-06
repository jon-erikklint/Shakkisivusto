package tkht.shakkisivusto.kontrollerit.luovutussivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;
import tkht.shakkisivusto.tietokanta.VuoroDao;

public class LuovutaController extends AbstraktiController{
    
    public LuovutaController(PeliDao peliDao, PelaajaDao pelaajaDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("/peli/:peli/luovuta", new LuovutaGet(peliDao, sm));
        
        Map<String, TemplateViewRoute> puts = new HashMap<>();
        puts.put("", new Luovuta(peliDao, pelaajaDao, pelinPelaajaDao, vuoroDao, sm));
        
        super.initialize(gets, puts);
    }
}
