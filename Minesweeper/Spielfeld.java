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

        int rowCount = felder.length; 
        int colCount = felder[0].length; 
        int[][] richtungen = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{1,1},{-1,1}}; 

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                int gezaehlt = 0; 
                Feld f = felder[i][j]; 

                for(int[] richtung : richtungen){
                    int newRow = i + richtung[0]; 
                    int newCol = j + richtung[1]; 
                    Feld fNachbar = felder[newRow][newCol];
                    if(newRow>=0 && newRow<rowCount && newCol>=0 && newCol<colCount){
                        if(fNachbar.getBombe()) gezaehlt++; 
                    }
                    f.setNachbarnAnzahl(gezaehlt); 
                }
            }
        }
    }

    public Feld[][] getFelder(){
        return felder;
    }

}
