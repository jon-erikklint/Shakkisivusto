package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.PelinPelaaja;
import tkht.shakkisivusto.tietokanta.luojat.PelinPelaajaLuoja;

public class PelinPelaajaDao extends AbstraktiDao<PelinPelaaja>{

    public PelinPelaajaDao(Database db) {
        super(db, "PelinPelaaja", "id","pelaajaid, peliid, valkoinen, voittaja", new PelinPelaajaLuoja());
    }
    
    public void paivitaPelinPelinpelaajaToiseksi(int peliid, int pelaajajolta, int pelaajajolle) throws Exception{
        String query = "UPDATE Pelinpelaaja SET pelaajaid = ? WHERE "
                + "Pelinpelaaja.peliid = ? AND Pelinpelaaja.pelaajaid = ?";
        List values = createList(pelaajajolle, peliid, pelaajajolta);
        
        super.updateByQuery(query, values);
    }
    
    public void paivitaPelienHaviajaksi(List<Peli> pelit, int pelaaja) throws Exception{
        List<PelinPelaaja> voittajat = new ArrayList<>();
        
        for(Peli peli : pelit){
            if(peli.getPelaaja1().getPelaajaid() == pelaaja){
                voittajat.add(peli.getPelaaja2());
            }else{
                voittajat.add(peli.getPelaaja1());
            }
        }
        
        paivitaVoittajat(voittajat);
    }
    
    public void paivitaPelinHaviajaksi(Peli peli, int pelaaja) throws Exception{
        if(peli.getPelaaja1().getPelaajaid() == pelaaja){
            if(peli.getPelaaja2() != null){
                paivitaVoittaja(peli.getPelaaja2());
            }
        }else{
            paivitaVoittaja(peli.getPelaaja1());
        }
    }
    
    public void paivitaVoittajat(List<PelinPelaaja> pelaajat) throws Exception{
        for(PelinPelaaja pelaaja : pelaajat){
            paivitaVoittaja(pelaaja);
        }
    }
    
    public void paivitaVoittaja(PelinPelaaja pp) throws Exception{
        paivitaVoittaja(pp.getPeliid(), pp.getPelaajaid());
    }
    
    public void paivitaVoittaja(int peli, int pelaaja) throws Exception{
        List<String> columns = new ArrayList<>();
        columns.add("voittaja");
        List<Object> newValues = createList(true);
        List<String> conditions = new ArrayList<>();
        conditions.add("Pelinpelaaja.peliid = ?");
        conditions.add("Pelinpelaaja.pelaajaid = ?");
        List<Object> values = createList(peli, pelaaja);
        
        super.update(columns, newValues, conditions, values);
    }
    
    public void poistaPelinPelinpelaajat(int peliid) throws Exception{
        String query = "DELETE FROM Pelinpelaaja WHERE peliid = ?";
        List values = createList(peliid);
        
        super.updateByQuery(query, values);
    }

    @Override
    public List<Object> decomposeT(PelinPelaaja t) {
        List<Object> lista = new ArrayList<>();
        
        lista.add(t.getPelaajaid());
        lista.add(t.getPeliid());
        lista.add(t.isValkoinen());
        lista.add(t.isVoittaja());
        
        return lista;
    }

    @Override
    public int getId(PelinPelaaja t) {
        return 0;
    }
    
}
