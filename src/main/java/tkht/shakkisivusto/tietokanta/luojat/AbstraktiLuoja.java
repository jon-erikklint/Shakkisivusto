package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstraktiLuoja<T> implements Luoja<T>{

    private Map<T, T> results;

    public AbstraktiLuoja(){
        results = new HashMap<>();
    }
    
    @Override
    public void addT(ResultSet rs) throws Exception{
        T t = createT(rs);
        
        if(results.get(t) == null){
            results.put(t, t);
        }
    }
    
    protected abstract T createT(ResultSet rs) throws Exception;

    @Override
    public List<T> getAll() {
        List<T> toBeReturned = new ArrayList<>(results.keySet());
        
        results = new HashMap<>();
        
        return toBeReturned;
    }

    @Override
    public T getOne(ResultSet rs) throws Exception {
        return createT(rs);
    }
    
}
