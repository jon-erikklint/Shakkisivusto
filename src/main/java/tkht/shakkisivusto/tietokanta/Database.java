package tkht.shakkisivusto.tietokanta;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database {
    
    private String osoite;
    
    public Database() throws Exception{
        osoite = System.getenv("DATABASE_URL");
    }
    
    public void alustaTietokanta() throws Exception{
        if(tietokantaPystyssa()){
            suoritaKaskyt(pudotusKaskyt());
        }
        
        suoritaKaskyt(tietokantataulut());
        suoritaKaskyt(testidata());
    }
    
    private boolean tietokantaPystyssa(){
        try{
            
            Connection c = getConnection();
            c.prepareStatement("SELECT * FROM Vuoro").executeQuery();
            
        }catch(Exception e){
            return false;
        }
        
        return true;
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
                    "kayttajanimi varchar(50) unique not null,\n" +
                    "pelaajanimi varchar(50) not null,\n" +
                    "salasana varchar(30) not null,\n" +
                    "admin boolean not null\n" +
                    ")");
        taulut.add("CREATE TABLE Peli(\n" +
                    "id serial primary key,\n" +
                    "nimi varchar(50) not null,\n" +
                    "status varchar(25) not null,\n" +
                    "voittaja int,\n" +
                    "FOREIGN KEY (voittaja) REFERENCES Pelaaja(id)\n" +
                    ")");
        taulut.add("CREATE TABLE Pelinpelaaja(\n" +
                    "pelaajaid int not null,\n" +
                    "peliid int not null,\n" +
                    "valkoinen boolean not null,\n" +
                    "FOREIGN KEY (pelaajaid) REFERENCES Pelaaja(id),\n" +
                    "FOREIGN KEY (peliid) REFERENCES Peli(id),\n" +
                    "CONSTRAINT pk_pelinpelaaja PRIMARY KEY (pelaajaid, peliid)\n" +
                    ")");
        taulut.add("CREATE TABLE Vuoro(\n" +
                    "vuoro int not null,\n" +
                    "peliid int not null,\n" +
                    "pelaaja int,\n" +
                    "lauta varchar(100) not null,\n" +
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
    
    private List<String> testidata(){
        List<String> kaskyt = new ArrayList<>();
        
        kaskyt.add("INSERT INTO Pelaaja (kayttajanimi, pelaajanimi, salasana, admin) VALUES ('asd', 'asd', 'asd', false)");
        
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
