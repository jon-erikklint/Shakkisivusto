package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.Vuoro;

public class VuoroYhdistavaLuoja extends AbstraktiYhdistaja<Vuoro>{

    private PelaajaLuoja pelaajaLuoja;
    private PeliLuoja peliLuoja;
    private VuoroLuoja vuoroLuoja;
    
    public VuoroYhdistavaLuoja(){
        this.pelaajaLuoja = new PelaajaLuoja();
        this.peliLuoja = new PeliLuoja();
        this.vuoroLuoja = new VuoroLuoja();
    }
    
    @Override
    public void addT(ResultSet rs) throws Exception {
        pelaajaLuoja.addT(rs);
        peliLuoja.addT(rs);
        vuoroLuoja.addT(rs);
    }

    @Override
    public Vuoro getOne(ResultSet rs) throws Exception {
        addT(rs);
        
        List<Vuoro> lista = getAll();
        if(lista.isEmpty()){
            return null;
        }
        
        return lista.get(0);
    }

    @Override
    public List<Vuoro> getAll() {
        List<Pelaaja> pelaajat = pelaajaLuoja.getAll();
        List<Peli> pelit = peliLuoja.getAll();
        List<Vuoro> vuorot = vuoroLuoja.getAll();
        
        super.yhdista(pelit, vuorot);
        super.yhdista(pelaajat, vuorot);
        
        return vuorot;
    }
    
    
}
