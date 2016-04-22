package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;

public class PelaajaDao extends AbstraktiDao<Pelaaja>{

    private PelinPelaajaDao ppDao;
    private PeliDao peliDao;
    private VuoroDao vuoroDao;
    
    public PelaajaDao(Database db) {
        super(db, "Pelaaja", "kayttajanimi, pelaajanimi, salasana, admin");
    }
    
    public void addDaos(PelinPelaajaDao ppDao, PeliDao peliDao, VuoroDao vuoroDao){
        this.ppDao = ppDao;
        this.peliDao = peliDao;
        this.vuoroDao = vuoroDao;
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
        peli.setPelaaja1(findOne(peli.getPelaaja1id()));
        peli.setPelaaja2(findOne(peli.getPelaaja2id()));
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
    
    public void deleteCascade(Pelaaja pelaaja) throws Exception{
        List<String> columns = new ArrayList<>();
        columns.add("Vuoro.pelaaja");
        List<Object> newValues = new ArrayList<>();
        columns.add(null);
        List<String> conditions = new ArrayList<>();
        conditions.add("Vuoro.pelaaja = ?");
        List<Object> values = new ArrayList<>();
        values.add(pelaaja.getIndeksi());
        
        vuoroDao.update(columns, newValues, conditions, values); //laitetaan vuorojen pelaajat nulliksi
        
        columns.clear();
        columns.add("Peli.voittaja");
        conditions.clear();
        conditions.add("Peli.voittaja = ?");
        
        peliDao.update(columns, newValues, conditions, values); //laitetaan voittaja nulliksi
        
        conditions.clear();
        conditions.add("Pelinpelaaja.pelaajaid = ?");
        
        ppDao.delete(conditions, values); //poistetaan osallistumiset peleihin
        
        delete(pelaaja); //poistetaan itse pelaaja
    }
 
}
