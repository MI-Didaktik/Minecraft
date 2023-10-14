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
 * Dies ist die Klasse Controller, welche alle Interaktionen mit der
 * Nutzeroberflaeche handhabt. Hier kann auf die Objekte aus der .fxml-Datei,
 * welche mit der @FXML-Annotation gekennzeichnet werden zugegriffen werden.
 * 
 * @author Tim Busch, Beatrice Wellmann
 * @version 1
 */

public class Controller {
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

    private Button[][] buttons;
    private int bildBreite = 20;
    private int bildHoehe = 20;

    private long startZeit;

    /**
     * Wird bei Aktivierung des Buttons Leicht ausgefuehrt und startet das Spiel auf der Schwierigkeitsstufe LEICHT.
     * @param event
     */
    @FXML
    void startLeicht(ActionEvent event) throws IOException {
        start(Schwierigkeitsstufe.LEICHT);
    }

    /**
     * Wird bei Aktivierung des Buttons Mittel ausgefuehrt und startet das Spiel auf der Schwierigkeitsstufe MITTEL.
     * @param event
     */
    @FXML
    void startMittel(ActionEvent event) throws IOException {
        start(Schwierigkeitsstufe.MITTEL);
    }

    /**
     * Wird bei Aktivierung des Buttons Schwer ausgefuehrt und startet das Spiel auf der Schwierigkeitsstufe SCHWER.
     * @param event
     */
    @FXML
    void startSchwer(ActionEvent event) throws IOException {
        start(Schwierigkeitsstufe.SCHWER);
    }

    /**
     * Erzeugt eine neue Instanz der Klasse Spiel, haelt die startZeit fest und
     * erzeugt ein neues Spielfeld mitsamt Anpassung der Textfelder und Label auf der Nutzeroberflaeche
     * @param schwierigkeitsstufe
     */
    private void start(Schwierigkeitsstufe schwierigkeitsstufe) {
        spiel = new Spiel(schwierigkeitsstufe);
        spielfeld = spiel.getSpielfeld();
        startZeit = System.currentTimeMillis();

        fuelleSpielfeld();
        statusLabel.setVisible(false);
        spiel.setSpielstatus(Spielstatus.LAUFEND);
        aktualisiereOberflaeche();
    }

    /**
     * Fuellt das spielfeldGrid mit Buttons. 
     * Jedem Button ist ein Feld-Objekt zugeordnet und ein Bild, welches von dem Feldstatus ahaengig ist. 
     * Um auf diese Feld-Buttons zugreifen zu koennen, werden sie in der Liste buttons abgespeichert.
     */
    private void fuelleSpielfeld() {
        // leert das spielfeld
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
                aktualisiereBild(feld, fButton);
                buttons[r][s] = fButton;
            }
        }

        // passt das Fenster an die Spielfeldgr��e an
        ((Stage) spielfeldGrid.getScene().getWindow()).sizeToScene();
    }

    /**
     * Fuegt einem Button einen Listener hinzu, welcher definiert was passiert, wenn dieser Button angeklickt wird. 
     * Wenn der Spielstatus LAUFEND entspricht, wird zwischen Links- und Rechtsklick unterschieden. 
     * Bei einfachem Linksklick wird ein markiertes oder verdecktes Feld aufgedeckt. 
     * Bei Doppel-Linksklick auf ein aufgedecktes Feld werden alle verdeckten Nachbarfelder aufgedeckt, wenn bereits ausreichend Nachbarfelder markiert wurden.
     * Bei Rechtsklick wird ein verdecktes Feld markiert bzw. ein markiertes Feld wieder verdeckt.
     * Anschliessend wird die Oberflaeche aktualisiert.
     * @param button
     * @param reihe
     * @param spalte
     * @see aktualisiereOberflache()
     */
    private void mausListenerHinzufuegen(Button button, int reihe, int spalte) {
        // fuegt einem button die Funktion hinzu, die bei Mausklick ausgefuehrt werden soll
        button.setOnMouseClicked(event -> {

                    Feld feld = spielfeld.getFeld(reihe, spalte);
                    if (spiel.getSpielstatus() == Spielstatus.LAUFEND) {
                        if (event.getButton() == MouseButton.PRIMARY) { // Linksklick
                            List<Feld> neueFelder = new ArrayList<>();
                            if (feld.getFeldstatus() != Feldstatus.AUFGEDECKT) {
                                neueFelder = spiel.deckeAuf(feld);
                            } else if (event.getClickCount() == 2) { // Doppelklick
                                if (spielfeld.getMarkierteNachbarnAnzahl(feld) == feld.getNachbarBombenAnzahl()) {
                                    neueFelder = spiel.deckeVerdeckteNachbarnAuf(feld);
                                }
                            }
                            for (Feld f : neueFelder) {
                                Button b = buttons[f.getReihe()][f.getSpalte()];
                                aktualisiereBild(f, b);
                            }
                        } else if (event.getButton() == MouseButton.SECONDARY) { // Rechtsklick
                            spiel.markiereOderVerdecke(feld);
                            aktualisiereBild(feld, button);
                        }
                        aktualisiereOberflaeche();
                    }

            });
    }

    /**
     * aktualisiert das Smiley, welches den Spielstatus (GEWONNEN, VERLOREN,
     * LAUFEND, NICHTGESTARTET) anzeigt aktualisiert die Anzahl momentan verdeckter
     * Bomben und die aktuelle Punktzahl Ist das Spiel gewonnen oder verloren, so
     * wird das Spielfeld aufgedeckt und eine Nachricht angezeigt. Ist das Spiel
     * gewonnen, so wird zusaetzlich ggf. der Highscore aktualisiert.
     */
    private void aktualisiereOberflaeche() {
        Spielstatus status = spiel.getSpielstatus();
        aktualisiereStatusBild();
        aktualisiereBombenTextfeld();
        punkteTextfeld.setText("" + berechnePunkte());
        if (status == Spielstatus.GEWONNEN) {
            deckeSpielfeldAuf();
            statusLabel.setText("Du hast gewonnen!");
            statusLabel.setVisible(true);
            aktualisiereHighscore();
        } else if (status == Spielstatus.VERLOREN) {
            deckeSpielfeldAuf();
            statusLabel.setText("Du hast verloren!");
            statusLabel.setVisible(true);
        }
    }

    /**
     * Aktualisiert das auf einem Feld-Button angezeigte Bild.
     * 
     * @param feld
     * @param button
     */
    private void aktualisiereBild(Feld feld, Button button) {
        Image bild = feld.getBild(bildBreite, bildHoehe, spiel.getSpielstatus());
        ImageView view = new ImageView();
        view.setImage(bild);
        button.setGraphic(view);
    }

    /**
     * Aktualisiert das Textfeld, welches die Anzahl momentan noch nicht markierter
     * Bomben anzeigt.
     */
    private void aktualisiereBombenTextfeld() {
        int restBomben = spielfeld.getAnzahlBomben() - spiel.getMarkierteFelder();
        bombeTextfeld.setText("" + restBomben);
    }

    /**
     * Aktualisiert das Smiley, welches den Spielstatus (GEWONNEN, VERLOREN,
     * LAUFEND, NICHTGESTARTET) anzeigt
     */
    private void aktualisiereStatusBild() {
        Spielstatus status = spiel.getSpielstatus();
        Image bild = new Image("bilder/" + status.name() + ".png", bildHoehe * 2, bildBreite * 2, true, false);
        statusBild.setImage(bild);
    }

    /**
     * Aktualisiert das Highscore-Feld, welches den seit Start des Fensters
     * erreichten Highscore anzeigt. Ist die erreichte Punktzahl hoeher als der
     * Highscore, so wird dieser angepasst.
     */
    private void aktualisiereHighscore() {
        long punkte = berechnePunkte();
        long highscore = Math.max(spiel.getHighscore(), punkte);
        highscoreTextfeld.setText("" + highscore);
        spiel.setHighscore(highscore);
    }

    /**
     * Berechnet die Anzahl momentan erreichter Punkte aus der durch die
     * Schwierigkeitsstufe vorgegebene Hoechstpunktzahl und der seit dem Start
     * vergangenen Zeit.
     * 
     * @return punkte
     */
    private long berechnePunkte() {
        long zeitdifferenz = (System.currentTimeMillis() - startZeit) / 1000;
        long aktuellePunkte = spiel.getSchwierigkeitsstufe().getHoechstpunkte() - zeitdifferenz;
        long punkte = Math.max(aktuellePunkte, 0);
        return punkte;
    }

    /**
     * Deckt das gesamte Spielfeld auf.
     */
    private void deckeSpielfeldAuf() {
        for (int reihe = 0; reihe < spielfeld.getReihen(); reihe++) {
            for (int spalte = 0; spalte < spielfeld.getSpalten(); spalte++) {
                Feld feld = spielfeld.getFelder()[reihe][spalte];
                if (feld.getFeldstatus() != Feldstatus.AUFGEDECKT) {
                    feld.setFeldstatus(Feldstatus.AUFGEDECKT);
                    Button button = buttons[reihe][spalte];
                    aktualisiereBild(feld, button);
                }
            }
        }
    }

}