package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.tietokanta.luojat.PelaajaLuoja;

public class PelaajaDao extends AbstraktiDao<Pelaaja>{

    public PelaajaDao(Database db) {
        super(db, "Pelaaja", "idpelaaja", "kayttajanimi, pelaajanimi, salasana, admin", new PelaajaLuoja());
    }
    
    @Override
    public List<Pelaaja> findAll() throws Exception{
        String query = "SELECT * FROM Pelaaja WHERE idpelaaja != 0";
        
        return super.findByQuery(query, new ArrayList<>());
    }
    
    public Pelaaja findByKayttajatunnus(String kayttajatunnus) throws Exception{
        String query = "SELECT * FROM Pelaaja WHERE kayttajanimi = ? AND idpelaaja != 0";
        List<Object> values = super.createList(kayttajatunnus);
        
        List<Pelaaja> vastaus = super.findByQuery(query, values);
        
        if(vastaus.isEmpty()){
            return null;
        }
        return vastaus.get(0);
    }
    
    public int paivitaNimi(Pelaaja pelaaja, String uusiNimi) throws Exception{
        List<String> columns = new ArrayList<>();
        columns.add("pelaajanimi");
        List<Object> values = super.createList(uusiNimi);
        
        return update(columns, values, pelaaja.getIndeksi());
    }

    @Override
    public List decomposeT(Pelaaja t) {
        List attribuutit = new ArrayList();
        
        attribuutit.add(t.getKayttajanimi());
        attribuutit.add(t.getNimi());
        attribuutit.add(t.getSalasana());
        attribuutit.add(t.isAdmin());
        
        return attribuutit;
    }

    @Override
    public int getId(Pelaaja t) {
        return t.getIndeksi();
    }
 
}
