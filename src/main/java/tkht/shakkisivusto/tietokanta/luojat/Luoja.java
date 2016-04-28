package tkht.shakkisivusto.tietokanta.luojat;

import java.sql.ResultSet;
import java.util.List;

public interface Luoja<T> {
    
    public void addT(ResultSet rs) throws Exception;
    
    public T getOne(ResultSet rs) throws Exception;
    
    public List<T> getAll();
    
}
