package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;
import tkht.shakkisivusto.tietokanta.luojat.VuoroLuoja;
import tkht.shakkisivusto.tietokanta.luojat.VuoroYhdistavaLuoja;

public class VuoroDao extends AbstraktiDao<Vuoro>{

    private VuoroYhdistavaLuoja yhdistavaLuoja;
    
    public VuoroDao(Database db) {
        super(db, "Vuoro", "peli", "vuoro, peli, pelaaja, tekoaika, lauta, erikoistilanteet", new VuoroLuoja());
        
        yhdistavaLuoja = new VuoroYhdistavaLuoja();
    }
    
    public void poistaPelinVuorot(int peliid) throws Exception{
        String query = "DELETE FROM Vuoro WHERE peli = ?";
        List values = createList(peliid);
        
        super.updateByQuery(query, values);
    }
    
    public void paivitaPelaajanVuorotToiselle(int pelaajaidjolta, int pelaajaidjolle) throws Exception{
        String query = "UPDATE Vuoro SET pelaaja = ? WHERE "
                + "pelaaja = ?";
        List values = createList(pelaajaidjolle, pelaajaidjolta);
        
        super.updateByQuery(query, values);
    }
    
    public List<Vuoro> findByPeli(Peli peli) throws Exception{
        String query = "SELECT * FROM Vuoro WHERE peliid = ? ORDER BY vuoro DESC LIMIT 1";
        
        List<Object> values = new ArrayList<>();
        values.add(peli.getId());
        
        return super.findByQuery(query, values);
    }
    
    public Vuoro findVuoroRambling(int peli, int vuoro) throws Exception{
        String query = "SELECT * FROM Vuoro, Peli, Pelaaja WHERE "
                + "Vuoro.peli = ? AND Vuoro.vuoro = ? AND "
                + "Vuoro.peli = Peli.idpeli AND Vuoro.pelaaja = Pelaaja.idpelaaja";
        List values = createList(peli, vuoro);
        
        List<Vuoro> vuorot = super.findByQuery(query, values, yhdistavaLuoja);
        if(vuorot.isEmpty()){
            return null;
        }
        
        return vuorot.get(0);
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
