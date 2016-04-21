package tkht.shakkisivusto.tietokanta;

import java.util.List;

public interface Dao<T> {
    
    public T findOne(int index) throws Exception;
    public T findNewest() throws Exception;
    public List<T> findAll() throws Exception;
    
    public boolean add(T t) throws Exception;
    public int delete(List<String> conditions, List<Object> values) throws Exception;
    public int update(List<String> columns, List<Object> newValues, List<String> conditions, List<Object> values) throws Exception;
    
    public List<T> findByConditions(List<String> conditions, List<Object> parametres, 
            List<String> orderByConditions, boolean desc, int limit, int offset) throws Exception;
    
    public List<T> findByQuery(String query, List<Object> parametres) throws Exception;
    
    public int findIntByAggregate(String query, List<Object> parametres) throws Exception;
    public double findDoubleByAggregate(String query, List<Object> parametres) throws Exception;
    
}
