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

    @FXML
    void start(ActionEvent event) throws IOException {
        spiel = new Spiel(this);
        spielfeld = spiel.getSpielfeld();
        fuelleSpielfeld();
        //TODO: entfernen
        highscoreTextfeld.setText(""+0+" s");
    }

    private void fuelleSpielfeld(){
        spielfeldGrid.getChildren().clear(); 
        int reihen = spielfeld.getReihen();
        int spalten = spielfeld.getSpalten();
        for (int r = 0; r < reihen; r++){
            for (int s = 0; s< spalten; s++){
                Feld feld = spielfeld.getFeld(r, s);
                Button fButton = new Button();
                fButton.setId("#b"+r+s);
                mausListenerHinzufuegen(fButton,r,s);
                spielfeldGrid.add(fButton, r, s);
                setzeBildFuerButton(fButton, feld.getBild());
            }
        }
        //TODO: Spielfeldgröße an Grid anpassen
    }

    public void aktualisiereSpielstatus(){
        //status auslesen
        //label, smiley, highscore ändern
    }

    private void aktualisiereBild(Feld feld, Button fButton){
        Image bild = feld.getBild();
        setzeBildFuerButton(fButton, bild); 
    }

    //TODO kürzen
    public void aktualisiereFeld(Feld feld, int r, int s){
        Button fButton = (Button) spielfeldGrid.getScene().lookup("#b"+r+s);
        aktualisiereBild(feld, fButton);
    }

    private void setzeBildFuerButton(Button button, Image bild){
        ImageView view = new ImageView();
        view.setImage(bild);
        button.setGraphic(view); 
    }

    private void mausListenerHinzufuegen(Button fButton, int r, int s){
        fButton.setOnMouseClicked(event ->
                {
                    Spielstatus status = spiel.getSpielstatus();
                    if (status.equals(Spielstatus.NICHTGESTARTET)){
                        spiel.setSpielstatus(Spielstatus.LAUFEND);
                        status = spiel.getSpielstatus();
                        //TODO: starte timer
                    }
                    if (status.equals(Spielstatus.LAUFEND)){
                        // Was passiert beim linken oder rechten Mausklick?
                        if (event.getButton() == MouseButton.PRIMARY)
                        {
                            fButton.setDisable(true);
                            Feld feld = spielfeld.getFeld(r,s);
                            spiel.deckeAuf(feld);
                            if (spiel.getSpielstatus().equals(Spielstatus.LAUFEND)){
                                aktualisiereBild(feld, fButton);    
                            }
                            //TODO: bild ändern spiellogik, bombenanzahl etc
                        } else if (event.getButton() == MouseButton.SECONDARY)
                        {
                            fButton.setDisable(false);
                            Feld feld = spielfeld.getFeld(r,s);
                            if (feld.getFeldstatus().equals(Feldstatus.ZUGEDECKT)){
                                spiel.markiere(feld);
                            }
                            else if (feld.getFeldstatus().equals(Feldstatus.MARKIERT)){
                                spiel.deckeZu(feld);
                            }
                            aktualisiereBild(feld, fButton);

                            //TODO: bild ändern spiellogik, bombenanzahl etc
                        }
                    }
            });
    }

}