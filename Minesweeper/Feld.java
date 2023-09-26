
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
    private int nachbarBombenAnzahl; 
    private Feldstatus feldstatus; 
    private int reihe;
    private int spalte;

    /**
     * Konstruktor für Objekte der Klasse Feld
     */
    public Feld(int reihe, int spalte)
    {
        this.reihe = reihe;
        this.spalte = spalte;
        nachbarBombenAnzahl = 0; 
        feldstatus = Feldstatus.VERDECKT; 
        istBombe = false; 
    }

    private String getBildName(){
        switch(feldstatus){
            case VERDECKT: return "Frei";  
            case MARKIERT: return "Markiert";  
            default: 
                if(istBombe) {
                    return "Explosion";
                } 
                else {
                    return (""+nachbarBombenAnzahl);
                }
        }
    }

    public Image getBild(){
        return new Image("bilder/"+getBildName()+".png"); 
    }

    public boolean istBombe(){
        return this.istBombe;
    }

    public void setBombe(){
        this.istBombe = true; 
    }

    public void setNachbarBombenAnzahl(int anzahl){
        nachbarBombenAnzahl = anzahl; 
    }
    
    public int getNachbarBombenAnzahl(){
        return nachbarBombenAnzahl;
    }
    
    public void setFeldstatus(Feldstatus s){
        this.feldstatus = s;
    }
    
    public Feldstatus getFeldstatus(){
        return this.feldstatus;
    }
    
    public int getReihe(){
        return reihe;
    }
    public int getSpalte(){
        return spalte;
    }

}
