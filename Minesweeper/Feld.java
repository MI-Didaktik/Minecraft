
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
        nachbarnAnzahl = 0; 
        feldstatus = Feldstatus.ZUGEDECKT; 
        istBombe = false; 
    }

    private String getBildName(){
        switch(feldstatus){
            case ZUGEDECKT: return "0";  
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
        return new Image("bilder/"+getBildName()+".png"); 
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
    
    public int getNachbarnAnzahl(){
        return nachbarnAnzahl;
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
