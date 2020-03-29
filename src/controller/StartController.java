package controller;

/**
*StartController class of the controller package, this controls the starting screen of the game.
*@author Marco Pérez.
*@version 16.09.2018
*/

import java.net.URL;
import javafx.util.Duration;
import model.Game;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import view.Main;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

public class StartController implements Initializable {
   
    @FXML
    private GridPane myGridPane;
  
   @FXML
   /**
	 * This method receives a mouse click on any part of the screen displayed. <br>
	 * <b>pre:</b> The Start screen and all its controls must be initialized. <br>
	 * <b>pos:</b> Loads the new fxml file, this being the Menu. <br>
	 * @param event  the mouse click action.
	 */
   private void mouseClicked(MouseEvent event) {
	   Alert alert = new Alert(AlertType.CONFIRMATION);
	   alert.setTitle("What do you want to do?");
	   alert.setHeaderText("Choose your option.");
	   alert.setContentText(null);

	   ButtonType buttonTypeOne = new ButtonType("Leaderboard.");
	   ButtonType buttonTypeTwo = new ButtonType("Select a player.");
	   ButtonType buttonTypeThree = new ButtonType("Create your player.");
	   ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	   alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
	   Optional<ButtonType> result = alert.showAndWait();
	   if (result.get() == buttonTypeOne){
		   try {
			   FXMLLoader loader = new FXMLLoader();
			   loader.setLocation(Main.class.getResource("/view/Leaderboard.fxml"));
			   Parent root = (Parent) loader.load();
			   Scene scene = new Scene(root);
			   LeaderboardController controller = (LeaderboardController) loader.getController();
			   controller.setGame(new Game(null), false);
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			   stage.setScene(scene);
			   stage.setResizable(false);
			   stage.show();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   } else if (result.get() == buttonTypeTwo) {
		   try {
			   FXMLLoader loader = new FXMLLoader();
			   loader.setLocation(Main.class.getResource("/view/SelectPlayer.fxml"));
			   Parent root = (Parent) loader.load();
			   Scene scene = new Scene(root);
			   SelectPlayerController controller = (SelectPlayerController) loader.getController();
			   controller.setGame(new Game(null));
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			   stage.setScene(scene);
			   stage.setResizable(false);
			   stage.show();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   } else if (result.get() == buttonTypeThree) {
		   try {
			   FXMLLoader loader = new FXMLLoader();
			   loader.setLocation(Main.class.getResource("/view/CreatePlayer.fxml"));
			   Parent root = (Parent) loader.load();
			   Scene scene = new Scene(root);
			   SelectPlayerController controller = (SelectPlayerController) loader.getController();
			   controller.setGame(new Game(null));
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			   stage.setScene(scene);
			   stage.setResizable(false);
			   stage.show();
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
	   } else {
	       alert.close();
	   }
	   
   }
	
   private void fadeInAndOut(Label label) {
	   FadeTransition ft = new FadeTransition(Duration.millis(1300), label);
	   ft.setFromValue(0);
	   ft.setToValue(1);
	   ft.setCycleCount(Animation.INDEFINITE);
	   ft.setAutoReverse(true);
	   ft.play();
   }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Label label = new Label("Click anywhere to start");
		myGridPane.add(label, 0, 1);
		fadeInAndOut(label);
	}
}
