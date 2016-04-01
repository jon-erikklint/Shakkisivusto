package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;

public class PelaajaDao extends AbstraktiDao<Pelaaja>{

    public PelaajaDao(Database db) {
        super(db, "Pelaaja");
    }

    @Override
    public Pelaaja createT(ResultSet rs) throws Exception{
        int indeksi = rs.getInt("indeksi");
        String nimi = rs.getString("nimi");
        String kayttajanimi = rs.getString("kayttajanimi");
        boolean admin = rs.getBoolean("admin");
        
        return new Pelaaja(indeksi, nimi, kayttajanimi, admin);
    }

    @Override
    public List decomposeT(Pelaaja t) {
        List attribuutit = new ArrayList();
        
        attribuutit.add(t.getIndeksi());
        attribuutit.add(t.getKayttajanimi());
        attribuutit.add(t.getNimi());
        attribuutit.add(t.isAdmin());
        
        return attribuutit;
    }
    
}
