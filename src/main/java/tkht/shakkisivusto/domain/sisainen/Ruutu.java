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
    
    @Override
    public String toString(){
        char xk = tulkitse(x);
        
        String teksti = ""+xk;
        teksti += y;
        
        return teksti;
    }
}
