package tkht.shakkisivusto.tietokanta.luojat;

import java.util.List;

public abstract class AbstraktiYhdistaja<T> implements Luoja<T>{
    
    protected void yhdista(List yhdistettavat, List yhdistyvat){
        
        for(Object yhdistetaan : yhdistettavat){
            for(Object yhdistyv : yhdistyvat){
                Yhdistettava yhdistyva = (Yhdistettava) yhdistyv;
                
                yhdistyva.yhdista(yhdistetaan);
            }
        }
    }
    
}
