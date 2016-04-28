package tkht.shakkisivusto.tietokanta;

import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.tietokanta.luojat.PeliLuoja;
import tkht.shakkisivusto.tietokanta.luojat.PeliYhdistavaLuoja;

public class PeliDao extends AbstraktiDao<Peli>{

    private PeliYhdistavaLuoja yhdistavaLuoja;
    
    public PeliDao(Database db) {
        super(db, "Peli", "nimi, status", new PeliLuoja());
        
        yhdistavaLuoja = new PeliYhdistavaLuoja();
    }
    
    public int voitettujaPeleja(int pelaajaid) throws Exception{
        String query = "SELECT COUNT(*) FROM Pelinpelaaja WHERE Pelinpelaaja.pelaajaid = ? AND Pelinpelaaja.voittaja = true";
        
        List<Object> values = new ArrayList<>();
        values.add(pelaajaid);
        
        return super.findIntByAggregate(query, values);
    }
    
    public int havittyjaPeleja(int pelaajaid) throws Exception{
        String query = "SELECT COUNT(*) FROM Peli, PelinPelaaja "
                + "WHERE Peli.id = PelinPelaaja.peliid AND Peli.status = 'LOPPUNUT' "
                + "AND PelinPelaaja.pelaajaid = ? AND Pelinpelaaja.voittaja = false";
        
        List<Object> values = new ArrayList<>();
        values.add(pelaajaid);
        
        return super.findIntByAggregate(query, values);
    }
    
    public List<Peli> findByPelaajaRambling(int pelaajaid) throws Exception{
        String query = "SELECT * FROM PelinPelaaja, Peli WHERE Pelinpelaaja.peliid = Peli.id AND PelinPelaaja.pelaajaid = ?";
        
        List<Object> values = new ArrayList<>();
        
        values.add(pelaajaid);
        
        return super.findByQuery(query, values, yhdistavaLuoja);
    }
    
    public List<Peli> liityttavatPelit(int pelaajaid) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja WHERE Pelinpelaaja.peliid = Peli.id AND "
                + "Peli.status = 'HAETAAN VASTAPELAAJAA' AND Pelinpelaaja.pelaajaid != ?";
        
        List<Object> values = new ArrayList<>();
        
        values.add(pelaajaid);
        
        return super.findByQuery(query, values, yhdistavaLuoja);
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
