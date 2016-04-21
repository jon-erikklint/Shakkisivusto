package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;

public class PeliDao extends AbstraktiDao<Peli>{

    public PeliDao(Database db) {
        super(db, "Peli", "nimi, status");
    }
    
    public int voitettujaPeleja(int pelaajaid) throws Exception{
        String query = "SELECT COUNT(*) FROM Peli WHERE Peli.voittaja = ?";
        
        List<Object> values = new ArrayList<>();
        values.add(pelaajaid);
        
        return super.findIntByAggregate(query, values);
    }
    
    public int havittyjaPeleja(int pelaajaid) throws Exception{
        String query = "SELECT COUNT(*) FROM Peli, PelinPelaaja "
                + "WHERE Peli.id = PelinPelaaja.peliid AND Peli.status = 'LOPPUNUT' "
                + "AND PelinPelaaja.pelaajaid = ? AND Peli.voittaja != ?";
        
        List<Object> values = new ArrayList<>();
        values.add(pelaajaid);
        values.add(pelaajaid);
        
        return super.findIntByAggregate(query, values);
    }
    
    public List<Peli> findByPelaaja(int pelaajaid) throws Exception{
        String query = "SELECT * FROM PelinPelaaja, Peli WHERE Pelinpelaaja.peliid = Peli.id AND PelinPelaaja.pelaajaid = ?";
        
        List<Object> values = new ArrayList<>();
        
        values.add(pelaajaid);
        
        return super.findByQuery(query, values);
    }
    
    public List<Peli> liityttavatPelit(int pelaajaid) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja WHERE Pelinpelaaja.peliid = Peli.id AND "
                + "Peli.status = 'HAETAAN VASTAPELAAJAA' AND Pelinpelaaja.pelaajaid != ?";
        
        List<Object> values = new ArrayList<>();
        
        values.add(pelaajaid);
        
        return super.findByQuery(query, values);
    }

    @Override
    public Peli createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        String status = rs.getString("status");
        
        return new Peli(id, nimi, status);
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
