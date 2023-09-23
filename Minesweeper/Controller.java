import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Beschreiben Sie hier die Klasse Spielfeld.
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
        spiel = new Spiel();
        spielfeld = spiel.getSpielfeld();
        fuelleSpielfeld();
    }

    // @FXML
    // void spielerHinzu(ActionEvent event) throws IOException {
        // if(anzSpieler<1 && !TextFieldSpielerName.getText().isBlank()){
            // Spieler s = new Spieler(TextFieldSpielerName.getText());
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/kartenhand.fxml"));
            // Pane newLoadedPane = loader.load();
            // kartenHand = (ControllerKH)loader.getController();
            // kartenHand.setCroupier(c);
            // kartenHand.setCKH(croupierKH);
            // kartenHand.setSpieler(s);
            // kartenHand.teilStarthand();

            // GridPaneSpielFelder.add(newLoadedPane, anzSpieler+2, 0);
            // anzSpieler++;
            // ButtonSpielerHinzu.setVisible(false);
            // TextFieldSpielerName.setVisible(false);
        // }
    // }
    
    private void fuelleSpielfeld(){
        spielfeldGrid = new GridPane();
        Feld[][] felder = spiel.getFelder();
        int reihen = spielfeld.getReihen();
        int spalten = spielfeld.getSpalten();
        for (int r = 0; r < reihen; r++){
            for (int s = 0; s< spalten; s++){
                Feld f = felder[r][s];
                Button fbutton = new Button();
                fbutton.setId("b"+r+s);
                // fbutton.addEventHandler(EventType., _EventHandler<? super T>_)
                spielfeldGrid.add(fbutton, r, s);
            }
        }
    }
}