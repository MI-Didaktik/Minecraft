
/**
 * Beschreiben Sie hier die Klasse Feld.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
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
        s = Feldstatus.Zugedeckt; 
        bombe = false; 
    }
}
