package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.PelinPelaaja;

public class PelinPelaajaDao extends AbstraktiDao<PelinPelaaja>{

    public PelinPelaajaDao(Database db) {
        super(db, "PelinPelaaja", "pelaajaid, peliid, valkoinen");
    }
    
    public void haePelienPelaajatList(List<Peli> pelit) throws Exception{
        for(Peli peli : pelit){
            haePelinPelaajat(peli);
        }
    }
    
    public void haePelinPelaajat(Peli peli) throws Exception{
        String query = "SELECT * FROM Peli, Pelinpelaaja WHERE Peli.id = Pelinpelaaja.peliid AND Peli.id = ?";
        List<Object> values = new ArrayList<>();
        values.add(peli.getId());
        
        List<PelinPelaaja> pelinpelaajat = findByQuery(query, values);
        for(PelinPelaaja pelaaja : pelinpelaajat){
            if(pelaaja.isValkoinen()){
                peli.setPelaaja1id(pelaaja.getPelaaja());
            }else{
                peli.setPelaaja2id(pelaaja.getPelaaja());
            }
        }
    }

    @Override
    public PelinPelaaja createT(ResultSet rs) throws Exception {
        int pelaajaid = rs.getInt("pelaajaid");
        int peliid = rs.getInt("peliid");
        boolean valkoinen = rs.getBoolean("valkoinen");
        
        return new PelinPelaaja(pelaajaid, peliid, valkoinen);
    }

    @Override
    public List<Object> decomposeT(PelinPelaaja t) {
        List<Object> lista = new ArrayList<>();
        
        lista.add(t.getPelaaja());
        lista.add(t.getPeli());
        lista.add(t.isValkoinen());
        
        return lista;
    }

    @Override
    public int getId(PelinPelaaja t) {
        return t.getPelaaja();
    }
    
}
