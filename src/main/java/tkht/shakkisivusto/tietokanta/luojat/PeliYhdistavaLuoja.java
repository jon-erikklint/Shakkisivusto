package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.util.List;
import tkht.shakkisivusto.domain.Pelaaja;
import tkht.shakkisivusto.domain.Peli;
import tkht.shakkisivusto.domain.PelinPelaaja;
import tkht.shakkisivusto.domain.Vuoro;

public class PeliYhdistavaLuoja extends AbstraktiYhdistaja<Peli>{
    
    private PeliLuoja peliLuoja;
    private PelinPelaajaLuoja pelinpelaajaLuoja;
    private VuoroLuoja vuoroLuoja;
    private PelaajaLuoja pelaajaLuoja;
    
    public PeliYhdistavaLuoja(){
        peliLuoja = new PeliLuoja();
        pelinpelaajaLuoja = new PelinPelaajaLuoja();
        vuoroLuoja = new VuoroLuoja();
        pelaajaLuoja = new PelaajaLuoja();
    }

    @Override
    public void addT(ResultSet rs) throws Exception {
        peliLuoja.addT(rs);
        
        try{
            pelaajaLuoja.addT(rs);
            pelinpelaajaLuoja.addT(rs);
        }catch(Exception e){}
        
        try{vuoroLuoja.addT(rs);}catch(Exception e){}
    }

    @Override
    public List<Peli> getAll() {
        List<PelinPelaaja> pelinPelaajat = pelinpelaajaLuoja.getAll();
        List<Pelaaja> pelaajat = pelaajaLuoja.getAll();
        List<Vuoro> vuorot = vuoroLuoja.getAll();
        List<Peli> pelit = peliLuoja.getAll();
        
        System.out.println("PP:T");
        for(PelinPelaaja pp : pelinPelaajat){
            System.out.println(pp);
        }
        System.out.println("PELAAJAT:");
        for(Pelaaja pelaaja : pelaajat){
            System.out.println(pelaaja);
        }
        System.out.println("VUOROT:");
        for(Vuoro vuoro : vuorot){
            System.out.println(vuoro);
        }
        System.out.println("Pelit:");
        for(Peli peli: pelit){
            System.out.println(peli);
        }
        
        yhdista(pelaajat, pelinPelaajat);
        yhdista(pelinPelaajat, pelit);
        yhdista(vuorot, pelit);
        
        System.out.println("JÄLKEEN:");
        System.out.println("PP:T");
        for(PelinPelaaja pp : pelinPelaajat){
            System.out.println(pp);
        }
        System.out.println("PELAAJAT:");
        for(Pelaaja pelaaja : pelaajat){
            System.out.println(pelaaja);
        }
        System.out.println("VUOROT:");
        for(Vuoro vuoro : vuorot){
            System.out.println(vuoro);
        }
        System.out.println("Pelit:");
        for(Peli peli: pelit){
            System.out.println(peli);
        }
        
        return pelit;
    }
    
    @Override
    public Peli getOne(ResultSet rs) throws Exception {
        peliLuoja.addT(rs);
        pelinpelaajaLuoja.addT(rs);
        vuoroLuoja.addT(rs);
        pelaajaLuoja.addT(rs);
        
        return getAll().get(0);
    }
    
}
