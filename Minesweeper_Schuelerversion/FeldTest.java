
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse FeldTest.
 *
 * @author  Tim Busch, Beatrice Wellmann
 * @version 1
 */
public class FeldTest
{

    @Test
    public void testGetBildNameForMarkiert() {
        Feld feld = new Feld(true, 1, Feldstatus.MARKIERT);
        assertEquals("Markiert", feld.getBildName());
    }

    @Test
    public void testGetBildNameForVerdeckt() {
        Feld feld = new Feld(true, 2, Feldstatus.VERDECKT);
        assertEquals("Verdeckt", feld.getBildName());
    }

    @Test
    public void testGetBildNameForNachbarn() {
        Feld feld = new Feld(false, 4, Feldstatus.AUFGEDECKT);
        assertEquals("4", feld.getBildName());
    }

    @Test
    public void testGetBildNameForKeineNachbarn() {
        Feld feld = new Feld(false, 0, Feldstatus.AUFGEDECKT);
        assertEquals("0", feld.getBildName());
    }    
}
