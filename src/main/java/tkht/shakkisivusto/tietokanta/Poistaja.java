package tkht.shakkisivusto.tietokanta;

import tkht.shakkisivusto.domain.Pelaaja;

public class Poistaja {
    
    private PelaajaDao pelaajaDao;
    private PeliDao peliDao;
    private PelinPelaajaDao pelinPelaajaDao;
    private VuoroDao vuoroDao;

    public Poistaja(PelaajaDao pelaajaDao, PeliDao peliDao, PelinPelaajaDao pelinPelaajaDao, VuoroDao vuoroDao) {
        this.pelaajaDao = pelaajaDao;
        this.peliDao = peliDao;
        this.pelinPelaajaDao = pelinPelaajaDao;
        this.vuoroDao = vuoroDao;
    }
    
    public void poistaKayttaja(Pelaaja pelaaja) throws Exception{
        lopetaKeskeneraisetPelit(pelaaja);
        poistaViittaukset(pelaaja);
        
        pelaajaDao.delete(pelaaja);
    }
    
    private void lopetaKeskeneraisetPelit(Pelaaja pelaaja) throws Exception{
        peliDao.paivitaPelistatusPelaajanLoppuneisiinPeleihin(pelaaja.getIndeksi(), "LOPPUNUT");
        
    }
    
    private void poistaViittaukset(Pelaaja pelaaja) throws Exception{
        
    }
}
