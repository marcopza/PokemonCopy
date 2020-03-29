package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import view.Main;

public class LeaderboardController implements Initializable{

	@FXML
    private ScrollPane leaderboardScrollPane;
	@FXML
    private Pane leaderboardPane;
	private Game game;
	private boolean inGame;
	
	public void setGame(Game game, boolean inGame) {
		this.game = game;
		this.inGame = inGame;
		ArrayList<Player> players = game.organizeNaturalOrder();
		int i = 0;
		ListView lv = new ListView();
		leaderboardPane.getChildren().add(lv);
		lv.setPrefHeight(leaderboardPane.getPrefHeight());
		lv.setPrefWidth(leaderboardPane.getPrefWidth());
		for(Player player : players) {
			lv.getItems().add(player.toString());
			}
	}
	
	@FXML
	public void backButtonPressed(ActionEvent event) {
		if(inGame) {
			try {
				FXMLLoader loader = new FXMLLoader();
			    loader.setLocation(Main.class.getResource("/view/Menu.fxml"));
			    Parent root = (Parent) loader.load();
			    SelectionController controller = (SelectionController) loader.getController();
				controller.setGame(new Game(game.getPlayerSelected()));
			    Scene scene = new Scene(root);
			    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			    stage.setScene(scene);
			    stage.setResizable(false);
			    stage.show();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FXMLLoader loader = new FXMLLoader();
			    loader.setLocation(Main.class.getResource("/view/Start.fxml"));
			    Parent root = (Parent) loader.load();
			    Scene scene = new Scene(root);
			    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			    stage.setScene(scene);
			    stage.setResizable(false);
			    stage.show();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
