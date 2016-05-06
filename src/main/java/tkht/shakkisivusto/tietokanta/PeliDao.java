package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.tietokanta.luojat.PeliLuoja;
import tkht.shakkisivusto.tietokanta.luojat.PeliYhdistavaLuoja;

public class PeliDao extends AbstraktiDao<Peli>{

    private PeliYhdistavaLuoja yhdistavaLuoja;
    
    public PeliDao(Database db) {
        super(db, "Peli", "idpeli", "nimi, status", new PeliLuoja());
        
        yhdistavaLuoja = new PeliYhdistavaLuoja();
    }
    
    public void paivitaPelistatus(int id, String uusiStatus) throws Exception{
        List<String> columns = new ArrayList<>();
        columns.add("status");
        
        List<Object> values = createList(uusiStatus);
        
        super.update(columns, values, id);
    }
    
    public int paivitaPelistatusPelaajanKeskeneraisiinPeleihin(int pelaajaid, String uusiStatus) throws Exception{
        List<Peli> pelaajanKeskeneraisetPelit = findKeskeneraiset(pelaajaid);
        
        String query = "UPDATE Peli SET Peli.status = ? WHERE "
                + "idpeli IN (";
        query = super.addQuestionMarks(query, pelaajanKeskeneraisetPelit.size());
        query += ")";
        
        List values = createList(uusiStatus);
        for(Peli peli : pelaajanKeskeneraisetPelit){
            values.add(peli.getId());
        }
        
        return updateByQuery(query, values);
    }
    
    public void lisaaPelienpelaajienVoittoratiot(List<Peli> pelit) throws Exception{
        for(Peli peli : pelit){
            if(peli.getPelaaja1() != null ){
                lisaaPelaajanVoittoratio(peli.getPelaaja1().getPelaaja());
            }
            if(peli.getPelaaja2() != null ){
                lisaaPelaajanVoittoratio(peli.getPelaaja2().getPelaaja());
            }
        }
    }
    
    public void lisaaPelaajanVoittoratio(Pelaaja pelaaja) throws Exception{
        int voitettuja = voitettujaPeleja(pelaaja.getIndeksi());
        int havittyja = havittyjaPeleja(pelaaja.getIndeksi());
        
        pelaaja.setVoittoja(voitettuja);
        pelaaja.setTappioita(havittyja);
    }
    
    public int voitettujaPeleja(int pelaajaid) throws Exception{
        String query = "SELECT COUNT(*) FROM Pelinpelaaja WHERE Pelinpelaaja.pelaajaid = ? AND Pelinpelaaja.voittaja = true";
        
        List<Object> values = super.createList(pelaajaid);
        
        return super.findIntByAggregate(query, values);
    }
    
    public int havittyjaPeleja(int pelaajaid) throws Exception{
        String query = "SELECT COUNT(*) FROM Peli, PelinPelaaja "
                + "WHERE Peli.idpeli = PelinPelaaja.peliid AND Peli.status = 'LOPPUNUT' "
                + "AND PelinPelaaja.pelaajaid = ? AND Pelinpelaaja.voittaja = false";
        
        List<Object> values = super.createList(pelaajaid);
        
        return super.findIntByAggregate(query, values);
    }
    
    public List<Peli> findKeskeneraiset(int pelaajaid) throws Exception{
        String query = "SELECT * FROM PelinPelaaja, Peli, Vuoro WHERE "
                + "Pelinpelaaja.peliid = Peli.idpeli AND Peli.idpeli = Vuoro.peli "
                + "AND Peli.status != 'LOPPUNUT' AND PelinPelaaja.pelaajaid = ?";
        
        List<Object> values = super.createList(pelaajaid);
        
        return super.findByQuery(query, values, yhdistavaLuoja);
    }
    
    public Peli findOneRambling(int id) throws Exception{
        String query = "SELECT * FROM Peli, PelinPelaaja, Pelaaja, Vuoro WHERE "
                + "Peli.idpeli = PelinPelaaja.peliid AND PelinPelaaja.pelaajaid = Pelaaja.idpelaaja "
                + "AND Peli.idpeli = Vuoro.peli AND Peli.idpeli = ?";
        
        List<Object> values = super.createList(id);
        
        List<Peli> pelit = super.findByQuery(query, values, yhdistavaLuoja);
        if(pelit.isEmpty()){
            return null;
        }
        
        return pelit.get(0);
    }
    
    public List<Peli> liityttavatPelit(int pelaajaid) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja, Pelaaja WHERE Pelinpelaaja.peliid = Peli.idpeli AND "
                + "Peli.status = 'HAETAAN VASTAPELAAJAA' AND Pelinpelaaja.pelaajaid != ? AND "
                + "Pelaaja.idpelaaja = Pelinpelaaja.pelaajaid";
        
        List<Object> values = super.createList(pelaajaid);
        
        return super.findByQuery(query, values, yhdistavaLuoja);
    }
    
    public List<Peli> pelatutPelit(int pelaajaid) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja, Pelaaja, Vuoro WHERE Pelinpelaaja.peliid = Peli.idpeli AND "
                + "Pelaaja.idpelaaja = Pelinpelaaja.peliid AND Peli.idpeli = Vuoro.peli AND "
                + "Pelaaja.idpelaaja = ? AND Peli.status = 'LOPPUNUT'";
        
        List<Object> values = super.createList(pelaajaid);
        
        return super.findByQuery(query, values);
    }
    
    public List<Peli> pelaajanPelit(int pelaajaid) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja, Pelaaja WHERE "
                + "Peli.idpeli = Pelinpelaaja.peliid AND Pelaaja.idpelaaja = Pelinpelaaja.pelaajaid AND "
                + "Pelaaja.idpelaaja = ?";
        List values = createList(pelaajaid);
        
        List<Peli> pelit = super.findByQuery(query, values);
        
        List<Peli> palautettavat = new ArrayList<>();
        
        for(Peli peli : pelit){
            palautettavat.add(findOneRambling(peli.getId()));
        }
        
        return palautettavat;
    }

    @Override
    public List<Object> decomposeT(Peli t) {
        List<Object> hajoitettu = new ArrayList<>();
        
        hajoitettu.add(t.getNimi());
        hajoitettu.add(t.getStatus());
        
        return hajoitettu;
    }

    @Override
    public int getId(Peli t) {
        return t.getId();
    }

}
