import java.util.List;
import java.util.ArrayList;

/**
 * Die Klasse Spiel steuert die Interaktionen des Spielers und regelt
 * den aktuellen Spielstatus. 
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */
public class Spiel
{
    private Spielfeld spielfeld;
    private Spielstatus spielstatus; 
    private Schwierigkeitsstufe schwierigkeitsstufe;
    private Controller controller;
    private int markierteFelder;
    /**
     * Konstruktor für Objekte der Klasse Spiel
     * @param controller Controller zur Steuerung der GUI
     * @param schwierigkeitsstufe die Schwierigkeitsstufe, welche Anzahl Reihen, Spalten, Bomben und Höchstpunkte vorgibt
     */
    public Spiel(Controller controller, Schwierigkeitsstufe schwierigkeitsstufe)
    {
        this.controller = controller;
        this.schwierigkeitsstufe = schwierigkeitsstufe;
        spielfeld = new Spielfeld(schwierigkeitsstufe.getBomben(),schwierigkeitsstufe.getReihen(),schwierigkeitsstufe.getSpalten());
        spielstatus = Spielstatus.NICHTGESTARTET;  
        markierteFelder = 0;
    }

    /**
     * Deckt alle verdeckten Nachbarn eines bestimmten Feldes auf.
     * @param feld Feld von welchem die Nachbarn aufgedeckt werden sollen
     * @return Liste der dabei neu aufgedeckten Felder
     */
    public List<Feld> deckeVerdeckteNachbarnAuf(Feld feld){
        List<Feld> neueFelder = new ArrayList<>();
        for (Feld fNachbar : spielfeld.getNachbarFelder(feld)){
            if (fNachbar.getFeldstatus().equals(Feldstatus.VERDECKT)){
                neueFelder.addAll(deckeAuf(fNachbar));
            }
        }
        return neueFelder;
    }

    /**
     * Deckt ein Feld auf. War das Feld markiert, so wird der Zähler markierteFelder um eins reduziert.
     * Falls dieses Feld keine Bombe hat wird anschließend die 
     * Methode deckeFreieNachbarnAufRekursiv() der Klasse Spielfeld aufgerufen.
     * Hat das Feld jedoch eine Bombe, so ist das Spiel verloren und der Spielstatus
     * wird entsprechend auf VERLOREN gesetzt. 
     * @param feld Feld welches aufgedeckt werden soll
     * @return Liste der neu aufgedeckten Felder
     */
    public List<Feld> deckeAuf(Feld feld){
        List<Feld> neueFelder = new ArrayList<>();
        neueFelder.add(feld);
        if (feld.getFeldstatus() == Feldstatus.MARKIERT){
            markierteFelder--;
        }
        feld.setFeldstatus(Feldstatus.AUFGEDECKT);
        if (feld.istBombe()){
            spielstatus = Spielstatus.VERLOREN;
            controller.aktualisiereSpielstatus();
        }
        else {
            if (feld.getNachbarBombenAnzahl() == 0){
                //Alternativ: spielfeld.deckeFreieNachbarnAuf(feld, neueFelder);
                spielfeld.deckeFreieNachbarnAufRekursiv(feld, neueFelder);
            }
            spielfeld.aktualisiereVerdeckteFelder(neueFelder.size());
            if (spielfeld.getAnzahlVerdeckt()==spielfeld.getAnzahlBomben()){
                spielstatus = Spielstatus.GEWONNEN;
                controller.aktualisiereSpielstatus();
            }
        }
        return neueFelder;
        //TODO: stoppe timer, highscore....
    }

    /**
     * Setzt den Feldstatus auf MARKIERT, falls der Status vorher VERDECKT war.
     * Anderenfalls wird der Status auf VERDECKT gesetzt, falls der Status vorher MARKIERT war.
     * Die Variable markierteFelder wird aktualisiert.
     * @param feld Feld von welchem der Status verändert werden soll
     */
    public void markiere(Feld feld){
        if (feld.getFeldstatus().equals(Feldstatus.VERDECKT)){
            feld.setFeldstatus(Feldstatus.MARKIERT);
            markierteFelder++;
        }
        else if (feld.getFeldstatus().equals(Feldstatus.MARKIERT)){
            feld.setFeldstatus(Feldstatus.VERDECKT);
            markierteFelder--;
        }
    }

    /**
     * Liefert den Spielstatus.
     * @return spielstatus
     */
    public Spielstatus getSpielstatus(){
        return spielstatus;
    }

    /**
     * Setzt den gewünschten Spielstatus.
     * @param s der gewünschte Spielstatus
     */
    public void setSpielstatus(Spielstatus s){
        this.spielstatus = s;
    }

    /**
     * Liefert das Spielfeld. 
     * @return spielfeld
     */
    public Spielfeld getSpielfeld(){
        return spielfeld;
    }

    /**
     * Liefert die aktuelle Anzahl markierter Felder. 
     * @return markierteFelder
     */
    public int getMarkierteFelder(){
        return markierteFelder;
    }

    /**
     * Liefert die gesetzte Schwierigkeitsstufe für dieses Spiel.
     * @return schwierigkeitsstufe
     */
    public Schwierigkeitsstufe getSchwierigkeitsstufe(){
        return schwierigkeitsstufe;
    }
}
