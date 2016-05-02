package tkht.shakkisivusto.kontrollerit.liitypeliin;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.PelinPelaaja;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.kontrollerit.pelisivu.PeliHelper;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;

public class LiityPeliin extends PeliHelper{

    private PelinPelaajaDao ppDao;
    
    public LiityPeliin(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, SessionManager sm) {
        super("liitypeliin", peliDao, sm);
        
        ppDao = pelinPelaajaDao;
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(onkoLiityttava(peli)){
            liityPeliin(kirjautunut, peli);
            
            rspns.redirect(peli.getPelisivu());
            
            return;
        }
        
        ilmoitaVirhe("Et voi liittyä peliin", peli);
    }
    
    private boolean onkoLiityttava(Peli peli){
        return peli.getPelaaja2() == null;
    }
    
    private void liityPeliin(Pelaaja kirjautunut, Peli peli) throws Exception{
        PelinPelaaja uusi = new PelinPelaaja(kirjautunut.getIndeksi(), peli.getId(), false);
        peliDao.paivitaPelistatus(peli.getId(), "KÄYNNISSÄ");
        
        ppDao.add(uusi);
    }

    @Override
    protected String virheSivu(Peli peli) {
        return peli.getLiittymislinkki();
    }
    
}
