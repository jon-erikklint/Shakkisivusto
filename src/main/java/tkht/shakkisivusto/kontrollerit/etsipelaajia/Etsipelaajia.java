package tkht.shakkisivusto.kontrollerit.etsipelaajia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class Etsipelaajia extends KirjautunutHelper{

    private PelaajaDao pelaajaDao;
    
    public Etsipelaajia(PelaajaDao pelaajaDao, SessionManager sm) {
        super(sm, "etsipelaajia");
        
        this.pelaajaDao = pelaajaDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        
        String haettuPelaaja = rqst.queryParams("pelaaja");
        
        List<Pelaaja> haetut;
        
        if(haettuPelaaja == null){
            haetut = pelaajaDao.findAll();
        }else{
            haetut = new ArrayList<>();
            haetut.add(pelaajaDao.findByKayttajatunnus(haettuPelaaja));
        }
        
        map.put("pelaajat", haetut);
    }
    
}
