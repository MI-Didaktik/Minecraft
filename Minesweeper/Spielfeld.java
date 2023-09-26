import javax.swing.*; 
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

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
    private int anzahlVerdeckt;
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
        anzahlVerdeckt = reihen*spalten; 
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
                felder[r][s] = new Feld(r,s); 
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
        for(int r=0; r<reihen; r++){
            for(int s=0; s<spalten; s++){
                int nachbarBomben = 0; 
                Feld f = felder[r][s]; 
                List<Feld> nachbarFelder = getNachbarFelder(f);
                for(Feld fNachbar : nachbarFelder){
                    if(fNachbar.istBombe()) {
                        nachbarBomben++; 
                    }
                }
                f.setNachbarBombenAnzahl(nachbarBomben); 
            }
        }
    }

    public int getAnzahlVerdeckt(){
        return anzahlVerdeckt;
    }

    public void aktualisiereVerdeckteFelder(int n){
        this.anzahlVerdeckt=this.anzahlVerdeckt-n;
    }

    public int getAnzahlBomben(){
        return anzahlBomben;
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

    public List<Feld> getNachbarFelder(Feld feld){
        int[][] richtungen = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{1,1},{-1,1}}; 
        List<Feld> nachbarFelder = new ArrayList<>();
        for(int[] richtung : richtungen){
            int nr = feld.getReihe() + richtung[0]; 
            int ns = feld.getSpalte() + richtung[1];
            if(nr>=0 && nr<reihen && ns>=0 && ns<spalten){
                Feld fNachbar = felder[nr][ns];
                nachbarFelder.add(fNachbar);
            }
        }
        return nachbarFelder;
    }

    public int getMarkierteNachbarnAnzahl(Feld feld){
        int markiertAnzahl = 0;
        List<Feld> nachbarFelder = getNachbarFelder(feld);
        for(Feld fNachbar : nachbarFelder){
            if (fNachbar.getFeldstatus() == Feldstatus.MARKIERT){
                markiertAnzahl++;
            } 
        }
        return markiertAnzahl;
    }

    public void deckeFreieNachbarnAufRekursiv(Feld start, List<Feld> neueFelder){
        if (start.getNachbarBombenAnzahl()==0){
            List<Feld> nachbarFelder = getNachbarFelder(start);
            for(Feld fNachbar : nachbarFelder){
                if (fNachbar.getFeldstatus() == Feldstatus.VERDECKT){
                    fNachbar.setFeldstatus(Feldstatus.AUFGEDECKT);
                    neueFelder.add(fNachbar);
                    deckeFreieNachbarnAufRekursiv(fNachbar, neueFelder);
                } 
            }
        }
    }

    public void deckeFreieNachbarnAuf(Feld start, List<Feld> neueFelder){
        if (start.getNachbarBombenAnzahl()==0){
            boolean neuerNachbarGefunden = true;
            while(neuerNachbarGefunden){
                neuerNachbarGefunden = false;
                for (int r = 0; r<reihen; r++){
                    for (int s = 0; s<spalten; s++){
                        Feld f = felder[r][s];
                        if (f.getFeldstatus()== Feldstatus.AUFGEDECKT && f.getNachbarBombenAnzahl()==0){
                            List<Feld> nachbarFelder = getNachbarFelder(start);
                            for(Feld fNachbar : nachbarFelder){
                                if(fNachbar.getFeldstatus()== Feldstatus.VERDECKT){
                                    fNachbar.setFeldstatus(Feldstatus.AUFGEDECKT); 
                                    neueFelder.add(fNachbar);
                                    neuerNachbarGefunden = true;
                                }
                            }
                            if (neuerNachbarGefunden){
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
