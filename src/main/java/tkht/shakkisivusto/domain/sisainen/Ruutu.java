package tkht.shakkisivusto.domain.sisainen;

public class Ruutu {
    
    private int x;
    private int y;

    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Ruutu(String sijainti) {
        x = sijainti.charAt(0) - 'a' + 1;
        y = Integer.parseInt(""+sijainti.charAt(1));
    }
    
    public char tulkitse(int n){
        switch(n){
            case 1:
                return 'a';
            case 2:
                return 'b';
            case 3:
                return 'c';
            case 4:
                return 'd';
            case 5:
                return 'e';
            case 6:
                return 'f';
            case 7:
                return 'g';
            case 8:
                return 'h';
        }
        
        return '0';
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Ruutu getSuunnasta(Suunta suunta){
        switch(suunta){
            case YLOS:
                return getYlos();
            case ALAS:
                return getAlas();
            case OIKEA:
                return getOikea();
            case VASEN:
                return getVasen();
            case YLAOIKEA:
                return getYlaoikea();
            case YLAVASEN:
                return getYlavasen();
            case ALAOIKEA:
                return getAlaoikea();
            case ALAVASEN:
                return getAlavasen();
        }
        
        return null;
    }
    
    public Ruutu getYlos(){
        return getVerraten(x, y+1);
    }
    
    public Ruutu getAlas(){
        return getVerraten(x, y-1);
    }
    
    public Ruutu getOikea(){
        return getVerraten(x+1, y);
    }
    
    public Ruutu getVasen(){
        return getVerraten(x-1, y);
    }
    
    public Ruutu getYlaoikea(){
        return getVerraten(x+1, y-1);
    }
    
    public Ruutu getYlavasen(){
        return getVerraten(x-1, y-1);
    }
    
    public Ruutu getAlaoikea(){
        return getVerraten(x+1, y+1);
    }
    
    public Ruutu getAlavasen(){
        return getVerraten(x-1, y+1);
    }
    
    public Ruutu getVerraten(int x, int y){
        if(tarkistaLuku(x) && tarkistaLuku(y)){
            return new Ruutu(x, y);
        }
        
        return null;
    }
    
    private boolean tarkistaLuku(int n){
        return n > 0 && n < 9;
    }
    
    @Override
    public String toString(){
        char xk = tulkitse(x);
        
        String teksti = ""+xk;
        teksti += y;
        
        return teksti;
    }
}
