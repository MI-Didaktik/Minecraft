import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Spiel steuert die Interaktionen des Spielers und regelt den aktuellen Spielstatus.
 * 
 * @author Tim Busch, Beatrice Wellmann
 * @version 1
 */
public class Spiel {
    private Spielfeld spielfeld;
    private Spielstatus spielstatus;
    private Schwierigkeitsstufe schwierigkeitsstufe;
    private int markierteFelder;
    private long highscore;

    /**
     * Konstruktor für Objekte der Klasse Spiel
     * 
     * @param schwierigkeitsstufe die Schwierigkeitsstufe, welche Anzahl Reihen, Spalten, Bomben und Höchstpunkte vorgibt
     */
    public Spiel(Schwierigkeitsstufe schwierigkeitsstufe) {
        this.schwierigkeitsstufe = schwierigkeitsstufe;
        spielfeld = new Spielfeld(schwierigkeitsstufe.getBomben(), schwierigkeitsstufe.getReihen(),
            schwierigkeitsstufe.getSpalten());
        spielstatus = Spielstatus.NICHTGESTARTET;
        markierteFelder = 0;
        highscore = 0;
    }

    /**
     * Konstruktor zu Testzwecken
     * NICHT ANPASSEN
     * @param spielfeld das Spielfeld
     */
    public Spiel(Spielfeld spielfeld){
        this.spielfeld = spielfeld;
    }

    /**
     * Deckt alle verdeckten Nachbarn eines bestimmten Feldes auf.
     * 
     * @param feld Feld von welchem die Nachbarn aufgedeckt werden sollen
     * @return Liste der dabei neu aufgedeckten Felder
     */
    public List<Feld> deckeVerdeckteNachbarnAuf(Feld feld) {
        List<Feld> neueFelder = new ArrayList<>();
        for (Feld fNachbar : spielfeld.getNachbarFelder(feld)) {
            if (fNachbar.getFeldstatus() == Feldstatus.VERDECKT) {
                neueFelder.addAll(deckeAuf(fNachbar));
            }
        }
        return neueFelder;
    }

    /**
     * Deckt ein Feld auf. War das Feld markiert, so wird der Zähler markierteFelder um eins reduziert. 
     * Falls dieses Feld keine Bombe hat wird anschließend die Methode deckeFreieNachbarnAufRekursiv() der Klasse Spielfeld aufgerufen. 
     * Hat das Feld jedoch eine Bombe, so ist das Spiel verloren und der Spielstatus wird entsprechend auf VERLOREN gesetzt.
     * 
     * @param feld Feld welches aufgedeckt werden soll
     * @return Liste der neu aufgedeckten Felder
     */
    public List<Feld> deckeAuf(Feld feld) {
        List<Feld> neueFelder = new ArrayList<>();
        neueFelder.add(feld);
        if (feld.getFeldstatus() == Feldstatus.MARKIERT) {
            markierteFelder--;
        }
        feld.setFeldstatus(Feldstatus.AUFGEDECKT);
        if (feld.istBombe()) {
            spielstatus = Spielstatus.VERLOREN;
        } else {
            if (feld.getNachbarBombenAnzahl() == 0) {
                // Alternativ: spielfeld.deckeFreieNachbarnAuf(feld, neueFelder);
                spielfeld.deckeFreieNachbarnAufRekursiv(feld, neueFelder);
            }
            spielfeld.aktualisiereVerdeckteFelder(neueFelder.size());
            if (spielfeld.getAnzahlVerdeckt() == spielfeld.getAnzahlBomben()) {
                spielstatus = Spielstatus.GEWONNEN;
            }
        }
        return neueFelder;
    }

    /**
     * Setzt den Feldstatus auf MARKIERT, falls der Status vorher VERDECKT war.
     * Anderenfalls wird der Status auf VERDECKT gesetzt, falls der Status vorher MARKIERT war. Die Variable markierteFelder wird aktualisiert.
     * 
     * @param feld Feld von welchem der Status verändert werden soll
     */
    public void markiereOderVerdecke(Feld feld) {
        if (feld.getFeldstatus() == Feldstatus.VERDECKT) {
            feld.setFeldstatus(Feldstatus.MARKIERT);
            markierteFelder++;
        } else if (feld.getFeldstatus() == Feldstatus.MARKIERT) {
            feld.setFeldstatus(Feldstatus.VERDECKT);
            markierteFelder--;
        }
    }

    /**
     * Liefert das Spielfeld.
     * 
     * @return spielfeld
     */
    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    /**
     * Liefert den Spielstatus.
     * 
     * @return spielstatus
     */
    public Spielstatus getSpielstatus() {
        return spielstatus;
    }

    /**
     * Setzt den gewünschten Spielstatus.
     * 
     * @param s der gewünschte Spielstatus
     */
    public void setSpielstatus(Spielstatus s) {
        this.spielstatus = s;
    }

    /**
     * Liefert die gesetzte Schwierigkeitsstufe für dieses Spiel.
     * 
     * @return schwierigkeitsstufe
     */
    public Schwierigkeitsstufe getSchwierigkeitsstufe() {
        return schwierigkeitsstufe;
    }

    /**
     * Liefert die aktuelle Anzahl markierter Felder.
     * 
     * @return markierteFelder
     */
    public int getMarkierteFelder() {
        return markierteFelder;
    }

    /**
     * Liefert den aktuellen Highscore.
     * 
     * @return highscore
     */
    public long getHighscore(){
        return highscore;
    }

    /**
     * Setzt den Highscore auf einen neuen Wert.
     * 
     * @param highscore der neue Highscore
     */
    public void setHighscore(long highscore){
        this.highscore = highscore;
    }
}
