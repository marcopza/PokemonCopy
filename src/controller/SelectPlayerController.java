package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Exception.PlayerAlreadyExistsException;
import Exception.PlayerNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.Game;
import model.Player;
import view.Main;

public class SelectPlayerController implements Initializable{

	@FXML
    private Pane infoPane;
	@FXML
    private TextField nicknameTextField;
	@FXML
    private TextField nicknameSelectionTextField;
	private Game game;
	ListView lv;
	
	public void setGame(Game game) {
		this.game = game;
		if(infoPane != null) {
			ArrayList<Player> players = game.organizePartialOrder();
			int i = 0;
			lv = new ListView();
			lv.setOnMouseClicked(event -> {
		        String selection = (String)lv.getSelectionModel().getSelectedItem();
		        String[] info = selection.split("\t");
		        game.setPlayerByNickname(info[0]);
		        try {
	    			FXMLLoader loader = new FXMLLoader();
	    			loader.setLocation(Main.class.getResource("/view/Menu.fxml"));
	    			Parent root = (Parent) loader.load();
	    			SelectionController controller = (SelectionController)loader.getController();
	    			controller.setGame(game);
	    			Scene scene = new Scene(root);
	    			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    			stage.setOnCloseRequest(e -> controller.shutdown());
	    			stage.setScene(scene);
	    			stage.show();
	    		}catch(Exception e) {
	    			e.printStackTrace();
	    		}
		    });
			infoPane.getChildren().add(lv);
			lv.setPrefWidth(infoPane.getPrefWidth());
			lv.setPrefHeight(infoPane.getPrefHeight());
			for(Player player : players) {
				lv.getItems().add(player.toString());
			}
		}
	}
	
	@FXML
    private void mouseClickedSelect(MouseEvent event) {
		if(!nicknameSelectionTextField.getText().equals("Nicnkame...")) {
			setGame(game);
		}
		nicknameSelectionTextField.setText("");
    }
	
    @FXML
    private void mouseClicked(MouseEvent event) {
    	nicknameTextField.setText("");
    }

    @FXML
    private void savePlayerButtonPressed(ActionEvent event){
    	Player player = new Player(nicknameTextField.getText(), 0);
		game.setPlayerSelectedObject(player);
		try {
			game.createPlayer(player);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Menu.fxml"));
			Parent root = (Parent) loader.load();
			SelectionController controller = (SelectionController) loader.getController();
			controller.setGame(game);
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setOnCloseRequest(e -> controller.shutdown());
			stage.setScene(scene);
			stage.show();
		}catch (PlayerAlreadyExistsException e1) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Welcome!");
			alert.setHeaderText(null);
			alert.setContentText(e1.getMessage());
			alert.showAndWait();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
    @FXML
    private void searchButtonPressed(ActionEvent event) {
    	lv.getItems().clear();
    	try {
    		lv.getItems().add(game.getPlayerByName(nicknameSelectionTextField.getText()));
    	}catch(PlayerNotFoundException e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Welcome!");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			setGame(game);
			}
    }
    
    @FXML
    private void backButtonPressed(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("Start.fxml"));
    		Parent root = (Parent) loader.load();
    		Scene scene = new Scene(root);
    		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    		stage.setScene(scene);
    		stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
