 package tkht.shakkisivusto.tietokanta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tkht.shakkisivusto.tietokanta.luojat.Luoja;

public abstract class AbstraktiDao<T> implements Dao<T>{

    private Database db;
    
    private String taulu;
    private String columns;
    
    private Luoja<T> luoja;
    
    public AbstraktiDao(Database db, String taulu, String columns, Luoja<T> luoja){
        this.db = db;
        this.taulu = taulu;
        this.columns = columns;
        this.luoja = luoja;
    }
    
    public void addExtra(ResultSet rs, T t, int ekstra){};
    public abstract List<Object> decomposeT(T t);
    public abstract int getId(T t);
    
    protected List createList(Object... os){
        List objects = new ArrayList<>();
        
        for(Object o : os){
            objects.add(o);
        }
        
        return objects;
    }
    
    private void setPreparedStatementValues(PreparedStatement ps, List<Object> values) throws Exception{
        for(int i = 0 ; i < values.size() ; i++){
            int j = i+1;
            Object o = values.get(i);
            
            if(o == null){
                ps.setObject(j, "");
                continue;
            }
            
            Class clas = o.getClass();
            
            if(clas == Timestamp.class){
                ps.setTimestamp(j, (Timestamp) o);
            }else if(clas == Integer.class){
                ps.setInt(j, (Integer) o);
            }else if(clas == String.class){
                ps.setString(j, (String) o);
            }else if(clas == Boolean.class){
                ps.setBoolean(j, (Boolean) o);
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
        return addStrings(preConditionQuery, conditions, " AND ");
    }
    
    public String addColumns(String query, List<String> columns){
        return addStrings(query, columns, "= ?, ")+"= ?";
    }
    
    private String addStrings(String query, List<String> toAdd, String between){
        String readyQuery = query;
        
        for(int i = 0 ; i < toAdd.size() ; i++){
            String condition = toAdd.get(i);
            readyQuery += condition;
            
            if(i < toAdd.size()-1){
                readyQuery += between;
            }
        }
        
        return readyQuery;
    }

    @Override
    public T findOne(int index) throws Exception {
        return findOne(index, luoja);
    }
    
    public T findOne(int index, Luoja<T> luoja) throws Exception{
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement("SELECT * FROM "+taulu+" WHERE id=?");
        ps.setInt(1, index);
        ResultSet rs = ps.executeQuery();
        
        T t = null;
        
        if(rs.next()){
            t = luoja.getOne(rs);
        }
        
        rs.close();
        ps.close();
        c.close();
        
        return t;
    }
    
    @Override
    public T findNewest() throws Exception{
        return findNewest(luoja);
    }
    
    public T findNewest(Luoja<T> luoja) throws Exception{
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement("SELECT * FROM "+taulu+" ORDER BY id DESC");
        
        ResultSet rs = ps.executeQuery();
        
        T t = null;
        
        if(rs.next()){
            t = luoja.getOne(rs);
        }
        
        ps.close();
        c.close();
        
        return t;
    }

    @Override
    public List<T> findAll() throws Exception {
        return findAll(luoja);
    }
    
    public List<T> findAll(Luoja<T> luoja) throws Exception{
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement("SELECT * FROM "+taulu);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            luoja.addT(rs);
        }
        
        rs.close();
        ps.close();
        c.close();
        
        return luoja.getAll();
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
    
    public int delete(T t) throws Exception{
        List<String> conditions = new ArrayList<>();
        conditions.add("id=?");
        List<Object> values = new ArrayList<>();
        values.add(getId(t));
        
        return delete(conditions, values);
    }
    
    @Override
    public int update(List<String> columns, List<Object> newValues, List<String> conditions, List<Object> values) throws Exception{
        Connection c = db.getConnection();
        
        String query = "UPDATE "+taulu+" SET ";
        query = addColumns(query, columns);
        query += " WHERE ";
        query = addConditions(query, conditions);
        
        PreparedStatement ps = c.prepareStatement(query);
        newValues.addAll(values);
        
        setPreparedStatementValues(ps, newValues);
        
        int updated = ps.executeUpdate();
        
        ps.close();
        c.close();
        
        return updated;
    }
    
    public int update(List<String> columns, List<Object> newValues, int id) throws Exception{
        List<String> condition = new ArrayList<>();
        condition.add("id=?");
        List<Object> values = new ArrayList<>();
        values.add(id);
        
        return update(columns, newValues, condition, values);
    }

    @Override
    public List<T> findByConditions(List<String> conditions, List<Object> values, List<String> orderConditions, boolean desc, int limit, int offset) throws Exception{
        return findByConditions(conditions, values, orderConditions, desc, limit, offset, luoja);
    }
    
    public List<T> findByConditions(List<String> conditions, List<Object> values, List<String> orderConditions, boolean desc, int limit, int offset, Luoja luoja) throws Exception{
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
        
        List<T> ts = findByQuery(query, allValues, luoja);
        
        return ts;
    }
    
    @Override
    public List<T> findByQuery(String query, List<Object> values) throws Exception{
        return findByQuery(query, values, luoja);
    }
    
    public List<T> findByQuery(String query, List<Object> values, Luoja<T> luoja) throws Exception{
        Connection c = db.getConnection();
        
        PreparedStatement ps = c.prepareStatement(query);
        setPreparedStatementValues(ps, values);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            luoja.addT(rs);
        }
        
        rs.close();
        ps.close();
        c.close();
        
        return luoja.getAll();
    }
    
    @Override
    public int findIntByAggregate(String query, List<Object> values) throws Exception{
        Connection c = db.getConnection();
        PreparedStatement ps = c.prepareStatement(query);
        setPreparedStatementValues(ps, values);
        
        ResultSet rs = ps.executeQuery();
        
        rs.next();
        
        int result = rs.getInt(1);
        
        rs.close();
        ps.close();
        c.close();
        
        return result;
    }
    
    @Override
    public double findDoubleByAggregate(String query, List<Object> values) throws Exception{
        Connection c = db.getConnection();
        PreparedStatement ps = c.prepareStatement(query);
        setPreparedStatementValues(ps, values);
        
        ResultSet rs = ps.executeQuery();
        
        rs.next();
        
        double result = rs.getInt(1);
        
        rs.close();
        ps.close();
        c.close();

        return result; 
    }
    
}
