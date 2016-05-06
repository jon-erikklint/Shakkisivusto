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
        super("pelisivu", true, false, peliDao, sm);
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        map.put("kartta", peli.getUusinVuoro().getRuudut());
        
        lisaaVuorotilanne(rqst, map, peli, kirjautunut);
        lisaaValkoinenVaiMusta(peli, kirjautunut, map);
    }
    
    public void lisaaVuorotilanne(Request rqst, Map map, Peli peli, Pelaaja pelaaja){
        String vuorotilanne;
        if(peli.getPelaaja2() == null){
            
            vuorotilanne = "toinenpelaajaeiliittynyt";
            
        }else{
            
            int vuorossaolijanid = peli.getUusinVuoro().getPelaajaid();
        
            if(vuorossaolijanid == pelaaja.getIndeksi()){
                vuorotilanne = "onvuoro";
            }else{
                vuorotilanne = "toisenvuoro";
            }
            
        }
        
        map.put("vuorotilanne", vuorotilanne);
    }
    
    public void lisaaValkoinenVaiMusta(Peli peli, Pelaaja pelaaja, Map map){
        boolean valkoinen = false;
        if(peli.getPelaaja1().getPelaajaid() == pelaaja.getIndeksi()){
            valkoinen = true;
        }
        
        map.put("oletValkoinen", valkoinen);
    }
    
}
