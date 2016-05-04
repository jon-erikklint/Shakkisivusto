package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.sql.Timestamp;
import tkht.shakkisivusto.domain.Vuoro;

public class VuoroLuoja extends AbstraktiLuoja<Vuoro>{

    @Override
    protected Vuoro createT(ResultSet rs) throws Exception {
        int vuoro = rs.getInt("vuoro");
        int peliId = rs.getInt("peli");
        int pelaaja = rs.getInt("pelaaja");
        Timestamp tekoaika = rs.getTimestamp("tekoaika");
        String lauta = rs.getString("lauta");
        String erikoistilanteet = rs.getString("erikoistilanteet");
        
        return new Vuoro(vuoro, peliId, pelaaja, tekoaika, lauta, erikoistilanteet);
    }
    
}
