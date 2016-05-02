package tkht.shakkisivusto.kontrollerit.liitypeliin;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;

public class LiitypeliinController extends AbstraktiController{
    
    public LiitypeliinController(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, SessionManager sm){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        gets.put("/peli/:peli/liity", new LiitypeliinGet(peliDao, sm));
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        sets.put("/peli/:peli/liity", new LiityPeliin(peliDao, pelinPelaajaDao, sm));
        
        super.initialize(gets, sets);
    }
    
}
