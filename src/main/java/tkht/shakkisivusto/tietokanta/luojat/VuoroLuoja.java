package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.Timestamp;
import tkht.shakkisivusto.domain.Vuoro;

public class VuoroLuoja extends AbstraktiLuoja<Vuoro>{

    public VuoroLuoja(String lisa) {
        super(lisa);
    }

    @Override
    protected Vuoro createT() throws Exception {
        int vuoro = super.getInt("vuoro");
        int peliId = super.getInt("peliid");
        int pelaaja = super.getInt("pelaaja");
        Timestamp tekoaika = super.getTimestamp("tekoaika");
        String lauta = super.getString("lauta");
        String erikoistilanteet = super.getString("erikoistilanteet");
        
        return new Vuoro(vuoro, peliId, pelaaja, tekoaika, lauta, erikoistilanteet);
    }
    
}
