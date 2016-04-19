package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.PelinPelaaja;

public class PelinPelaajaDao extends AbstraktiDao<PelinPelaaja>{

    public PelinPelaajaDao(Database db) {
        super(db, "PelinPelaaja", "pelaajaid, peliid, valkoinen");
    }

    @Override
    public PelinPelaaja createT(ResultSet rs) throws Exception {
        int pelaajaid = rs.getInt("pelaajaid");
        int peliid = rs.getInt("peliid");
        boolean valkoinen = rs.getBoolean("valkoinen");
        
        return new PelinPelaaja(pelaajaid, peliid, valkoinen);
    }

    @Override
    public List<Object> decomposeT(PelinPelaaja t) {
        List<Object> lista = new ArrayList<>();
        
        lista.add(t.getPelaaja());
        lista.add(t.getPeli());
        lista.add(t.isValkoinen());
        
        return lista;
    }
    
}
