import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Dies ist die Klasse MinesweeperGUI, welche zum Start des Spiels und das Laden der fxml-Datei benötigt wird.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */ 

public class MinesweeperGUI extends Application {

    /**
     * Lädt die Datei minesweeper.fxml und erzeugt das zugehörige Fenster.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/minesweeper.fxml"));
            VBox root = (VBox) loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Minesweeper");
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(event ->
                {
                    System.out.print('\u000C'); 
                    System.exit(0);             
                });
            primaryStage.show();
        } 
        catch(Exception e)    {
            e.printStackTrace();
        }
    }

}