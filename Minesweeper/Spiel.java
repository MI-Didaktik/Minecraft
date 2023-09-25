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

    public void deckeAuf(Feld feld){
        feld.setFeldstatus(Feldstatus.AUFGEDECKT);
        //TODO Spiellogik
        // prüfe ob gewonnen oder verloren
            // //TODO: stoppe timer, highscore....
        //rückmeldung an controller -> decke felder 1 - n auf
    }

    public void markiere(Feld feld){
        //TODO
    }

    public void deckeZu(Feld feld){
        //TODO 
    }
}
