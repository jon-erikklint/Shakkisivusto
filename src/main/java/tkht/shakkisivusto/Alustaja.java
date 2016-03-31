package tkht.shakkisivusto;

import tkht.shakkisivusto.tietokanta.*;

public class Alustaja {
    
    public void alusta() throws Exception{
        alustaTietokanta();
        alustaKontrollerit();
    }
    
    public void alustaTietokanta() throws Exception{
        Database db = new Database();
    }
    
    public void alustaKontrollerit(){
        
    }
}
