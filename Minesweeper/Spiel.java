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
        spielfeld = new Spielfeld(20,10,10);
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

    public List<Feld> deckeVerdeckteNachbarnAuf(Feld feld){
        List<Feld> neueFelder = new ArrayList<>();
        for (Feld fNachbar : spielfeld.getNachbarFelder(feld)){
            if (fNachbar.getFeldstatus().equals(Feldstatus.VERDECKT)){
                neueFelder.addAll(deckeAuf(fNachbar));
            }
        }
        return neueFelder;
    }

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

    public void markiere(Feld feld){
        if (feld.getFeldstatus().equals(Feldstatus.VERDECKT)){
            feld.setFeldstatus(Feldstatus.MARKIERT);
        }
        else if (feld.getFeldstatus().equals(Feldstatus.MARKIERT)){
            feld.setFeldstatus(Feldstatus.VERDECKT);
        }
    }

}
