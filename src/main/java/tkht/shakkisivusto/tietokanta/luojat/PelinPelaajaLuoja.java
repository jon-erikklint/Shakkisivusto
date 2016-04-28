package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import tkht.shakkisivusto.domain.PelinPelaaja;

public class PelinPelaajaLuoja extends AbstraktiLuoja{

    @Override
    protected Object createT(ResultSet rs) throws Exception {
        int pelaajaid = rs.getInt("pelaajaid");
        int peliid = rs.getInt("peliid");
        boolean valkoinen = rs.getBoolean("valkoinen");
        boolean voittaja = rs.getBoolean("voittaja");
        
        return new PelinPelaaja(pelaajaid, peliid, valkoinen, voittaja);
    }
    
}
