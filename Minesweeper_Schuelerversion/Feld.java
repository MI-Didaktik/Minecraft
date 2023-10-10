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
    private int reihe;
    private int spalte;

    public Feld(){
        //Konstruktor
    }

    /*
     * Konstruktor zu Testzwecken
     * NICHT ANPASSEN
     */
    public Feld(boolean istBombe, int nachbarBombenAnzahl, Feldstatus feldstatus, int reihe, int spalte){
        this.istBombe = istBombe;
        this.nachbarBombenAnzahl = nachbarBombenAnzahl;
        this.feldstatus = feldstatus;
        this.reihe = reihe;
        this.spalte = spalte;
    }

    public Image getBild(int breite, int hoehe){
        return new Image("bilder/" + getBildName() + ".png",breite,hoehe,false,false);
    }

    public Feldstatus getFeldstatus(){
        return feldstatus;
    }

    protected String getBildName(){
        return "Bombe";
    }

}
