package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;

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
    
    public void lopetaPeli(Pelaaja pelaaja, boolean voittaja, Peli peli) throws Exception{
        peliDao.paivitaPelistatus(peli.getId(), "LOPPUNUT");
        if(!voittaja){
            pelinPelaajaDao.paivitaPelinHaviajaksi(peli, pelaaja.getIndeksi());
        }else{
            pelinPelaajaDao.paivitaVoittaja(peli.getId(), pelaaja.getIndeksi());
        }
    }
    
    public void poistaPelaajaPelista(Pelaaja pelaaja, Peli peli) throws Exception{
        if(peli.getPelaaja1().getPelaajaid() == pelaaja.getIndeksi()){
            
            if(peli.getPelaaja2().getPelaajaid() == 0){
                poistaPeli(peli);
            }else{
                pelinPelaajaDao.paivitaPelinPelinpelaajaToiseksi(peli.getId(), pelaaja.getIndeksi(), 0);
            }
            
        }else{
            
            if(peli.getPelaaja1().getPelaajaid() == 0){
                poistaPeli(peli);
            }else{
                pelinPelaajaDao.paivitaPelinPelinpelaajaToiseksi(peli.getId(), pelaaja.getIndeksi(), 0);
            }
            
        }
    }
    
    private void poistaPeli(Peli peli) throws Exception{
        pelinPelaajaDao.poistaPelinPelinpelaajat(peli.getId());
        vuoroDao.poistaPelinVuorot(peli.getId());
        
        peliDao.delete(peli);
    }
    
    public void poistaKayttaja(Pelaaja pelaaja) throws Exception{
        lopetaKeskeneraisetPelit(pelaaja);
        poistaViittaukset(pelaaja);
        
        pelaajaDao.delete(pelaaja);
    }
    
    private void lopetaKeskeneraisetPelit(Pelaaja pelaaja) throws Exception{
        List<Peli> pelaajanpelit = peliDao.findKeskeneraiset(pelaaja.getIndeksi()); //haetaan pelaajan keskeneräiset pelit
        peliDao.paivitaPelistatusPelaajanKeskeneraisiinPeleihin(pelaaja.getIndeksi(), "LOPPUNUT"); //laitetaan pelit loppuneiksi
        pelinPelaajaDao.paivitaPelienHaviajaksi(pelaajanpelit, pelaaja.getIndeksi()); //laitetaan toinen pelaaja voittajaksi
    }
    
    private void poistaViittaukset(Pelaaja pelaaja) throws Exception{
        List<Peli> pelaajanpelit = peliDao.pelaajanPelit(pelaaja.getIndeksi());
        
        for(Peli peli : pelaajanpelit){
            poistaPelaajaPelista(pelaaja, peli);
        }
        
        vuoroDao.paivitaPelaajanVuorotToiselle(pelaaja.getIndeksi(), 0);
    }
}
