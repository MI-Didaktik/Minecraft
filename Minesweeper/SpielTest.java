
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

    @Test
    public void testDeckeVerdeckteNachbarnAuf(){
        Feld[][] felder = new Feld[4][4];
        Feld f11 = new Feld(false, 0, Feldstatus.VERDECKT,0,0);
        felder[0][0] = f11;
        Feld f12 = new Feld(false, 1, Feldstatus.VERDECKT,0,1);
        felder[0][1] = f12;
        Feld f13 = new Feld(true, 1, Feldstatus.VERDECKT,0,2);
        felder[0][2] = f13;
        Feld f14 = new Feld(false, 2, Feldstatus.VERDECKT,0,3);
        felder[0][3] = f14;

        Feld f21 = new Feld(false, 0, Feldstatus.VERDECKT,1,0);
        felder[1][0] = f21;
        Feld f22 = new Feld(false, 1, Feldstatus.VERDECKT,1,1);
        felder[1][1] = f22;
        Feld f23 = new Feld(false, 2, Feldstatus.VERDECKT,1,2);
        felder[1][2] = f23;
        Feld f24 = new Feld(true, 1, Feldstatus.VERDECKT,1,3);
        felder[1][3] = f24;

        Feld f31 = new Feld(false, 0, Feldstatus.VERDECKT,2,0);
        felder[2][0] = f31;
        Feld f32 = new Feld(false, 0, Feldstatus.VERDECKT,2,1);
        felder[2][1] = f32;
        Feld f33 = new Feld(false, 2, Feldstatus.VERDECKT,2,2);
        felder[2][2] = f33;
        Feld f34 = new Feld(false, 2, Feldstatus.VERDECKT,2,3);
        felder[2][3] = f34;

        Feld f41 = new Feld(false, 0, Feldstatus.VERDECKT,3,0);
        felder[3][0] = f41;
        Feld f42 = new Feld(false, 0, Feldstatus.VERDECKT,3,1);
        felder[3][1] = f42;
        Feld f43 = new Feld(false, 1, Feldstatus.VERDECKT,3,2);
        felder[3][2] = f43;
        Feld f44 = new Feld(true, 0, Feldstatus.MARKIERT,3,3);
        felder[3][3] = f44;

        Spielfeld spielfeld = new Spielfeld(felder);    
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
        Feld f44 = new Feld(true, 0, Feldstatus.MARKIERT,3,3);
        Feld[][] felder = new Feld[4][4];
        Spielfeld spielfeld = new Spielfeld(felder);   
        Spiel spiel = new Spiel(spielfeld);
        spiel.markiereOderVerdecke(f44);
        assertEquals(Feldstatus.VERDECKT, f44.getFeldstatus());
    }

    @Test
    public void testMarkiereVerdecktesFeld(){
        Feld f11 = new Feld(false, 0, Feldstatus.VERDECKT,0,0);
        Feld[][] felder = new Feld[4][4];
        Spielfeld spielfeld = new Spielfeld(felder); 
        Spiel spiel = new Spiel(spielfeld);
        spiel.markiereOderVerdecke(f11);
        assertEquals(Feldstatus.MARKIERT, f11.getFeldstatus());
    }
}
