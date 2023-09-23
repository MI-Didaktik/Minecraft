
/**
 * Beschreiben Sie hier die Klasse Spiel.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */
public class Spiel
{
    private Spielfeld spielfeld;
    private Feld[][] felder; 
    private int zugedeckt; 
    private Spielstatus s; 
    /**
     * Konstruktor für Objekte der Klasse Spiel
     */
    public Spiel()
    {
        spielfeld = new Spielfeld();
        felder = new Feld[spielfeld.getReihen()][spielfeld.getSpalten()]; 
        zugedeckt = spielfeld.getReihen()*spielfeld.getSpalten(); 
        erzeugeFeld(); 
        s = Spielstatus.NICHTGESTARTET;  
    }

    private void erzeugeFeld(){
        //erzeuge Feld-Objekte
        //setze Random Bomben
        //suche und setze Nachbarn
    }
    
    public Spielfeld getSpielfeld(){
        return spielfeld;
    }
    
    public Feld[][] getFelder(){
        return felder;
    }
}
