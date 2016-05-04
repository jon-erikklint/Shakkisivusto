package tkht.shakkisivusto.tietokanta.luojat;

import tkht.shakkisivusto.domain.Peli;

public class PeliLuoja extends AbstraktiLuoja<Peli>{

    public PeliLuoja(String lisa) {
        super(lisa);
    }

    @Override
    protected Peli createT() throws Exception {
        int id = super.getInt("id");
        String nimi = super.getString("nimi");
        String status = super.getString("status");
        
        return new Peli(id, nimi, status);
    }

}
