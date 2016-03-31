package tkht.shakkisivusto.tietokanta;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
    
    DataSource yhteysVarasto;
    
    public Database() throws Exception{
        InitialContext cxt = new InitialContext();
        yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");
    }
    
    public Connection getConnection() throws Exception{
        return yhteysVarasto.getConnection();
    }
}
