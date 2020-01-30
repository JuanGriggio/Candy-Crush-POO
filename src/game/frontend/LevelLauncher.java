package game.frontend;

import game.backend.CandyGame;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class LevelLauncher {
    public static void launchLevel(CandyGame game) {
        if(game.getLevelClass() == null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("YOU WON");
            alert.setHeaderText("Congratulations! You won the game!!!!");
            alert.setContentText("Go to main menu?\nCredits:\nLucía Digon\nJuan Gabriel Griggio\n");
            Optional<ButtonType> confirm = alert.showAndWait();
            if(confirm.isPresent()) {
                if (confirm.get() == ButtonType.OK) {
                    GameApp.showMenu(GameApp.theStage);
                }
                else
                    Platform.exit();
            }
            return;
        }
        Stage primaryStage = GameApp.theStage;
        CandyFrame frame = new CandyFrame(game);
        Scene scene = new Scene(frame);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
