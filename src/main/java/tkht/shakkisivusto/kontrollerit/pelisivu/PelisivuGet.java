package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelisivuGet extends PeliHelper{

    private PeliDao peliDao;
    
    public PelisivuGet(PeliDao peliDao, SessionManager sm) {
        super("pelisivu", peliDao, sm);
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(!tarkistaPaasy(kirjautunut, peli, map)){
            return;
        }
        
        map.put("kartta", peli.getUusinVuoro().getRuudut());
        
        String vuorotilanne;
        if(peli.getPelaaja2() == null){
            
            vuorotilanne = "toinenpelaajaeiliittynyt";
            
        }else{
            
            int vuorossaolijanid = peli.getUusinVuoro().getPelaajaid();
        
            if(vuorossaolijanid == kirjautunut.getIndeksi()){
                vuorotilanne = "onvuoro";
            }else{
                vuorotilanne = "toisenvuoro";
            }
            
        }
        
        System.out.println(vuorotilanne);
        
        map.put("vuorotilanne", vuorotilanne);
    }
    
}
