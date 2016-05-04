package tkht.shakkisivusto.tietokanta.luojat;

import tkht.shakkisivusto.domain.Pelaaja;

public class PelaajaLuoja extends AbstraktiLuoja<Pelaaja>{

    public PelaajaLuoja(String lisa) {
        super(lisa);
    }

    @Override
    protected Pelaaja createT() throws Exception{
        int indeksi = super.getInt("id");
        String nimi = super.getString("pelaajanimi");
        String kayttajanimi = super.getString("kayttajanimi");
        String salasana = super.getString("salasana");
        boolean admin = super.getBoolean("admin");
        
        return new Pelaaja(indeksi, nimi, kayttajanimi, salasana, admin);
    }
    
}
