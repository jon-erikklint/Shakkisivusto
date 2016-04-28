package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public class PelisivuGet extends KirjautunutHelper{

    private PeliDao peliDao;
    
    public PelisivuGet(PeliDao peliDao, SessionManager sm) {
        super(sm, "pelisivu");
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        System.out.println("ALOITETTU");
        int peliid = Integer.parseInt(rqst.params(":peli"));
        System.out.println("PELIID SAATU");;
        Peli peli = peliDao.findOneRambling(peliid);
        System.out.println("PELI LÖYDETTY");
        map.put("kartta", peli.getUusinVuoro().getRuudut());
        System.out.println("PELI LISÄTTY");
        int vuorossaolijanid = peli.getUusinVuoro().getPelaajaid();
        System.out.println("VUOROSSAOLIJA LÖYDETTY");
        map.put("pelaajanVuoro", vuorossaolijanid == kirjautunut.getIndeksi());
        System.out.println("EHTO LISÄTTY");
    }
    
}
