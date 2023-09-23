
import javafx.scene.image.Image;
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
    private Image bild; 
    
    /**
     * Konstruktor für Objekte der Klasse Feld
     */
    public Feld()
    {
        nachbarnAnzahl = 0; 
        s = Feldstatus.ZUGEDECKT; 
        bombe = false; 
        bild = new Image("Bilder/"+getBildName(s)+".png"); 
    }
    
    private String getBildName(Feldstatus fs){
        switch(fs){
            case AUFGEDECKT: return "Frei";  
            case MARKIERT: return "Markiert";  
            default: if(bombe) return "Explosion"; else return (""+nachbarnAnzahl);
        }
    }
    
    public Image getBild(){
        return bild; 
    }
    
    
}
