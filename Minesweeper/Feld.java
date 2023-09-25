
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
    private boolean istBombe;
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
        istBombe = false; 
        bild = new Image("bilder/"+getBildName()+".png"); 
    }

    private String getBildName(){
        switch(s){
            case AUFGEDECKT: return "Frei";  
            case MARKIERT: return "Markiert";  
            default: 
                if(istBombe) {
                    return "Explosion";
                } 
                else {
                    return (""+nachbarnAnzahl);
                }
        }
    }

    public Image getBild(){
        return bild; 
    }

    public boolean istBombe(){
        return this.istBombe;
    }

    public void setBombe(){
        this.istBombe = true; 
    }

    public void setNachbarnAnzahl(int anzahl){
        nachbarnAnzahl = anzahl; 
    }

}
