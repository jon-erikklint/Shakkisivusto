package tkht.shakkisivusto.kontrollerit.pelaajasivu;

import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.tietokanta.PelaajaDao;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelaajasivuGet extends KirjautunutHelper{

    private PelaajaDao pelaajaDao;
    private PeliDao peliDao;
    
    public PelaajasivuGet(PelaajaDao pelaajaDao, PeliDao peliDao){
        super("pelaajasivu");
        this.pelaajaDao = pelaajaDao;
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map) throws Exception{
        String kayttajanimi = rqst.params(":pelaaja");
        
        Pelaaja pelaaja = pelaajaDao.findByKayttajatunnus(kayttajanimi);
        pelaaja.setVoittoja( peliDao.voitettujaPeleja( pelaaja.getIndeksi() ) );
        pelaaja.setTappioita( peliDao.havittyjaPeleja( pelaaja.getIndeksi() ) );
        
        map.put("pelaaja", pelaaja);
    }
    
}
