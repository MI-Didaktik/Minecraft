import java.util.List;
import java.util.ArrayList;

/**
 * Beschreiben Sie hier die Klasse Spiel.
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
     */
    public Spiel(Controller controller)
    {
        this.controller = controller;
        //TODO: übergabe der reihen/spalten/bomben anzahl
        //ggf. Schwierigkeitslevel statt volle Kontrolle über spalten/zeilen/bomben-Anzahl
        spielfeld = new Spielfeld(10,10,10);
        spielstatus = Spielstatus.NICHTGESTARTET;  
    }

    public Spielfeld getSpielfeld(){
        return spielfeld;
    }

    public Spielstatus getSpielstatus(){
        return spielstatus;
    }

    public void setSpielstatus(Spielstatus s){
        this.spielstatus = s;
    }

    public List<Feld> deckeAuf(Feld feld){
        List<Feld> neueFelder = new ArrayList<>();
        neueFelder.add(feld);
        feld.setFeldstatus(Feldstatus.AUFGEDECKT);
        if (feld.istBombe()){
            // spielstatus = Spielstatus.VERLOREN;
        }
        else {
            if (feld.getNachbarnAnzahl() == 0){
                //Alternativ: spielfeld.deckeFreieNachbarnAuf(feld, neueFelder);
                spielfeld.deckeFreieNachbarnAuf(feld, neueFelder);
            }
            spielfeld.aktualisiereZugedeckteFelder(neueFelder.size());
            if (spielfeld.getAnzahlZugedeckt()==spielfeld.getAnzahlBomben()){
                spielstatus = Spielstatus.GEWONNEN;
            }
        }
        return neueFelder;
        //TODO: stoppe timer, highscore....
    }

    public void markiere(Feld feld){
        if (feld.getFeldstatus().equals(Feldstatus.ZUGEDECKT)){
            feld.setFeldstatus(Feldstatus.MARKIERT);
        }
        else if (feld.getFeldstatus().equals(Feldstatus.MARKIERT)){
            feld.setFeldstatus(Feldstatus.ZUGEDECKT);
        }
    }

}
