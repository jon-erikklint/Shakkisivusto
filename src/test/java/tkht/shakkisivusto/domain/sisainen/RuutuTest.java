/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tkht.shakkisivusto.domain.sisainen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {
    
    public RuutuTest() {}
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void arvotSijoitetaanOikein(){
        Ruutu ruutu = new Ruutu(1,1);
        assertEquals(1, ruutu.getX());
        assertEquals(1, ruutu.getY());
    }
    
    @Test
    public void kirjainTulkitaanOikein(){
        Ruutu ruutu = new Ruutu(1,1);
        assertEquals('a', ruutu.tulkitse(1));
        assertEquals('b', ruutu.tulkitse(2));
        assertEquals('c', ruutu.tulkitse(3));
        assertEquals('d', ruutu.tulkitse(4));
        assertEquals('e', ruutu.tulkitse(5));
        assertEquals('f', ruutu.tulkitse(6));
        assertEquals('g', ruutu.tulkitse(7));
        assertEquals('h', ruutu.tulkitse(8));
        assertEquals('0', ruutu.tulkitse(54));
    }
    
    @Test
    public void lukuTarkistetaanOikein(){
        Ruutu ruutu = new Ruutu(1,1);
        
        assertEquals(true, ruutu.tarkistaLuku(1));
        assertEquals(true, ruutu.tarkistaLuku(8));
        assertEquals(false, ruutu.tarkistaLuku(0));
        assertEquals(false, ruutu.tarkistaLuku(9));
    }
    
    @Test
    public void toStringToimii(){
        Ruutu ruutu = new Ruutu(1,1);
        Ruutu ruutu2 = new Ruutu(3,3);
        Ruutu ruutu3 = new Ruutu(8,7);
        
        assertEquals("a1", ruutu.toString());
        assertEquals("c3", ruutu2.toString());
        assertEquals("h7", ruutu3.toString());
    }
    
    @Test
    public void getVerratenToimiiOikeilla(){
        Ruutu ruutu = new Ruutu(4,4);
        
        Ruutu siirretty = ruutu.getVerraten(0, 0);
        assertEquals(4, siirretty.getX());
        assertEquals(4, siirretty.getY());
        
        siirretty = ruutu.getVerraten(2, 2);
        assertEquals(6, siirretty.getX());
        assertEquals(6, siirretty.getY());
        
        siirretty = ruutu.getVerraten(-2, -3);
        assertEquals(2, siirretty.getX());
        assertEquals(1, siirretty.getY());
    }
    
    @Test
    public void getVerratenToimiiVaarilla(){
        Ruutu ruutu = new Ruutu(2, 7);
        
        Ruutu siirretty = ruutu.getVerraten(-2, 0);
        assertEquals(siirretty, null);
        
        siirretty = ruutu.getVerraten(0, 2);
        assertEquals(siirretty, null);
        
        siirretty = ruutu.getVerraten(-2, 3);
        assertEquals(siirretty, null);
    }
    
    @Test
    public void getSuunnallisetToimivatOikeilla(){
        Ruutu ruutu1 = new Ruutu(1,1);
        Ruutu ruutu2 = new Ruutu(8,8);
        
        Ruutu siirretty = ruutu1.getOikea();
        assertEquals(2, siirretty.getX());
        assertEquals(1, siirretty.getY());
        
        siirretty = ruutu1.getAlas();
        assertEquals(1, siirretty.getX());
        assertEquals(2, siirretty.getY());
        
        siirretty = ruutu2.getVasen();
        assertEquals(7, siirretty.getX());
        assertEquals(8, siirretty.getY());
        
        siirretty = ruutu2.getYlos();
        assertEquals(8, siirretty.getX());
        assertEquals(7, siirretty.getY());
        
        siirretty = ruutu1.getAlaoikea();
        assertEquals(2, siirretty.getX());
        assertEquals(2, siirretty.getY());
        
        siirretty = ruutu2.getYlavasen();
        assertEquals(7, siirretty.getX());
        assertEquals(7, siirretty.getY());
        
        siirretty = siirretty.getAlavasen();
        assertEquals(6, siirretty.getX());
        assertEquals(8, siirretty.getY());
        
        siirretty = siirretty.getYlaoikea();
        assertEquals(7, siirretty.getX());
        assertEquals(7, siirretty.getY());
    }
    
    @Test
    public void getSuunnallisetToimivatVaarilla(){
        Ruutu ruutu1 = new Ruutu(1,1);
        Ruutu ruutu2 = new Ruutu(8,8);
        
        Ruutu siirretty = ruutu1.getYlos();
        assertEquals(siirretty, null);
        
        siirretty = ruutu1.getVasen();
        assertEquals(siirretty, null);
        
        siirretty = ruutu1.getYlavasen();
        assertEquals(siirretty, null);
        
        siirretty = ruutu1.getAlavasen();
        assertEquals(siirretty, null);
        
        siirretty = ruutu2.getAlas();
        assertEquals(siirretty, null);
        
        siirretty = ruutu2.getOikea();
        assertEquals(siirretty, null);
        
        siirretty = ruutu2.getYlaoikea();
        assertEquals(siirretty, null);
        
        siirretty = ruutu2.getAlaoikea();
        assertEquals(siirretty, null);
    }
}
