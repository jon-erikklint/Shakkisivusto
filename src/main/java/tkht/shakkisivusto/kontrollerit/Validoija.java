package tkht.shakkisivusto.kontrollerit;

import java.util.HashMap;
import java.util.Map;

public class Validoija {
    
    private static Map<Character,Character> sallitutMerkit;
    
    public static void alusta(String merkit){
        sallitutMerkit = new HashMap<>();
        
        for(int i = 0 ; i < merkit.length() ; i++){
            char kirjain = merkit.charAt(i);
            sallitutMerkit.put(kirjain, kirjain);
        }
    }
    
    private static boolean tarkistaMerkit(String string){
        for(int i = 0 ; i < string.length() ; i++){
            Character kirjain = sallitutMerkit.get(string.charAt(i));
            
            if(kirjain == null){
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean tarkistaPelaajanKayttajanimi(String nimi){
        if(!tarkistaMerkit(nimi)){
            return false;
        }
        
        if(nimi.length() < 3){
            return false;
        }
        
        if(nimi.length() > 30){
            return false;
        }
        
        return true;
    }
    
    public static boolean tarkistaPelaajanSalasana(String salasana){
        if(!tarkistaMerkit(salasana)){
            return false;
        }
        
        if(salasana.length() < 3){
            return false;
        }
        
        if(salasana.length() > 30){
            return false;
        }
        
        return true;
    }
    
    public static boolean tarkistaPelinNimi(String nimi){
        if(!tarkistaMerkit(nimi)){
            return false;
        }
        
        if(nimi.length() < 3){
            return false;
        }
        
        if(nimi.length() > 50){
            return false;
        }
        
        return true;
    }
    
}
