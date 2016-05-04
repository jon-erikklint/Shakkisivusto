package tkht.shakkisivusto.domain.sisainen;

import java.util.ArrayList;
import java.util.List;

public enum Suunta {
    YLOS, ALAS, OIKEA, VASEN, YLAOIKEA, YLAVASEN, ALAVASEN, ALAOIKEA;
    
    public static Suunta valkoinenLiike(){
        return YLOS;
    }
    
    public static Suunta mustaLiike(){
        return ALAS;
    }
    
    public static List<Suunta> valkoisenHyokkaykset(){
        List<Suunta> suunnat = new ArrayList<>();
        suunnat.add(YLAOIKEA);
        suunnat.add(YLAVASEN);
        return suunnat;
    }
    
    public static List<Suunta> mustanHyokkaykset(){
        List<Suunta> suunnat = new ArrayList<>();
        suunnat.add(ALAOIKEA);
        suunnat.add(ALAVASEN);
        return suunnat;
    }
}
