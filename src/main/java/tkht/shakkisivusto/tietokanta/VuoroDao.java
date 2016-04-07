package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Vuoro;

public class VuoroDao extends AbstraktiDao<Vuoro>{

    public VuoroDao(Database db) {
        super(db, "Vuoro", "vuoro, peliid, pelaaja, lauta, erikoistilanteet, tekoaika");
    }

    @Override
    public Vuoro createT(ResultSet rs) throws Exception {
        int vuoro = rs.getInt("vuoro");
        int peliId = rs.getInt("peliid");
        int pelaaja = rs.getInt("pelaaja");
        Timestamp tekoaika = rs.getTimestamp("tekoaika");
        String lauta = rs.getString("lauta");
        String erikoistilanteet = rs.getString("erikoistilanteet");
        
        return new Vuoro(vuoro, peliId, pelaaja, tekoaika, lauta, erikoistilanteet);
    }

    @Override
    public List<Object> decomposeT(Vuoro t) {
        List<Object> lista = new ArrayList<>();
        
        lista.add(t.getVuoro());
        lista.add(t.getPeliid());
        lista.add(t.getPelaaja());
        lista.add(t.getTekoaika());
        lista.add(t.getNappulatString());
        lista.add(t.getErikoistilanteetString());
        
        return lista;
    }

    
    
}
