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
    
    
}
