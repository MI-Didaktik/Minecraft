
/**
 * Die Aufzählungsklasse Schwierigkeitsstufe beeinhaltet für jede der Schwierigkeitsstufen die Anzahl Reihen, Spalten und Bomben 
 * sowie die größtmögliche Punktzahl, die für diese Stufe erreicht werden kann.
 * 
 * @author Tim Busch, Beatrice Wellmann
 * @version 1
 */
public enum Schwierigkeitsstufe
{
    LEICHT(5,5,5,120), MITTEL(7,7,13,300), SCHWER(10,10,20,420);
    
    private final int reihen;
    private final int spalten;
    private final int bomben;
    private final long hoechstpunkte;
    
    /**
     * Konstruktor für Objekte der Aufzählungsklasse Schwierigkeitsstufe
     * @param reihen die Anzahl Reihen des Spielfelds
     * @param spalten die Anzahl Spalten des Spielfelds
     * @param bomben die Anzahl Bomben des Spielfelds
     * @param hoechstpunkte die höchste zu erreichende Punktzahl
     */
    Schwierigkeitsstufe(int reihen, int spalten, int bomben, long hoechstpunkte){
        this.reihen = reihen;
        this.spalten = spalten;
        this.bomben = bomben;
        this.hoechstpunkte = hoechstpunkte;
        }
      
    /**
     * Liefert die gesetzte Anzahl Reihen für die Schwierigkeitsstufe
     * @return reihen
     */
    public int getReihen(){
        return reihen;
    }
    
    /**
     * Liefert die gesetzte Anzahl Spalten für die Schwierigkeitsstufe
     * @return spalten
     */
    public int getSpalten(){
        return spalten;
    }
    /**
     * Liefert die gesetzte Anzahl Bomben für die Schwierigkeitsstufe
     * @return bomben
     */
    public int getBomben(){
        return bomben;
    }
    
    /**
     * Liefert die maximal erreichbaren Punkte für die Schwierigkeitsstufe
     * @return hoechstpunkte
     */
    public long getHoechstpunkte(){
        return hoechstpunkte;
    }
}
