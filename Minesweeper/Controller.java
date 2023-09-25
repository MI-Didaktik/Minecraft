import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;
import javafx.event.EventHandler;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Beschreiben Sie hier die Klasse Controller.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */ 

public class Controller {

    @FXML
    private Button startButton;
    @FXML
    private GridPane spielfeldGrid;
    @FXML
    private TextField bombeTextfeld;
    @FXML
    private TextField zeitTextfeld;
    @FXML
    private TextField highscoreTextfeld;

    private Spiel spiel;
    //TODO: brauch man das
    private Spielfeld spielfeld;

    private Button[][] buttons;

    @FXML
    void start(ActionEvent event) throws IOException {
        spiel = new Spiel(this);
        spielfeld = spiel.getSpielfeld();
        fuelleSpielfeld();
    }

    private void fuelleSpielfeld(){
        spielfeldGrid.getChildren().clear(); 
        int reihen = spielfeld.getReihen();
        int spalten = spielfeld.getSpalten();
        buttons = new Button[reihen][spalten];
        for (int r = 0; r < reihen; r++){
            for (int s = 0; s< spalten; s++){
                Feld feld = spielfeld.getFeld(r, s);
                Button fButton = new Button();
                fButton.setId("#b"+r+s);
                mausListenerHinzufuegen(fButton,r,s);
                spielfeldGrid.add(fButton, s, r);
                setzeBildFuerButton(fButton, feld.getBild());
                buttons[r][s] = fButton;
            }
        }
        //TODO: Spielfeldgröße an Grid anpassen
    }

    public void aktualisiereSpielstatus(){
        if (spiel.getSpielstatus().equals(Spielstatus.GEWONNEN)){
            deckeSpielfeldAuf();
            //TODO Zeige Nachricht, aktualisiere Highscore
        }
        else if (spiel.getSpielstatus().equals(Spielstatus.VERLOREN)){
            deckeSpielfeldAuf();
            //TODO Zeige Nachricht
        }
        //label, smiley, highscore ändern
    }

    public void deckeSpielfeldAuf(){
        for (int r = 0 ; r< spielfeld.getReihen(); r++){
            for (int s = 0 ; s< spielfeld.getSpalten(); s++){
                Feld feld =spielfeld.getFelder()[r][s];
                if (feld.getFeldstatus()!=Feldstatus.AUFGEDECKT){
                    feld.setFeldstatus(Feldstatus.AUFGEDECKT);
                }
                Button b = buttons[r][s];
                aktualisiereBild(feld, b);
            }  }
    }

    private void aktualisiereBild(Feld feld, Button fButton){
        Image bild = feld.getBild();
        setzeBildFuerButton(fButton, bild); 
    }

    private void setzeBildFuerButton(Button button, Image bild){
        ImageView view = new ImageView();
        view.setImage(bild);
        button.setGraphic(view); 
    }

    private void mausListenerHinzufuegen(Button fButton, int r, int s){
        fButton.setOnMouseClicked(event ->
                {
                    Feld feld = spielfeld.getFeld(r,s);
                    Spielstatus status = spiel.getSpielstatus();
                    if (status.equals(Spielstatus.NICHTGESTARTET)){
                        spiel.setSpielstatus(Spielstatus.LAUFEND);
                        status = spiel.getSpielstatus();
                        //TODO: starte timer
                    }
                    if (status.equals(Spielstatus.LAUFEND) && !feld.getFeldstatus().equals(Feldstatus.AUFGEDECKT)){
                        if (event.getButton() == MouseButton.PRIMARY )
                        {
                            List<Feld> neueFelder = spiel.deckeAuf(feld);
                            if (spiel.getSpielstatus().equals(Spielstatus.LAUFEND)){
                                for (Feld f: neueFelder){
                                    Button b = buttons[f.getReihe()][f.getSpalte()];                                   
                                    aktualisiereBild(f, b); 
                                }   
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY)
                        {
                            spiel.markiere(feld);
                            aktualisiereBild(feld, fButton);
                            //aktualisiere Bombenanzahl

                            //TODO: bild ändern spiellogik, bombenanzahl etc
                        }
                    }
            });
    }

}