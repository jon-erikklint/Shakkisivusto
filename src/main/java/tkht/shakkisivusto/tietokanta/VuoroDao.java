package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;

public class VuoroDao extends AbstraktiDao<Vuoro>{

    public VuoroDao(Database db) {
        super(db, "Vuoro", "vuoro, peliid, pelaaja, lauta, erikoistilanteet, tekoaika");
    }
    
    public void lisaaVuorotiedotPeleihin(List<Peli> pelit) throws Exception{
        
        for(Peli peli : pelit){
            lisaaVuorotiedotPeliin(peli);
        }
        
    }
    
    public void lisaaVuorotiedotPeliin(Peli peli) throws Exception{
        peli.setVuorot(findByPeli(peli));
    }
    
    public List<Vuoro> findByPeli(Peli peli) throws Exception{
        String query = "SELECT * FROM Vuoro WHERE peliid = ? ORDER BY vuoro DESC LIMIT 1";
        
        List<Object> values = new ArrayList<>();
        values.add(peli.getId());
        
        return super.findByQuery(query, values);
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
