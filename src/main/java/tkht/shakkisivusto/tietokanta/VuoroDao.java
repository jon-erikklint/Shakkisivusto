package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;
import tkht.shakkisivusto.tietokanta.luojat.VuoroLuoja;

public class VuoroDao extends AbstraktiDao<Vuoro>{

    public VuoroDao(Database db) {
        super(db, "Vuoro", "vuoro, peliid, pelaaja, tekoaika, lauta, erikoistilanteet", new VuoroLuoja());
    }
    
    public List<Vuoro> findByPeli(Peli peli) throws Exception{
        String query = "SELECT * FROM Vuoro WHERE peliid = ? ORDER BY vuoro DESC LIMIT 1";
        
        List<Object> values = new ArrayList<>();
        values.add(peli.getId());
        
        return super.findByQuery(query, values);
    }

    @Override
    public List<Object> decomposeT(Vuoro t) {
        List<Object> lista = new ArrayList<>();
        
        lista.add(t.getVuoro());
        lista.add(t.getPeliid());
        lista.add(t.getPelaajaid());
        lista.add(t.getTekoaika());
        lista.add(t.getNappulatString());
        lista.add(t.getErikoistilanteetString());
        
        return lista;
    }

    @Override
    public int getId(Vuoro t) {
        return t.getVuoro();
    }

    
    
}
