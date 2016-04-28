package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.tietokanta.luojat.PelaajaLuoja;

public class PelaajaDao extends AbstraktiDao<Pelaaja>{

    private PelinPelaajaDao ppDao;
    private PeliDao peliDao;
    private VuoroDao vuoroDao;
    
    public PelaajaDao(Database db) {
        super(db, "Pelaaja", "kayttajanimi, pelaajanimi, salasana, admin", new PelaajaLuoja());
    }
    
    public void addDaos(PelinPelaajaDao ppDao, PeliDao peliDao, VuoroDao vuoroDao){
        this.ppDao = ppDao;
        this.peliDao = peliDao;
        this.vuoroDao = vuoroDao;
    }
    
    public Pelaaja findByKayttajatunnus(String kayttajatunnus) throws Exception{
        String query = "SELECT * FROM Pelaaja WHERE kayttajanimi = ?";
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
    
    public void deleteCascade(Pelaaja pelaaja) throws Exception{
        List<String> columns = new ArrayList<>();
        columns.add("Vuoro.pelaaja");
        List<Object> newValues = super.createList((Object) null);
        List<String> conditions = new ArrayList<>();
        conditions.add("Vuoro.pelaaja = ?");
        List<Object> values = super.createList(pelaaja.getIndeksi());
        
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
