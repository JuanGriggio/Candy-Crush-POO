package game.frontend;

import game.backend.CandyGame;
import game.backend.level.*;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//import static javafx.geometry.Pos.CENTER;
//import static javafx.geometry.Pos.CENTER_LEFT;
import static javafx.geometry.Pos.*;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage theStage;

	@Override
	public void start(Stage primaryStage) {
		theStage = primaryStage;
		showMenu(primaryStage);
	}

	private static Button createButton(String text) {			// CAMBIAR COLORES Y BORDES!!!!!!!!!
		Button b = new Button();
		Font font = Font.font(16);

		b.setPrefSize(150, 20);
		b.setStyle("-fx-border-color: #000000; -fx-background-color: #ffffff;-fx-border-width: 2px;");
		b.setAlignment(CENTER);
		b.setText(text);
		b.setFont(font);


		return b;
	}

	public static void showMenu(Stage primaryStage) {
		Button l1 = createButton("Juego tradicional");
		Button l2 = createButton("Fruit Mode");
		Button l3 = createButton("Timer Candy");
		Button about = createButton("Acerca de");

		l1.setOnAction(event -> LevelLauncher.launchLevel(new CandyGame(Level1.class)));
		l2.setOnAction(event -> LevelLauncher.launchLevel(new CandyGame(Level2.class)));
		l3.setOnAction(event -> LevelLauncher.launchLevel(new CandyGame(Level3.class)));

		about.setOnAction(event -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Acerca De");
			alert.setHeaderText("POO CRUSH!");
			alert.setContentText("Hecho por:\nLucía Digon\nJuan Gabriel Griggio\n");
			alert.showAndWait();
		});

		VBox layout = new VBox();
		Image image = new Image("images/Splash screen.jpeg");
		BackgroundSize size = new BackgroundSize(480, 360, false, false, false, false);
		BackgroundPosition position = new BackgroundPosition(Side.LEFT, 0, false, Side.BOTTOM, 0, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, NO_REPEAT, NO_REPEAT, position, size);

		layout.setBackground(new Background(backgroundImage));
		layout.setSpacing(10);
		layout.setAlignment(BOTTOM_LEFT);
		layout.getChildren().addAll(l1, l2, l3, about);

		Scene scene = new Scene(layout, 480, 360);
		primaryStage.setScene(scene);
		primaryStage.setTitle("POO Crush!");
		primaryStage.show();
	}
}
