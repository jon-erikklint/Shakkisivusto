package tkht.shakkisivusto.tietokanta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstraktiDao<T> implements Dao<T>{

    private Database db;
    private String taulu;
    private String columns;
    
    public AbstraktiDao(Database db, String taulu, String columns){
        this.db = db;
        this.taulu = taulu;
        this.columns = columns;
    }
    
    public abstract T createT(ResultSet rs) throws Exception;
    public abstract List<Object> decomposeT(T t);
    
    private void setPreparedStatementValues(PreparedStatement ps, List<Object> values) throws Exception{
        for(int i = 0 ; i < values.size() ; i++){
            Object o = values.get(i);
            Class clas = o.getClass();
            
            if(clas == String.class){
                ps.setString(i+1, (String) o);
            }else if(clas == Integer.class){
                ps.setInt(i+1, (Integer) o);
            }else if(clas == Timestamp.class){
                ps.setTimestamp(i+1, (Timestamp) o);
            }
        }
    }
    
    private String addQuestionMarks(String query, int amount){
        String newQuery = query;
        
        for(int i = 0 ; i < amount; i++){
            newQuery += "?";
            if(i < amount-1){
                newQuery += ", ";
            }
        }
        
        return newQuery;
    }
    
    public String addConditions(String preConditionQuery, List<String> conditions){
        String queryWithConditions = preConditionQuery;
        
        for(int i = 0 ; i < conditions.size() ; i++){
            String condition = conditions.get(i);
            queryWithConditions += condition;
            
            if(i < conditions.size()-1){
                queryWithConditions += " AND ";
            }
        }
        
        return queryWithConditions;
    }

    @Override
    public T findOne(int index) throws Exception {
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement("SELECT * FROM "+taulu+" WHERE id=?");
        ps.setInt(1, index);
        ResultSet rs = ps.executeQuery();
        
        if(!rs.next()){
            return null;
        }
        
        T t = createT(rs);
        
        rs.close();
        ps.close();
        c.close();
        
        return null;
    }

    @Override
    public List<T> findAll() throws Exception {
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement("SELECT * FROM "+taulu);
        ResultSet rs = ps.executeQuery();
        
        List<T> ts = new ArrayList<>();
        while(rs.next()){
            T t = createT(rs);
            ts.add(t);
        }
        
        rs.close();
        ps.close();
        c.close();
        
        return ts;
    }
    
    @Override
    public boolean add(T t) throws Exception {
        Connection c = db.getConnection();
        
        List<Object> decomposed = decomposeT(t);
        
        String query = "INSERT INTO "+taulu+" ("+columns+") VALUES (";
        query = addQuestionMarks(query, decomposed.size());
        query+=")";
        
        PreparedStatement ps = c.prepareStatement(query);
        setPreparedStatementValues(ps, decomposed);
        
        int createdAmount = ps.executeUpdate();
        
        ps.close();
        c.close();
        
        return createdAmount>0;
    }

    @Override
    public int delete(List<String> conditions, List<Object> values) throws Exception {
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement(addConditions("DELETE FROM "+taulu+" WHERE ", conditions) );
        setPreparedStatementValues(ps, values);
        
        int deleted = ps.executeUpdate();
        
        ps.close();
        c.close();
        
        return deleted;
    }

    @Override
    public List<T> findByConditions(List<String> conditions, List<Object> values, List<String> orderConditions, boolean desc, int limit, int offset) throws Exception{
        String query = "SELECT * FROM "+taulu+" WHERE ";
        query = addConditions(query, conditions);
        
        if(orderConditions != null){
            query += " ORDER BY ";
            query = addQuestionMarks(query, orderConditions.size());
            
            if(desc){
                query += " DESC";
            }
        }
        
        query += " LIMIT ?";
        query += " OFFSET ?";
        
        List<Object> allValues = new ArrayList<>();
        values.addAll(values);
        if(orderConditions != null){
            values.addAll(orderConditions);
        }
        values.add(limit);
        values.add(offset);
        
        List<T> ts = findByQuery(query, allValues);
        
        return ts;
    }
    
    @Override
    public List<T> findByQuery(String query, List<Object> values) throws Exception{
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement(query);
        setPreparedStatementValues(ps, values);
        ResultSet rs = ps.executeQuery();
        
        List<T> ts = new ArrayList<>();
        while(rs.next()){
            T t = createT(rs);
            ts.add(t);
        }
        
        rs.close();
        ps.close();
        c.close();
        
        return ts;
    }
    
     
}
