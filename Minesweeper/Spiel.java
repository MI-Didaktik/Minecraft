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
}
