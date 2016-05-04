package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstraktiLuoja<T> implements Luoja<T>{

    private Set<T> results;

    public AbstraktiLuoja(){
        results = new HashSet<>();
    }
    
    @Override
    public void addT(ResultSet rs) throws Exception{
        results.add(createT(rs));
    }
    
    protected abstract T createT(ResultSet rs) throws Exception;

    @Override
    public List<T> getAll() {
        List<T> toBeReturned = new ArrayList<>(results);
        
        results = new HashSet<>();
        
        return toBeReturned;
    }

    @Override
    public T getOne(ResultSet rs) throws Exception {
        return createT(rs);
    }
}
