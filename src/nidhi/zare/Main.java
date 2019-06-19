package nidhi.zare;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Controller controller;
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml")); 
		GridPane rootGridPane = loader.load();
		
	    controller = loader.getController();
	    controller.createPlayground();
	    
	    MenuBar menuBar = createMenu();
	    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
	    
	    Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
	    
	    menuPane.getChildren().add(menuBar);
	    
		Scene scene = new Scene(rootGridPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Connect Four");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public MenuBar createMenu() {
		//FileMenu
		Menu fileMenu = new Menu("File");
		
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(event -> {
			controller.resetGame();
		});
		
		MenuItem resetGame = new MenuItem("Reset Game");
		
		resetGame.setOnAction(event -> {
			controller.resetGame();
		});
		
		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		MenuItem exitGame = new MenuItem("Exit Game");
		
		exitGame.setOnAction(event->{
			exitGame();
		});
		
		fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);
		
		//HelpMenu
		Menu helpMenu = new Menu("Help");
		
		MenuItem aboutGame = new MenuItem("About Connect4");
		aboutGame.setOnAction(event -> {
			aboutConnect4();
		});
		
		SeparatorMenuItem separator = new SeparatorMenuItem();
		
		MenuItem aboutMe = new MenuItem("About Me");
		aboutMe.setOnAction(event -> {
			aboutMe();
		});
		
		helpMenu.getItems().addAll(aboutGame, separator, aboutMe);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		menuBar.getMenus().addAll(helpMenu);
		
		return menuBar;
	}
	
	private void aboutMe() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About The Developer");
		alert.setHeaderText("Nidhi Zare");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setContentText("I am Computer Science undergraduate. I am Data Science enthusiast and aspiring Software Developer.");
		alert.show();
		
	}

	private void aboutConnect4() {
		Alert alert = new Alert(AlertType.INFORMATION, "Connect Four is a two-player connection game in which the players first choose a color and then take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs. Connect Four is a solved game. The first player can always win by playing the right moves.", ButtonType.OK);
		alert.setTitle("About Connect Four");
		alert.setHeaderText("How to Play?");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
	}

	private void exitGame() {
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
