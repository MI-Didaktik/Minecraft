
/**
 * Beschreiben Sie hier die Klasse Feld.
 * 
 * @author (Tim Busch, Beatrice Wellmann) 
 * @version (1)
 */
public class Feld
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private boolean bombe;
    private int nachbarnAnzahl; 
    private Feldstatus s; 
    
    /**
     * Konstruktor für Objekte der Klasse Feld
     */
    public Feld()
    {
        nachbarnAnzahl = 0; 
        s = Feldstatus.ZUGEDECKT; 
        bombe = false; 
    }
}
