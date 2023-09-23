import javax.swing.*; 
/**
 * Beschreiben Sie hier die Klasse Spielfeld.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */ 
public class Spielfeld
{
    private int reihen;
    private int spalten; 
    private JLabel bomben; 
    private JLabel zeit; 
    private JLabel highscore; 
    
    /**
     * Konstruktor für Objekte der Klasse Spielfeld
     */
    public Spielfeld()
    {
        reihen = 10; 
        spalten = 10; 
    }
    
    public int getReihen(){
        return reihen;
    }
    
    public int getSpalten(){
        return spalten;
    }

    
}
