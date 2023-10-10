import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Klasse Spielfeld dient der Verwaltung der Felder des Spielfeldes. 
 * Ein Spielfeld besteht aus mehreren Objekten der Klasse Feld.
 * 
 * @author Tim Busch, Beatrice Wellmann
 * @version 1
 */
public class Spielfeld {
    private Feld[][] felder;
    private int reihen;
    private int spalten;
    private int anzahlVerdeckt;
    private int anzahlBomben;

    /**
     * Konstruktor für Objekte der Klasse Spielfeld
     * 
     * @param anzahlBomben die Anzahl der platzierten Bomben
     * @param reihen       die Anzahl der Reihen des erzeugten Spielfelds
     * @param spalten      die Anzahl der Reihen des erzeugten Spielfelds
     */
    public Spielfeld(int anzahlBomben, int reihen, int spalten) {
        felder = new Feld[reihen][spalten];
        this.reihen = reihen;
        this.spalten = spalten;
        this.anzahlBomben = anzahlBomben;
        anzahlVerdeckt = reihen * spalten;
        erzeugeSpielFeld();
    }
    
    /**
     * Konstruktor zu Testzwecken
     * NICHT ANPASSEN
     */
    public Spielfeld(Feld[][]felder){
        this.felder = felder;
        this.reihen =felder.length;
        this.spalten = felder[0].length;
    }

    /**
     * Deckt alle benachbarten Felder eines Feldes auf, bei welchen nachbarBombenAnzahl den Wert 0 hat. 
     * Fuer die dabei aufgedeckten Felder wird das wiederholt, so lange bis keine solchen benachbarten Felder mehr existieren. 
     * Die Methode nutzt hierzu Rekursion.
     * 
     * @param start      das Feld welches aufgedeckt wurde
     * @param neueFelder eine Liste in der die von der Methode neu aufgedeckten Felder gespeichert werden
     */
    public void deckeFreieNachbarnAufRekursiv(Feld start, List<Feld> neueFelder) {
        if (start.getNachbarBombenAnzahl() == 0) {
            List<Feld> nachbarFelder = getNachbarFelder(start);
            for (Feld fNachbar : nachbarFelder) {
                if (fNachbar.getFeldstatus() == Feldstatus.VERDECKT) {
                    fNachbar.setFeldstatus(Feldstatus.AUFGEDECKT);
                    neueFelder.add(fNachbar);
                    deckeFreieNachbarnAufRekursiv(fNachbar, neueFelder);
                }
            }
        }
    }

    /**
     * Alternative zu deckeFreieNachbarnAufRekursiv
     * Deckt alle benachbarten Felder eines Feldes auf, bei welchen nachbarBombenAnzahl den Wert 0 hat. 
     * Fuer die dabei aufgedeckten Felder wird das wiederholt, so lange bis keine solchen benachbarten Felder mehr existieren. 
     * Die Methode nutzt hierzu keine Rekursion.
     * 
     * @param start das Feld welches aufgedeckt wurde
     * @param neueFelder eine Liste, in der die von der Methode neu aufgedeckten Felder gespeichert werden
     */
    public void deckeFreieNachbarnAuf(Feld start, List<Feld> neueFelder) {
        if (start.getNachbarBombenAnzahl() == 0) {
            boolean neuerNachbarGefunden = true;
            while (neuerNachbarGefunden) {
                neuerNachbarGefunden = false;
                for (int r = 0; r < reihen; r++) {
                    for (int s = 0; s < spalten; s++) {
                        Feld f = felder[r][s];
                        if (f.getFeldstatus() == Feldstatus.AUFGEDECKT && f.getNachbarBombenAnzahl() == 0) {
                            List<Feld> nachbarFelder = getNachbarFelder(start);
                            for (Feld fNachbar : nachbarFelder) {
                                if (fNachbar.getFeldstatus() == Feldstatus.VERDECKT) {
                                    fNachbar.setFeldstatus(Feldstatus.AUFGEDECKT);
                                    neueFelder.add(fNachbar);
                                    neuerNachbarGefunden = true;
                                }
                            }
                            if (neuerNachbarGefunden) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Liefert für ein bestimmtes Feld alle benachbarten Feld-Objekte.
     * 
     * @param feld das Feld für welches die Nachbarn geliefert werden sollen
     * @return Liste mit den benachbarten Feldern
     */
    public List<Feld> getNachbarFelder(Feld feld) {
        int[][] richtungen = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 }, { -1, 1 } };
        List<Feld> nachbarFelder = new ArrayList<>();
        for (int[] richtung : richtungen) {
            int nr = feld.getReihe() + richtung[0];
            int ns = feld.getSpalte() + richtung[1];
            if (nr >= 0 && nr < reihen && ns >= 0 && ns < spalten) {
                Feld fNachbar = felder[nr][ns];
                nachbarFelder.add(fNachbar);
            }
        }
        return nachbarFelder;
    }

    /**
     * Liefert die Anzahl der markierten Nachbarn eines Feldes.
     * 
     * @param feld das Feld für welches die Anzahl der markierten Nachbarn geliefert werden soll
     * @return Anzahl der markierten Nachbarfelder
     */ 
    public int getMarkierteNachbarnAnzahl(Feld feld) {
        int markiertAnzahl = 0;
        List<Feld> nachbarFelder = getNachbarFelder(feld);
        for (Feld fNachbar : nachbarFelder) {
            if (fNachbar.getFeldstatus() == Feldstatus.MARKIERT) {
                markiertAnzahl++;
            }
        }
        return markiertAnzahl;
    }

    /**
     * Liefert das Feld Objekt an einer bestimmten Stelle.
     * 
     * @param r die Reihe in der das Feld liegt
     * @param s die Spalte in der das Feld liegt
     * @return feld
     */
    public Feld getFeld(int r, int s) {
        Feld feld = null;
        if (r >= 0 && r < reihen && s >= 0 && s < spalten) {
            feld = felder[r][s];
        }
        return feld;
    }

    /**
     * Liefert die Felder des Spielfelds.
     * 
     * @return die Felder des Spielfelds
     */
    public Feld[][] getFelder() {
        return felder;
    }

    /**
     * Liefert die Anzahl der Reihen des Spielfelds.
     * 
     * @return Anzahl der Reihen
     */
    public int getReihen() {
        return reihen;
    }

    /**
     * Liefert die Anzahl der Spalten des Spielfelds.
     * 
     * @return Anzahl der Spalten
     */
    public int getSpalten() {
        return spalten;
    }

    /**
     * Liefert die Anzahl der noch nicht aufgedeckten Felder auf dem Spielfeld.
     * 
     * @return Anzahl der verdeckten Felder
     */
    public int getAnzahlVerdeckt() {
        return anzahlVerdeckt;
    }

    /**
     * Liefert die Anzahl der Bomben auf dem Spielfeld.
     * 
     * @return Anzahl der Bomben
     */
    public int getAnzahlBomben() {
        return anzahlBomben;
    }

    /**
     * Aktualisiert die Anzahl der Verdeckten Felder.
     * 
     * @param n die Anzahl der neu aufgedeckten Felder
     */
    public void aktualisiereVerdeckteFelder(int n) {
        this.anzahlVerdeckt = this.anzahlVerdeckt - n;
    }

    /**
     * Erzeugt ein Spielfeld. Hierfür werden die Felder initialisiert, die Bomben
     * erzeugt und in den Feldern wird der Wert nachbarBombenAnzahl gesetzt.
     */
    private void erzeugeSpielFeld() {
        erzeugeFelder();
        erzeugeBomben();
        zaehleNachbarnProFeld();
    }

    /**
     * Fuellt das Array felder mit Objekten der Klasse Feld.
     */
    private void erzeugeFelder() {
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                felder[r][s] = new Feld(r, s);
            }
        }
    }

    /**
     * Erzeugt an zufaelligen Positionen des Spielfelds Bomben.
     */
    private void erzeugeBomben() {
        Random random = new Random();
        for (int i = 0; i < anzahlBomben; i++) {
            int reihe = random.nextInt(reihen);
            int spalte = random.nextInt(spalten);
            Feld f = felder[reihe][spalte];
            if (f.istBombe()) {
                i--;
            } else {
                f.setBombe();
            }
        }
    }

    /**
     * Zaehlt fuer jedes Feld die Anzahl der benachbarten Feldern, welche eine Bombe haben.
     */
    private void zaehleNachbarnProFeld() {
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                int nachbarBomben = 0;
                Feld f = felder[r][s];
                List<Feld> nachbarFelder = getNachbarFelder(f);
                for (Feld fNachbar : nachbarFelder) {
                    if (fNachbar.istBombe()) {
                        nachbarBomben++;
                    }
                }
                f.setNachbarBombenAnzahl(nachbarBomben);
            }
        }
    }
}
