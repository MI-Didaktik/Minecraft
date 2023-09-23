import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Beschreiben Sie hier die Klasse MinesweeperGUI.
 * 
 * @author Tim Busch, Beatrice Wellmann 
 * @version 1
 */ 

public class MinesweeperGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/minesweeper.fxml"));
            VBox root = (VBox) loader.load();
            Scene scene = new Scene(root);

            // Fenster festlegen
            primaryStage.setScene(scene);
            primaryStage.setTitle("Minesweeper");
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(event ->
                {
                    System.out.print('\u000C'); // Loescht die Konsolenausgabe
                    System.exit(0);             // Beendet
                });
            primaryStage.show();
        } 
        catch(Exception e)    {
            e.printStackTrace();
        }
    }

}