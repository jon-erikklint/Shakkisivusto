package tkht.shakkisivusto.kontrollerit.pelaajasivu;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelaajasivuGet implements TemplateViewRoute{

    private PelaajaDao pelaajaDao;
    private PeliDao peliDao;
    
    public PelaajasivuGet(PelaajaDao pelaajaDao, PeliDao peliDao){
        this.pelaajaDao = pelaajaDao;
        this.peliDao = peliDao;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        Map map = new HashMap<>();
        
        String kayttajanimi = rqst.params(":pelaaja");
        
        Pelaaja pelaaja = pelaajaDao.findByKayttajatunnus(kayttajanimi);
        pelaaja.setVoittoja( peliDao.voitettujaPeleja( pelaaja.getIndeksi() ) );
        pelaaja.setTappioita( peliDao.havittyjaPeleja( pelaaja.getIndeksi() ) );
        
        map.put("pelaaja", pelaaja);
        
        return new ModelAndView(map, "pelaajasivu");
    }
    
}
