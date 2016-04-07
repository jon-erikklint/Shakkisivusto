package tkht.shakkisivusto.domain.sisainen;

public class Erikoistilanteet {
    
    private boolean mustaTorni1;
    private boolean mustaTorni2;
    
    private boolean valkoinenTorni1;
    private boolean valkoinenTorni2;
    
    private Nappula passante;
    
    public Erikoistilanteet(String teksti){
        mustaTorni1 = tulkitse(teksti.charAt(0));
        mustaTorni2 = tulkitse(teksti.charAt(1));
        valkoinenTorni1 = tulkitse(teksti.charAt(2));
        valkoinenTorni2 = tulkitse(teksti.charAt(3));
        
        if(teksti.length() > 4){
            passante = new Nappula(teksti.substring(4));
        }
    }
    
    private boolean tulkitse(char c){
        if(c=='t'){
            return true;
        }
        
        return false;
    }
    
    private char tulkitse(boolean b){
        if(b){
            return 't';
        }
        
        return 'f';
    }

    public boolean isMustaTorni1() {
        return mustaTorni1;
    }

    public void setMustaTorni1(boolean mustaTorni1) {
        this.mustaTorni1 = mustaTorni1;
    }

    public boolean isMustaTorni2() {
        return mustaTorni2;
    }

    public void setMustaTorni2(boolean mustaTorni2) {
        this.mustaTorni2 = mustaTorni2;
    }

    public boolean isValkoinenTorni1() {
        return valkoinenTorni1;
    }

    public void setValkoinenTorni1(boolean valkoinenTorni1) {
        this.valkoinenTorni1 = valkoinenTorni1;
    }

    public boolean isValkoinenTorni2() {
        return valkoinenTorni2;
    }

    public void setValkoinenTorni2(boolean valkoinenTorni2) {
        this.valkoinenTorni2 = valkoinenTorni2;
    }

    public Nappula getPassante() {
        return passante;
    }

    public void setPassante(Nappula passante) {
        this.passante = passante;
    }
    
    @Override
    public String toString(){
        String teksti = ""+tulkitse(mustaTorni1);
        teksti += tulkitse(mustaTorni2);
        teksti += tulkitse(valkoinenTorni1);
        teksti += tulkitse(valkoinenTorni2);
        
        if(passante != null){
            teksti += passante.toString();
        }
        
        return teksti;
    }
}
