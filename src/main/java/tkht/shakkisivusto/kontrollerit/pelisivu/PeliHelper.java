package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.kontrollerit.KirjautunutHelper;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;

public abstract class PeliHelper extends KirjautunutHelper{

    protected PeliDao peliDao;
    
    public PeliHelper(PeliDao peliDao, SessionManager sm) {
        super(sm, "pelisivu");
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        String pelistring = rqst.params(":peli");
        int peliid;
        try{
            peliid = Integer.parseInt(pelistring);
        }catch(Exception e){
            ilmoitaVirhe("Virheellinen osoite", map);
            return;
        }
        
        Peli peli = peliDao.findOneRambling(peliid);
        
        if(peli == null){
            ilmoitaVirhe("Virheellinen osoite", map);
        }
        
        handle(rqst, rspns, map, kirjautunut, peli);
    }
    
    protected abstract void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception;
    
    protected void ilmoitaVirhe(String virhe, Map map){
        System.out.println("VIRHE: "+virhe);
    }
    
    protected boolean tarkistaPaasy(Pelaaja kirjautunut, Peli peli, Map map){
        if(peli.getPelaaja1() != null){
            if(peli.getPelaaja1().getPelaajaid() == kirjautunut.getIndeksi()){
                return true;
            }
        }
        
        if(peli.getPelaaja2() != null){
            if(peli.getPelaaja2().getPelaajaid() == kirjautunut.getIndeksi()){
                return true;
            }
        }
        
        ilmoitaVirhe("Sinulla ei ole katsomisoikeuksia peliin", map);
        
        return false;
    }
    
}
