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

    private int anzSpieler=0;
    private Spiel spiel;
    private Spielfeld spielfeld;

    @FXML
    void start(ActionEvent event) throws IOException {
        spiel = new Spiel(this);
        spielfeld = spiel.getSpielfeld();
        fuelleSpielfeld();
    }

    private void fuelleSpielfeld(){
        spielfeldGrid = new GridPane();
        Feld[][] felder = spiel.getFelder();
        int reihen = spielfeld.getReihen();
        int spalten = spielfeld.getSpalten();
        for (int r = 0; r < reihen; r++){
            for (int s = 0; s< spalten; s++){
                Feld f = felder[r][s];
                Button fButton = new Button();
                fButton.setId("#b"+r+s);
                mausListenerHinzufuegen(fButton,r,s);
                spielfeldGrid.add(fButton, r, s);
            }
        }
        //TODO: Spielfeldgröße an Grid anpassen
    }
    
    public void aktualisiereSpielstatus(){
        //status auslesen
        //label, smiley, highscore ändern
    }

    public void feldAktualisieren(Feld feld, int r, int s){
        Image image = feld.getBild();
        Button fButton = (Button) spielfeldGrid.getScene().lookup("#b"+r+s);
        ImageView view = new ImageView();
        view.setImage(image);
        fButton.setGraphic(view); 
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
                            //TODO: bild ändern spiellogik, bombenanzahl etc
                        } else if (event.getButton() == MouseButton.SECONDARY)
                        {
                            fButton.setDisable(false);
                            //TODO: bild ändern spiellogik, bombenanzahl etc
                        }
                    }
            });
    }

}