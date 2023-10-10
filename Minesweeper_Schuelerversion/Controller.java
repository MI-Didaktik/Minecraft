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
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.Map;

/**
 * 
 * @author 
 * @version 
 */

public class Controller {
    @FXML
    private GridPane spielfeldGrid;
    @FXML
    private ImageView statusBild;

    private int bildBreite = 20;
    private int bildHoehe = 20;

    @FXML
    void start(ActionEvent event) throws IOException {
        fuelleSpielfeld();
        aktualisiereStatusBild();        
    }

    private void fuelleSpielfeld() {
        // NICHT ANPASSEN: leert das spielfeld
        spielfeldGrid.getChildren().clear();
        spielfeldGrid.getRowConstraints().clear();
        spielfeldGrid.getColumnConstraints().clear();
        

        //erzeugt einen neuen button und ein Feld ruft die Methode mausListenerHinzufuegen auf
        Button button = new Button();
        Feld feld = new Feld();
        
        //ruft die Methode mausListenerHinzufuegen auf
        mausListenerHinzufuegen(button, 0, 0);
        aktualisiereBild(button, feld);
        
        //Fuegt den button zu reihe 0 und spalte 0 des spielfeldGrid hinzu 
        //(Die Groesse des spielfeldGrid muss nicht vorher festgelegt werden)
        spielfeldGrid.add(button, 0, 0);

        // NICHT ANPASSEN: passt das Fenster an die Groesse des spielfeldGrid an
        ((Stage) spielfeldGrid.getScene().getWindow()).sizeToScene();
    }

    private void mausListenerHinzufuegen(Button button, int reihe, int spalte) {
        // NICHT ANPASSEN: fuegt einem button die Funktion hinzu, die bei Mausklick ausgefuehrt werden soll
        button.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) { 
                        //Was soll bei Linksklick passieren?
                    }
                    if (event.getButton() == MouseButton.SECONDARY) { 
                        //Was soll bei Rechtsklick passieren?
                    }
                    if (event.getClickCount() == 2) { 
                        //Was soll bei Doppelklick passieren?
                    }

            });
    }

    private void aktualisiereBild(Button button, Feld feld) {
        Image bild = feld.getBild(bildBreite, bildHoehe);
        ImageView view = new ImageView();
        view.setImage(bild);
        button.setGraphic(view);
    }

    private void aktualisiereStatusBild() {
        Image bild = new Image("bilder/" + "NICHTGESTARTET" + ".png", bildHoehe * 2, bildBreite * 2, true, false);
        statusBild.setImage(bild);
    }

}