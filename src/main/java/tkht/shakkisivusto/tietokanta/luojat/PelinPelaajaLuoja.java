package tkht.shakkisivusto.tietokanta.luojat;

import tkht.shakkisivusto.domain.PelinPelaaja;

public class PelinPelaajaLuoja extends AbstraktiLuoja<PelinPelaaja>{

    public PelinPelaajaLuoja(String lisa) {
        super(lisa);
    }

    @Override
    protected PelinPelaaja createT() throws Exception {
        int pelaajaid = super.getInt("pelaajaid");
        int peliid = super.getInt("peliid");
        boolean valkoinen = super.getBoolean("valkoinen");
        boolean voittaja = super.getBoolean("voittaja");
        
        return new PelinPelaaja(pelaajaid, peliid, valkoinen, voittaja);
    }
    
}
