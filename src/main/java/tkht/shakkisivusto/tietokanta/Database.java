package tkht.shakkisivusto.tietokanta;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.port;

public class Database {
    
    private String osoite;
    
    public Database() throws Exception{
        osoite = System.getenv("DATABASE_URL");
        port(Integer.valueOf(System.getenv("PORT")));
    }
    
    public void alustaTietokanta() throws Exception{
        suoritaKaskyt(pudotusKaskyt());
        
        suoritaKaskyt(tietokantataulut());
        suoritaKaskyt(testidata());
        test();
    }
    
    private void test() throws Exception{
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Peli, PelinPelaaja, Pelaaja");
        
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        String columnsString = "";
        for(int i = 1 ; i <= columns ; i++){
            columnsString= rsmd.getColumnName(i)+",";
        }
        
        System.out.println(columnsString);
        
        c.close();
    }
    
    private void suoritaKaskyt(List<String> kaskyt) throws Exception{
        Connection c = getConnection();
        
        for(String kasky : kaskyt){
            PreparedStatement ps = c.prepareStatement(kasky);
            ps.executeUpdate();
            ps.close();
        }
        
        c.close();
    }
    
    private List<String> tietokantataulut(){
        List<String> taulut = new ArrayList<>();
        
        taulut.add("CREATE TABLE Pelaaja(\n" +
                    "id serial primary key,\n" +
                    "kayttajanimi varchar(30) unique not null,\n" +
                    "pelaajanimi varchar(30) not null,\n" +
                    "salasana varchar(30) not null,\n" +
                    "admin boolean not null\n" +
                    ")");
        taulut.add("CREATE TABLE Peli(\n" +
                    "id serial primary key,\n" +
                    "nimi varchar(50) not null,\n" +
                    "status varchar(25) not null\n" +
                    ")");
        taulut.add("CREATE TABLE Pelinpelaaja(\n" +
                    "pelaajaid int not null,\n" +
                    "peliid int not null,\n" +
                    "valkoinen boolean not null,\n" +
                    "voittaja boolean not null,\n" +
                    "FOREIGN KEY (pelaajaid) REFERENCES Pelaaja(id),\n" +
                    "FOREIGN KEY (peliid) REFERENCES Peli(id),\n" +
                    "CONSTRAINT pk_pelinpelaaja PRIMARY KEY (pelaajaid, peliid)\n" +
                    ")");
        taulut.add("CREATE TABLE Vuoro(\n" +
                    "vuoro int not null,\n" +
                    "peliid int not null,\n" +
                    "pelaaja int,\n" +
                    "lauta varchar(200) not null,\n" +
                    "erikoistilanteet varchar(20) not null,\n" +
                    "tekoaika timestamp not null,\n" +
                    "FOREIGN KEY (peliid) REFERENCES Peli(id),\n" +
                    "FOREIGN KEY (pelaaja) REFERENCES Pelaaja(id),\n" +
                    "CONSTRAINT pk_vuoro PRIMARY KEY (vuoro, peliid)\n" +
                    ")");
        
        return taulut;
    }
    
    private List<String> pudotusKaskyt(){
        List<String> kaskyt = new ArrayList<>();
        
        kaskyt.add("DROP TABLE IF EXISTS Vuoro CASCADE;");
        kaskyt.add("DROP TABLE IF EXISTS Pelinpelaaja CASCADE;");
        kaskyt.add("DROP TABLE IF EXISTS Peli CASCADE;");
        kaskyt.add("DROP TABLE IF EXISTS Pelaaja CASCADE;");
        
        return kaskyt;
    }
    
    private List<String> testidata(){List<String> kaskyt = new ArrayList<>();
        
        kaskyt.add("INSERT INTO Pelaaja (kayttajanimi, pelaajanimi, salasana, admin) VALUES ('asd', 'asd', 'asd', false)");
//        kaskyt.add("INSERT INTO Pelaaja (kayttajanimi, pelaajanimi, salasana, admin) VALUES ('sdf', 'sdf', 'sdf', false)");
//        
//        kaskyt.add("INSERT INTO Peli (nimi, status) VALUES ('asd', 'HAETAAN VASTAPELAAJAA')");
//        
//        kaskyt.add("INSERT INTO Pelinpelaaja (pelaajaid, peliid, valkoinen, voittaja) VALUES (1, 1, true, false)");
        
        return kaskyt;
    }
    
    public Connection getConnection() throws Exception{
        URI dbUri = new URI(osoite);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
           
    }
}
