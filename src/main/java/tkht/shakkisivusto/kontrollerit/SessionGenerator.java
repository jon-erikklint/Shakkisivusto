package tkht.shakkisivusto.kontrollerit;

import spark.Response;
import tkht.shakkisivusto.domain.Pelaaja;

public class SessionGenerator {
    
    private SessionManager sm;
    
    public SessionGenerator(SessionManager sm){
        this.sm = sm;
    }
    
    public void generateSession(Pelaaja kirjautuva, Response rspns){
        String sessionName;
        
        while(true){
                    
            sessionName = sm.generateSessionString();

            if(sm.addSession(sessionName, kirjautuva)){
                break;
            }

        }
        
        rspns.cookie("session", sessionName, sm.getSessionAliveTime());
    }
    
}
