package tkht.shakkisivusto.domain.sisainen;

public enum Nappulatyyppi {
    
    SOTILAS, LAHETTI, RATSU, TORNI, KUNINGATAR, KUNINGAS;
    
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
