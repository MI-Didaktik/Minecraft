

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse SpielTest.
 *
 * @author  Tim Busch, Beatrice Wellmann
 * @version 1
 */
public class SpielTest
{
    private Spielfeld spielfeld;
    private Feld f11;
    private Feld f12;
    private Feld f13;
    private Feld f21;
    private Feld f22;
    private Feld f23;
    private Feld f31;
    private Feld f32;
    private Feld f33;
    private Feld f44;

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
         Feld[][] felder = new Feld[4][4];
        f11 = new Feld(false, 0, Feldstatus.VERDECKT,0,0);
        felder[0][0] = f11;
        f12 = new Feld(false, 1, Feldstatus.VERDECKT,0,1);
        felder[0][1] = f12;
        f13 = new Feld(true, 1, Feldstatus.VERDECKT,0,2);
        felder[0][2] = f13;
        Feld f14 = new Feld(false, 2, Feldstatus.VERDECKT,0,3);
        felder[0][3] = f14;
        
        f21 = new Feld(false, 0, Feldstatus.VERDECKT,1,0);
        felder[1][0] = f21;
        f22 = new Feld(false, 1, Feldstatus.VERDECKT,1,1);
        felder[1][1] = f22;
         f23 = new Feld(false, 2, Feldstatus.VERDECKT,1,2);
        felder[1][2] = f23;
        Feld f24 = new Feld(true, 1, Feldstatus.VERDECKT,1,3);
        felder[1][3] = f24;
        
         f31 = new Feld(false, 0, Feldstatus.VERDECKT,2,0);
        felder[2][0] = f31;
         f32 = new Feld(false, 0, Feldstatus.VERDECKT,2,1);
        felder[2][1] = f32;
         f33 = new Feld(false, 2, Feldstatus.VERDECKT,2,2);
        felder[2][2] = f33;
        Feld f34 = new Feld(false, 2, Feldstatus.VERDECKT,2,3);
        felder[2][3] = f34;
        
        Feld f41 = new Feld(false, 0, Feldstatus.VERDECKT,3,0);
        felder[3][0] = f41;
        Feld f42 = new Feld(false, 0, Feldstatus.VERDECKT,3,1);
        felder[3][1] = f42;
        Feld f43 = new Feld(false, 1, Feldstatus.VERDECKT,3,2);
        felder[3][2] = f43;
        f44 = new Feld(true, 0, Feldstatus.MARKIERT,3,3);
        felder[3][3] = f44;
        
        spielfeld = new Spielfeld(felder);        
    }
    
    @Test
    public void testDeckeVerdeckteNachbarnAuf(){
        Spiel spiel = new Spiel(spielfeld);
        spiel.deckeVerdeckteNachbarnAuf(f22);
        assertEquals(Feldstatus.AUFGEDECKT, f11.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f12.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f13.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f21.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f22.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f23.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f31.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f32.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f33.getFeldstatus());
    }
    @Test
    public void testMarkiereMarkiertesFeld(){
        Spiel spiel = new Spiel(spielfeld);
        spiel.markiereOderVerdecke(f44);
        assertEquals(Feldstatus.VERDECKT, f44.getFeldstatus());
    }
    @Test
    public void testMarkiereVerdecktesFeld(){
        Spiel spiel = new Spiel(spielfeld);
        spiel.markiereOderVerdecke(f11);
        assertEquals(Feldstatus.MARKIERT, f11.getFeldstatus());
    }
}
