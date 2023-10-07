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
 * Beschreiben Sie hier die Klasse Controller.
 * 
 * @author Tim Busch, Beatrice Wellmann
 * @version 1
 */

public class Controller {
    @FXML
    private Button leichtButton;
    @FXML
    private Button mittelButton;
    @FXML
    private Button schwerButton;
    @FXML
    private GridPane spielfeldGrid;
    @FXML
    private TextField bombeTextfeld;
    @FXML
    private TextField punkteTextfeld;
    @FXML
    private TextField highscoreTextfeld;
    @FXML
    private Label statusLabel;
    @FXML
    private ImageView statusBild;

    private Spiel spiel;
    private Spielfeld spielfeld;
    private long startZeit;
    private Button[][] buttons;
    private int bildBreite = 20;
    private int bildHoehe = 20;
    private long highscore = 0;

    @FXML
    void startLeicht(ActionEvent event) throws IOException {
        start(Schwierigkeitsstufe.LEICHT);		
    }

    @FXML
    void startMittel(ActionEvent event) throws IOException {
        start(Schwierigkeitsstufe.MITTEL);		
    }

    @FXML
    void startSchwer(ActionEvent event) throws IOException {
        start(Schwierigkeitsstufe.SCHWER);		
    }

    private void start(Schwierigkeitsstufe schwierigkeitsstufe) throws IOException {
        spiel = new Spiel(this, schwierigkeitsstufe);
        spielfeld = spiel.getSpielfeld();
        startZeit = System.currentTimeMillis();
        aktualisiereSpielstatus();
        fuelleSpielfeld();		
    }

    private void aktualisiereBombenTextfeld() {
        int restBomben = spielfeld.getAnzahlBomben() - spiel.getMarkierteFelder();
        bombeTextfeld.setText("" + restBomben);
    }

    private long berechnePunkte() {
        long zeitdifferenz = (System.currentTimeMillis() - startZeit) / 1000;
        long aktuellePunkte = spiel.getSchwierigkeitsstufe().getHoechstpunkte()-zeitdifferenz;
        long punkte = Math.max(aktuellePunkte,0);
        return punkte;
    }

    private void aktualisiereHighscore(){
        long punkte = berechnePunkte();
        highscore = Math.max(highscore, punkte);
        highscoreTextfeld.setText(""+highscore);
    }

    private void aktualisiereStatusBild(){
        Spielstatus status = spiel.getSpielstatus();
        Image bild = new Image("bilder/" + status.name() + ".png", bildHoehe*2, bildBreite*2, true, false);
        statusBild.setImage(bild);
    }

    public void aktualisiereSpielstatus() {
        Spielstatus status = spiel.getSpielstatus();
        aktualisiereStatusBild();
        aktualisiereBombenTextfeld();
        punkteTextfeld.setText(""+berechnePunkte());
        if (status == Spielstatus.GEWONNEN) {
            deckeSpielfeldAuf();
            statusLabel.setText("Du hast gewonnen!");
            statusLabel.setVisible(true);
            aktualisiereHighscore();
        } else if (status == Spielstatus.VERLOREN) {
            deckeSpielfeldAuf();
            statusLabel.setText("Du hast verloren!");
            statusLabel.setVisible(true);
        } else {
            statusLabel.setVisible(false);
        }
    }

    private void fuelleSpielfeld() {
        spielfeldGrid.getChildren().clear();
        spielfeldGrid.getRowConstraints().clear();
        spielfeldGrid.getColumnConstraints().clear();
        int reihen = spielfeld.getReihen();
        int spalten = spielfeld.getSpalten();
        buttons = new Button[reihen][spalten];
        for (int r = 0; r < reihen; r++) {
            for (int s = 0; s < spalten; s++) {
                Feld feld = spielfeld.getFeld(r, s);
                Button fButton = new Button();
                mausListenerHinzufuegen(fButton, r, s);
                spielfeldGrid.add(fButton, s, r);
                setzeBildFuerButton(fButton, feld.getBild(bildBreite, bildHoehe));
                buttons[r][s] = fButton;
            }
        }		
        ((Stage)spielfeldGrid.getScene().getWindow()).sizeToScene();
    }

    public void deckeSpielfeldAuf() {
        for (int r = 0; r < spielfeld.getReihen(); r++) {
            for (int s = 0; s < spielfeld.getSpalten(); s++) {
                Feld feld = spielfeld.getFelder()[r][s];
                if (feld.getFeldstatus() != Feldstatus.AUFGEDECKT) {
                    feld.setFeldstatus(Feldstatus.AUFGEDECKT);
                }
                Button b = buttons[r][s];
                aktualisiereBild(feld, b);
            }
        }
    }

    private void aktualisiereBild(Feld feld, Button fButton) {
        Image bild = feld.getBild(bildBreite, bildHoehe);
        setzeBildFuerButton(fButton, bild);
    }

    private void setzeBildFuerButton(Button button, Image bild) {
        ImageView view = new ImageView();
        view.setImage(bild);
        statusBild.setFitHeight(bildHoehe);
        statusBild.setFitWidth(bildBreite);
        statusBild.preserveRatioProperty();
        button.setGraphic(view);
    }

    private void mausListenerHinzufuegen(Button fButton, int r, int s) {
        fButton.setOnMouseClicked(event -> {
                    Feld feld = spielfeld.getFeld(r, s);
                    if (spiel.getSpielstatus().equals(Spielstatus.NICHTGESTARTET)) {
                        spiel.setSpielstatus(Spielstatus.LAUFEND);
                    }
                    if (spiel.getSpielstatus().equals(Spielstatus.LAUFEND)) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            List<Feld> neueFelder = new ArrayList<>();
                            if (!feld.getFeldstatus().equals(Feldstatus.AUFGEDECKT)) {
                                neueFelder = spiel.deckeAuf(feld);
                            } else if (event.getClickCount() == 2) {
                                if (spielfeld.getMarkierteNachbarnAnzahl(feld) == feld.getNachbarBombenAnzahl()) {
                                    neueFelder = spiel.deckeVerdeckteNachbarnAuf(feld);
                                }
                            }
                            if (spiel.getSpielstatus().equals(Spielstatus.LAUFEND)) {
                                for (Feld f : neueFelder) {
                                    Button b = buttons[f.getReihe()][f.getSpalte()];
                                    aktualisiereBild(f, b);
                                }
                                aktualisiereSpielstatus();
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            spiel.markiere(feld);
                            aktualisiereBild(feld, fButton);
                            aktualisiereSpielstatus();
                        }
                    }
            });
    }

}