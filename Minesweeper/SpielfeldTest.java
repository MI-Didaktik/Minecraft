

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Test-Klasse SpielfeldTest.
 *
 * @author  Tim Busch, Beatrice Wellmann
 * @version 1
 */
public class SpielfeldTest
{
    private Spielfeld spielfeld;
    private Feld f11;
    private Feld f12;
    private Feld f13;
    private Feld f14;
    private Feld f22;
    private Feld f42;
    private Feld f43;
    private List<Feld> felderListe;
    /**
     * Konstruktor fuer die Test-Klasse SpielfeldTest
     */
    public SpielfeldTest()
    {
    }

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
        f14 = new Feld(false, 2, Feldstatus.VERDECKT,0,3);
        felder[0][3] = f14;
        
        Feld f21 = new Feld(false, 0, Feldstatus.VERDECKT,1,0);
        felder[1][0] = f21;
        f22 = new Feld(false, 1, Feldstatus.VERDECKT,1,1);
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
        f42 = new Feld(false, 0, Feldstatus.VERDECKT,3,1);
        felder[3][1] = f42;
        f43 = new Feld(false, 1, Feldstatus.VERDECKT,3,2);
        felder[3][2] = f43;
        Feld f44 = new Feld(true, 0, Feldstatus.VERDECKT,3,3);
        felder[3][3] = f44;
        
        felderListe = new ArrayList<>();
        spielfeld = new Spielfeld(felder);
        
    }

   
    @Test
    public void testDeckeFreieNachbarnAufWennHatKeineNachbarBomben(){
        spielfeld.deckeFreieNachbarnAufRekursiv(f11,felderListe);
        assertEquals(Feldstatus.AUFGEDECKT, f12.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f42.getFeldstatus());
        assertEquals(Feldstatus.AUFGEDECKT, f43.getFeldstatus());
        assertEquals(Feldstatus.VERDECKT, f13.getFeldstatus());
        assertEquals(Feldstatus.VERDECKT, f14.getFeldstatus());
        
    }
    @Test
    public void testDeckeFreieNachbarnAufWennHatNachbarBomben(){
        spielfeld.deckeFreieNachbarnAufRekursiv(f14, felderListe);
        assertEquals(Feldstatus.VERDECKT, f11.getFeldstatus());
        assertEquals(Feldstatus.VERDECKT, f12.getFeldstatus());
        assertEquals(Feldstatus.VERDECKT, f13.getFeldstatus());
        assertEquals(Feldstatus.VERDECKT, f42.getFeldstatus());
        assertEquals(Feldstatus.VERDECKT, f43.getFeldstatus());
    }
    
        @Test
    public void testgetNachbarFelder(){
        int nachbarFelderAnzahl = spielfeld.getNachbarFelder(f11).size();
        assertEquals(3,nachbarFelderAnzahl);
    }
    @Test
    public void testgetNachbarFelder8Nachbarn(){
        int nachbarFelderAnzahl = spielfeld.getNachbarFelder(f22).size();
        assertEquals(8,nachbarFelderAnzahl);
    }
}
