package tkht.shakkisivusto.tietokanta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.domain.Peli;

public class PeliDao extends AbstraktiDao<Peli>{

    public PeliDao(Database db) {
        super(db, "Peli", "");
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
        
        hajoitettu.add(t.getId());
        hajoitettu.add(t.getNimi());
        hajoitettu.add(t.getStatus());
        
        return hajoitettu;
    }

    


    
}
