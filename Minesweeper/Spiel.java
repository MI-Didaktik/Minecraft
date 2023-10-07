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
    private Controller controller;
    /**
     * Konstruktor für Objekte der Klasse Spiel
     * @param controller Controller zur Steuerung der GUI
     */
    public Spiel(Controller controller)
    {
        this.controller = controller;
        //TODO: übergabe der reihen/spalten/bomben anzahl
        //ggf. Schwierigkeitslevel statt volle Kontrolle über spalten/zeilen/bomben-Anzahl
        spielfeld = new Spielfeld(20,10,10);
        spielstatus = Spielstatus.NICHTGESTARTET;  
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
     * Deckt ein Feld auf. Falls dieses Feld keine Bombe hat wird anschließend die 
     * Methode deckeFreieNachbarnAufRekursiv() der Klasse Spielfeld aufgerufen.
     * Hat das Feld jedoch eine Bombe, so ist das Spiel verloren und der Spielstatus
     * wird entsprechend auf VERLOREN gesetzt. 
     * @param feld Feld welches aufgedeckt werden soll
     * @return Liste der neu aufgedeckten Felder
     */
    public List<Feld> deckeAuf(Feld feld){
        List<Feld> neueFelder = new ArrayList<>();
        neueFelder.add(feld);
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
     * @param feld Feld von welchem der Status verändert werden soll
     */
    public void markiere(Feld feld){
        if (feld.getFeldstatus().equals(Feldstatus.VERDECKT)){
            feld.setFeldstatus(Feldstatus.MARKIERT);
        }
        else if (feld.getFeldstatus().equals(Feldstatus.MARKIERT)){
            feld.setFeldstatus(Feldstatus.VERDECKT);
        }
    }

    /**
     * Liefert den Spielstatus.
     * @return der Spielstatus
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
     * @return das Spielfeld
     */
    public Spielfeld getSpielfeld(){
        return spielfeld;
    }

}
