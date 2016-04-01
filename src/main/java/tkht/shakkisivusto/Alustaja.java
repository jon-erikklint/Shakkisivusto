package tkht.shakkisivusto;

import tkht.shakkisivusto.tietokanta.*;

public class Alustaja {
    
    PelaajaDao pelaajaDao;
    
    public void alusta() throws Exception{
        alustaTietokanta();
        alustaKontrollerit();
    }
    
    public void alustaTietokanta() throws Exception{
        Database db = new Database();
        
        pelaajaDao = new PelaajaDao(db);
    }
    
    public void alustaKontrollerit(){
        
    }
}
