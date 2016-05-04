package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstraktiLuoja<T> implements Luoja<T>{

    private String etuliite;
    
    private Map<T, T> results;
    
    private ResultSet rs;

    public AbstraktiLuoja(String lisa){
        results = new HashMap<>();
    }
    
    @Override
    public void addT(ResultSet rs) throws Exception{
        this.rs = rs;
        
        T t = createT();
        
        if(results.get(t) == null){
            results.put(t, t);
        }
    }
    
    protected abstract T createT() throws Exception;

    @Override
    public List<T> getAll() {
        List<T> toBeReturned = new ArrayList<>(results.keySet());
        
        results = new HashMap<>();
        
        return toBeReturned;
    }

    @Override
    public T getOne(ResultSet rs) throws Exception {
        return createT();
    }
    
    protected String getString(String column) throws Exception{
        return rs.getString(etuliite+column);
    }
    
    protected int getInt(String column) throws Exception{
        return rs.getInt(etuliite+column);
    }
    
    protected Timestamp getTimestamp(String column) throws Exception{
        return rs.getTimestamp(etuliite+column);
    }
    
    protected boolean getBoolean(String column) throws Exception{
        return rs.getBoolean(etuliite+column);
    }
}
