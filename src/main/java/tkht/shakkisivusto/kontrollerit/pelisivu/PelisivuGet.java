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
        super(peliDao, sm);
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(!tarkistaPaasy(kirjautunut, peli, map)){
            return;
        }
        System.out.println("OIKEA HENKILÖ");
        
        List<List<String>> kartta = peli.getUusinVuoro().getRuudut();
        
        System.out.println(kartta.size());
        
        for(List<String> rivi : kartta){
            System.out.println("UUSI RIVI");
            for(String ruutu : rivi){
                System.out.println(ruutu);
            }
        }
        
        map.put("kartta", peli.getUusinVuoro().getRuudut());
        
        int vuorossaolijanid = peli.getUusinVuoro().getPelaajaid();
        
        map.put("pelaajanVuoro", vuorossaolijanid == kirjautunut.getIndeksi());
    }
    
}
