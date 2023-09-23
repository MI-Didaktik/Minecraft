
import java.util.Random; 
/**
 * Beschreiben Sie hier die Klasse Spiel.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */
public class Spiel
{
    private Spielfeld spielfeld;
    private Feld[][] felder; 
    private int zugedeckt; 
    private Spielstatus s; 
    /**
     * Konstruktor für Objekte der Klasse Spiel
     */
    public Spiel()
    {
        spielfeld = new Spielfeld();
        felder = new Feld[spielfeld.getReihen()][spielfeld.getSpalten()]; 
        zugedeckt = spielfeld.getReihen()*spielfeld.getSpalten(); 
        erzeugeFeld(); 
        s = Spielstatus.NICHTGESTARTET;  
    }

    /**
     *  erzeuge Feld-Objekte
     *  setze Random bombem
     *  suche und setze nachbarn
     */
    private void erzeugeFeld(){
        initialisiereFelder();
        erzeugeBomben(); 
        zaehleNachbarn();
    }

    private void initialisiereFelder(){
        for(int i=0; i<10; i++){
            for(int j=0; i<10; j++){
                felder[i][j] = new Feld(); 
            }
        }
    }

    private void erzeugeBomben(){
        Random random = new Random(); 
        for(int i=0; i<10; i++){
            int zeile = random.nextInt(10)+1; 
            int spalte = random.nextInt(10)+1; 
            Feld f = felder[zeile][spalte]; 
            if(f.getBombe()){
                i--; 
            } else{
                f.setBombe(); 
            }
        }
    }
    
    private void zaehleNachbarn(){
        int gezaehlt; 
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                Feld f = felder[i][j]; 
                f.setNachbarnAnzahl(gezaehlt); 
            }
        }
    }

    public Spielfeld getSpielfeld(){
        return spielfeld;
    }

    public Feld[][] getFelder(){
        return felder;
    }
}
