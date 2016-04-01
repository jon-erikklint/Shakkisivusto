package tkht.shakkisivusto.tietokanta;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    private String osoite;
    private String kayttaja;
    private String salasana;
    
    public Database() throws Exception{
        osoite = "jdbc:postgresql://localhost/klint";
        kayttaja = "klint";
        salasana = "d1e04e393847d728";
    }
    
    public Connection getConnection() throws Exception{
        return DriverManager.getConnection(osoite, kayttaja, salasana);
    }
}
