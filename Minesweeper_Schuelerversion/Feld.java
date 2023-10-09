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

    public Image getBild(int breite, int hoehe){
        return new Image("bilder/" + getBildName() + ".png",breite,hoehe,false,false);
    }

  
    private String getBildName(){
        return "";
    }

}
