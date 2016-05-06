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
    
    private Response response;
    private Pelaaja kirjautunut;
    private Map map;
    
    public PeliHelper(String sivu, PeliDao peliDao, SessionManager sm) {
        super(sm, sivu);
        
        this.peliDao = peliDao;
    }

    @Override
    public void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut) throws Exception {
        this.kirjautunut = kirjautunut;
        this.response = rspns;
        this.map = map;
        
        String pelistring = rqst.params(":peli");
        int peliid;
        try{
            peliid = Integer.parseInt(pelistring);
        }catch(Exception e){
            ilmoitaVirhe("Virheellinen osoite", null);
            return;
        }
        
        Peli peli = peliDao.findOneRambling(peliid);
        
        if(peli == null){
            ilmoitaVirhe("Virheellinen osoite", null);
        }
        
        if(peli.getStatus().equals("LOPPUNUT")){
            ilmoitaVirhe("Peli on loppunut", null);
        }
        
        map.put("peli", peli);
        
        handle(rqst, rspns, map, kirjautunut, peli);
    }
    
    protected abstract void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception;
    
    protected void ilmoitaVirhe(String virhe, Peli peli){
        String linkki = virheSivu(peli);
        response.redirect(linkki);
        
        System.out.println("VIRHE: "+virhe);
    }
    
    protected String virheSivu(Peli peli){
        return "/omatpelit";
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
        
        ilmoitaVirhe("Sinulla ei ole katsomisoikeuksia peliin", peli);
        
        return false;
    }
    
}
