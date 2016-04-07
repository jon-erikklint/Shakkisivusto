package tkht.shakkisivusto.domain.sisainen;

public enum Nappulatyyppi {
    
    SOTILAS('s'), LAHETTI('l'), RATSU('r'), TORNI('t'), KUNINGATAR('g'), KUNINGAS('k');
    
    private char kirjain;
    
    Nappulatyyppi(char kirjain){
        this.kirjain = kirjain;
    }
    
    @Override
    public String toString(){
        return ""+kirjain;
    }
    
    public static Nappulatyyppi getTyyppi(char kirjain){
        switch(kirjain){
            case 's':
                return SOTILAS;
            case 'l':
                return LAHETTI;
            case 'r':
                return RATSU;
            case 't':
                return TORNI;
            case 'k':
                return KUNINGAS;
            default:
                return KUNINGATAR;
        }
    }
}
