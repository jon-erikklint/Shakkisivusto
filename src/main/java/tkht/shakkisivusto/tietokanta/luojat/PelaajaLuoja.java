package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import tkht.shakkisivusto.domain.Pelaaja;

public class PelaajaLuoja extends AbstraktiLuoja<Pelaaja>{

    @Override
    protected Pelaaja createT(ResultSet rs) throws Exception{
        int indeksi = rs.getInt("idpelaaja");
        String nimi = rs.getString("pelaajanimi");
        String kayttajanimi = rs.getString("kayttajanimi");
        String salasana = rs.getString("salasana");
        boolean admin = rs.getBoolean("admin");
        
        return new Pelaaja(indeksi, nimi, kayttajanimi, salasana, admin);
    }
    
}
