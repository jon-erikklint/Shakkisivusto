package tkht.shakkisivusto.tietokanta;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    private String osoite;
    
    public Database() throws Exception{
        osoite = System.getenv("DATABASE_URL");
    }
    
    public Connection getConnection() throws Exception{
        URI dbUri = new URI(osoite);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
           
    }
}
