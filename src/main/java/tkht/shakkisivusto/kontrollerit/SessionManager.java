package tkht.shakkisivusto.kontrollerit;

import java.util.*;
import tkht.shakkisivusto.domain.Pelaaja;

public class SessionManager {
    
    private Map<String, Pelaaja> sessions;
    private Map<String, Long> sessionTimes;
    
    private long lastCheck;
    private final long refreshRate;
    private final int sessionAliveTime;
    
    public SessionManager(long refreshRate, int sessionAliveTime){
        sessions = new HashMap<>();
        sessionTimes = new HashMap<>();
        
        this.refreshRate = refreshRate;
        this.sessionAliveTime = sessionAliveTime;
        this.lastCheck = System.currentTimeMillis();
    }
    
    public Pelaaja getPelaaja(String sessionName){
        if(!valid(sessionName)){
            
            removeSession(sessionName);
            return null;
            
        }
        
        return sessions.get(sessionName);
    }
    
    public boolean valid(String sessionName){
        Long time = sessionTimes.get(sessionName);
        
        if(time == null){
            return false;
        }
        
        long now = System.currentTimeMillis();
        
        return time >= now;
    }
    
    public boolean addSession(String sessionName, Pelaaja pelaaja){
        if(sessions.get(sessionName) != null){
            return false;
        }
        
        sessions.put(sessionName, pelaaja);
        sessionTimes.put(sessionName, sessionAliveTime + lastCheck);
        
        return true;
    }
    
    public void removeSession(String sessionName){
        sessions.remove(sessionName);
        sessionTimes.remove(sessionName);
    }
    
    public void removeDeadSessions(){
        long now = System.currentTimeMillis();
        
        if(now < lastCheck+refreshRate){
            return;
        }
        
        List<String> toRemove = new ArrayList<>();
        
        for(String key : sessionTimes.keySet()){
            long toDie = sessionTimes.get(key);
            
            if(toDie < now){
                toRemove.add(key);
            }
        }
        
        for(String session : toRemove){
            removeSession(session);
        }
    }
    
    public String generateSessionString(){
        String session;
        
        while(true){
            session = "";
        
            Random r = new Random();

            for(int i = 0 ; i < 16 ; i++){
                char[] k = Character.toChars(r.nextInt(28));
                session += k;
            }
            
            if(sessions.get(session) == null){
                break;
            }
        
        }
        
        return session;
    }

    public int getSessionAliveTime() {
        return sessionAliveTime;
    }
    
}
