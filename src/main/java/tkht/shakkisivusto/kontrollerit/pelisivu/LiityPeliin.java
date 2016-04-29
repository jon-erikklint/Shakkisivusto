package tkht.shakkisivusto.kontrollerit.pelisivu;

import java.util.Map;
import spark.Request;
import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.PelinPelaaja;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PeliDao;
import tkht.shakkisivusto.tietokanta.PelinPelaajaDao;

public class LiityPeliin extends PeliHelper{

    private PelinPelaajaDao ppDao;
    
    public LiityPeliin(PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, SessionManager sm) {
        super(peliDao, sm);
    }

    @Override
    protected void handle(Request rqst, Response rspns, Map map, Pelaaja kirjautunut, Peli peli) throws Exception {
        if(onkoLiityttava(peli)){
            liityPeliin(kirjautunut, peli);
            return;
        }
        
        ilmoitaVirhe("Et voi liittyä peliin", map);
    }
    
    private boolean onkoLiityttava(Peli peli){
        return peli.getPelaaja2() == null;
    }
    
    private void liityPeliin(Pelaaja kirjautunut, Peli peli) throws Exception{
        PelinPelaaja uusi = new PelinPelaaja(kirjautunut.getIndeksi(), peli.getId(), false);
        peliDao.paivitaPelistatus(peli.getId(), "KÄYNNISSÄ");
        
        ppDao.add(uusi);
    }
    
}
