package tkht.shakkisivusto.tietokanta.luojat;

import java.util.List;

public abstract class AbstraktiYhdistaja<T> implements Luoja<T>{
    
    protected void yhdista(List mitka, List mihin){
        
        for(Object yhdistetaan : mitka){
            for(Object yhdistyv : mihin){
                Yhdistettava yhdistyva = (Yhdistettava) yhdistyv;
                
                yhdistyva.yhdista(yhdistetaan);
            }
        }
    }
    
}
