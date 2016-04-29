package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.PelinPelaaja;
import tkht.shakkisivusto.tietokanta.luojat.PelinPelaajaLuoja;

public class PelinPelaajaDao extends AbstraktiDao<PelinPelaaja>{

    public PelinPelaajaDao(Database db) {
        super(db, "PelinPelaaja", "pelaajaid, peliid, valkoinen, voittaja", new PelinPelaajaLuoja());
    }
    
    public void paivitaVoittaja(PelinPelaaja pp) throws Exception{
        
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
