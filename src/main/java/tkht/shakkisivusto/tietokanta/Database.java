package tkht.shakkisivusto.tietokanta;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    private String osoite;
    
    public Database(String osoite){
        this.osoite = osoite;
    }
    
    public Connection getConnection() throws Exception{
        return DriverManager.getConnection(osoite);
    }
}
