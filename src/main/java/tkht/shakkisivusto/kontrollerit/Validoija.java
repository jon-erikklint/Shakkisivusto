package tkht.shakkisivusto.kontrollerit;

import java.util.HashSet;
import java.util.Set;

public class Validoija {
    
    private static Set<Character> sallitutMerkit;
    private static Set<Character> koordinaatit;
    
    public static void alusta(String merkit){
        sallitutMerkit = alustaSet(merkit);
        koordinaatit = alustaSet("abcdefgh");
    }
    
    private static Set<Character> alustaSet(String string){
        Set<Character> merkit = new HashSet<>();
        
        for(int i = 0 ; i < string.length() ; i++){
            char kirjain = string.charAt(i);
            merkit.add(kirjain);
        }
        
        return merkit;
    }
    
    private static boolean tarkistaMerkit(Set<Character> sallitut, String string){
        for(int i = 0 ; i < string.length() ; i++){
            char kirjain = string.charAt(i);
            
            if(!sallitut.contains(kirjain)){
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean tarkistaPelaajanKayttajanimi(String nimi){
        if(!tarkistaMerkit(sallitutMerkit, nimi)){
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
        if(!tarkistaMerkit(sallitutMerkit, salasana)){
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
        if(!tarkistaMerkit(sallitutMerkit, nimi)){
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
    
    public static boolean tarkistaKoordinaatti(String koordinaatti){
        if(koordinaatti.length() != 2){
            return false;
        }
        
        if(!tarkistaMerkit(koordinaatit, koordinaatti.substring(0, 1))){
            return false;
        }
        
        try{
            int luku = Integer.parseInt(koordinaatti.substring(1, 2));
            
            if(luku > 8 && luku < 1){
                return false;
            }
        }catch(Exception e){
            return false;
        }
        
        return true;
    }
    
}
