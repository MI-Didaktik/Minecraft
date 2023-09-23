import javax.swing.*; 
import java.util.Random;

/**
 * Beschreiben Sie hier die Klasse Spielfeld.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */ 
public class Spielfeld
{
    private Feld[][] felder; 
    private int reihen;
    private int spalten; 
    private int zugedeckt;
    private int anzahlBomben;

    /**
     * Konstruktor für Objekte der Klasse Spielfeld
     */
    public Spielfeld(int anzahlBomben, int reihen, int spalten)
    {
        felder = new Feld[reihen][spalten]; 
        this.reihen = reihen; 
        this.spalten = spalten; 
        this.anzahlBomben = anzahlBomben;
        zugedeckt = reihen*spalten; 
        erzeugeFeld(); 
    }

    public int getReihen(){
        return reihen;
    }

    public int getSpalten(){
        return spalten;
    }

    /**
     * TODO: nach Spielfeld verschieben!!
     *  erzeuge Feld-Objekte
     *  setze Random bombem
     *  suche und setze nachbarn
     */
    public void erzeugeFeld(){
        initialisiereFelder();
        erzeugeBomben(); 
        zaehleNachbarn();
    }

    private void initialisiereFelder(){
        for(int r=0; r<reihen; r++){
            for(int s=0; s<spalten; s++){
                felder[r][s] = new Feld(); 
            }
        }
    }

    private void erzeugeBomben(){
        Random random = new Random(); 
        for(int i=0; i<anzahlBomben; i++){
            int zeile = random.nextInt(10); 
            int spalte = random.nextInt(10); 
            Feld f = felder[zeile][spalte]; 
            if(f.getBombe()){
                i--; 
            } else{
                f.setBombe(); 
            }
        }
    }

    private void zaehleNachbarn(){
        // int gezaehlt; 
        // for(int i=0; i<10; i++){
        // for(int j=0; j<10; j++){
        // Feld f = felder[i][j]; 
        // f.setNachbarnAnzahl(gezaehlt); 
        // }
        // }
    }

    public Feld[][] getFelder(){
        return felder;
    }

}
