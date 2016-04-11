package tkht.shakkisivusto;

public class Main {
    
    public static void main(String[] args) throws Exception{
        Serveri.osoite = "localhost:4567";
        
        Alustaja a = new Alustaja();
        a.alusta();
    }
}
