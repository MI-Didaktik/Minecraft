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
            int reihe = random.nextInt(reihen); 
            int spalte = random.nextInt(spalten); 
            Feld f = felder[reihe][spalte]; 
            if(f.istBombe()){
                i--; 
            } else{
                f.setBombe(); 
            }
        }
    }

    private void zaehleNachbarn(){

        int anzahlReihen = felder.length; 
        int anzahlSpalten = felder[0].length; 
        int[][] richtungen = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{1,1},{-1,1}}; 

        for(int r=0; r<reihen; r++){
            for(int s=0; s<spalten; s++){
                int nachbarBomben = 0; 
                Feld f = felder[r][s]; 

                for(int[] richtung : richtungen){
                    int nr = r + richtung[0]; 
                    int ns = s + richtung[1]; 
                    if(nr>=0 && nr<anzahlReihen && ns>=0 && ns<anzahlSpalten){
                        Feld fNachbar = felder[nr][ns];
                        if(fNachbar.istBombe()) {
                            nachbarBomben++; 
                        }
                    }
                    f.setNachbarnAnzahl(nachbarBomben); 
                }
            }
        }
    }

    public Feld[][] getFelder(){
        return felder;
    }

    public Feld getFeld(int r, int s){
        Feld feld = null;
        if (r>=0 && r<reihen && s>=0 && s<spalten){
            feld = felder[r][s];
        }
        return feld;
    }
}
