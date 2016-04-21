package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;

public class PelaajaDao extends AbstraktiDao<Pelaaja>{

    public PelaajaDao(Database db) {
        super(db, "Pelaaja", "kayttajanimi, pelaajanimi, salasana, admin");
    }
    
    public Pelaaja findByKayttajatunnus(String kayttajatunnus) throws Exception{
        String query = "SELECT * FROM Pelaaja WHERE kayttajanimi = ?";
        List<Object> values = new ArrayList<>();
        values.add(kayttajatunnus);
        
        List<Pelaaja> vastaus = super.findByQuery(query, values);
        
        if(vastaus.isEmpty()){
            return null;
        }
        return vastaus.get(0);
    }
    
    public int paivitaNimi(Pelaaja pelaaja, String uusiNimi) throws Exception{
        List<String> columns = new ArrayList<>();
        columns.add("pelaajanimi");
        List<Object> values = new ArrayList<>();
        values.add(uusiNimi);
        
        return update(columns, values, pelaaja.getIndeksi());
    }
    
    public void haePelaajat(List<Peli> pelit) throws Exception{
        for(Peli peli : pelit){
            haePelaajat(peli);
        }
    }
    
    public void haePelaajat(Peli peli) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja, Pelaaja WHERE Peli.id = Pelinpelaaja.peliid AND Pelinpelaaja.pelaajaid = Pelaaja.id"
                + "AND Peli.id = ?";
        List<Object> values = new ArrayList<>();
        values.add(peli.getId());
        
        List<Pelaaja> pelaajat = findByQueryExtra(query, values, 0);
        
        for(Pelaaja pelaaja : pelaajat){
            if(pelaaja.isHelpValkoinen()){
                peli.setPelaaja1(pelaaja);
            }else{
                peli.setPelaaja2(pelaaja);
            }
        }
    }

    @Override
    public Pelaaja createT(ResultSet rs) throws Exception{
        int indeksi = rs.getInt("id");
        String nimi = rs.getString("pelaajanimi");
        String kayttajanimi = rs.getString("kayttajanimi");
        String salasana = rs.getString("salasana");
        boolean admin = rs.getBoolean("admin");
        
        return new Pelaaja(indeksi, nimi, kayttajanimi, salasana, admin);
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
