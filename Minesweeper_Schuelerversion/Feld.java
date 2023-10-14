import javafx.scene.image.Image;
/**
 * @author 
 * @version 
 */
public class Feld
{
    private boolean istBombe;
    private int nachbarBombenAnzahl; 
    private Feldstatus feldstatus; 

    public Feld(){
        //Konstruktor
    }

    /*
     * Konstruktor zu Testzwecken
     * NICHT ANPASSEN
     */
    public Feld(boolean istBombe, int nachbarBombenAnzahl, Feldstatus feldstatus){
        this.istBombe = istBombe;
        this.nachbarBombenAnzahl = nachbarBombenAnzahl;
        this.feldstatus = feldstatus;
    }

    public Image getBild(int breite, int hoehe){
        return new Image("bilder/" + getBildName() + ".png",breite,hoehe,false,false);
    }

    public Feldstatus getFeldstatus(){
        return feldstatus;
    }
    
    protected String getBildName(){
        //passe diese Methode an
        return "Bombe";
    }

}
