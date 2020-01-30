package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Optional;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;

	public CandyFrame(CandyGame game) {
		this.game = game;
		getChildren().add(new AppMenu());
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);
		game.initGame();
		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = images.getImage(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null, element)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image, element)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});

		listener.gridUpdated();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				System.out.println("Get first = " +  lastPoint);
			} else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				if (newPoint != null) {
					System.out.println("Get second = " +  newPoint);
					game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
					String message;
					if (game().isFinished()) {
						if (game().playerWon()) {
							message = "Score: " + ((Long)game().getScore()).toString() + ", Finished - Player Won!";
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Finished - Player Won!");
							alert.setHeaderText("Congratulations, you won!");
							alert.setContentText("Want to go to the next level or go to the main menu?");
							Optional<ButtonType> confirm = alert.showAndWait();
							if(confirm.isPresent()) {
								if (confirm.get() == ButtonType.OK) {
									LevelLauncher.launchLevel(new CandyGame(game.nextLevel()));
								}
								else
									GameApp.showMenu(GameApp.theStage);
							}
						} else {
							message = "Score: " + ((Long)game().getScore()).toString() + ", Finished - Loser!";
							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Finished - Loser!");
							alert.setHeaderText("Better luck next time!");
							alert.setContentText("Want to try again or go to the main menu?");
							Optional<ButtonType> confirm = alert.showAndWait();
							if(confirm.isPresent()) {
								if (confirm.get() == ButtonType.OK) {
									LevelLauncher.launchLevel(new CandyGame(game.currentLevel()));
								}
								else
									GameApp.showMenu(GameApp.theStage);
							}
						}
					} else {
						message = game().getInfo();
					}
					scorePanel.updateScore(message);
					lastPoint = null;
				}
			}
		});

	}

	private CandyGame game() {
		return game;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
