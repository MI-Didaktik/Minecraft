
/**
 * Beschreiben Sie hier die Klasse Spiel.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */
public class Spiel
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    int[][] feld; 
    private int zugedeckt; 
    private Spielstatus s; 
    /**
     * Konstruktor für Objekte der Klasse Spiel
     */
    public Spiel()
    {
        feld = new int[10][10]; 
        zugedeckt = 100; 
        erzeugeFeld(); 
        s = Spielstatus.NICHTGESTARTET;  
    }

    public void erzeugeFeld(){
        
    }
}
