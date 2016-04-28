package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import tkht.shakkisivusto.domain.Peli;

public class PeliLuoja extends AbstraktiLuoja<Peli>{

    @Override
    protected Peli createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        String status = rs.getString("status");
        
        System.out.println("PELILUOJA TOIMII");
        
        return new Peli(id, nimi, status);
    }

}
