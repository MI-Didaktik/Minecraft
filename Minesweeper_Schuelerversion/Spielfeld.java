import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 
 * @version 
 */
public class Spielfeld {
    private Feld[][] felder;
    private int reihen;
    private int spalten;
    private int anzahlBomben;

    public Spielfeld(){
        erzeugeSpielFeld();
    }

    /**
     * Deckt alle benachbarten Felder eines Feldes auf, bei welchen nachbarBombenAnzahl den Wert 0 hat. 
     */
    public void deckeFreieNachbarnAuf(Feld feld) {

    }

    /**
     * Liefert für ein Feld alle benachbarten Feld-Objekte.
     */
    public List<Feld> getNachbarFelder(Feld feld) {

        return null;
    }
    
    /**
     * Liefert das Feld an einer durch reihe und spalte bestimmten position.
     */
    public Feld getFeld(int reihe, int spalte) {

        return null;
    }

    private void erzeugeSpielFeld() {
        erzeugeFelder();
        erzeugeBomben();
        zaehleNachbarn();
    }

    /**
     * Fuellt das Array felder mit Objekten der Klasse Feld.
     */
    private void erzeugeFelder() {

    }

    /**
     * Erzeugt an zufaelligen Positionen des Spielfelds Bomben.
     */
    private void erzeugeBomben() {

    }

    /**
     * Zaehlt für jedes Feld die Anzahl der benachbarten Feldern, welche eine Bombe haben.
     */
    private void zaehleNachbarn() {

    }
}
