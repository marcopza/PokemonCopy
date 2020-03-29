package controller;

/**
*Selection Controller class of the controller package, this controls both the Menu and the Selection Menu.
*@author Marco Pérez.
*@version 16.09.2018
*/

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;
import javafx.scene.Node;
import view.Main;

public class SelectionController implements Initializable{

	public static final char CATCH ='C';
	public static final char THROW ='T';
	
    @FXML
    private ImageView charmeleonImageView;
    @FXML
    private ImageView cyndaquilImageView;
    @FXML
    private ImageView lucarioImageView;
    @FXML
    private ImageView quilavaImageView;
    @FXML
    private ImageView totodileImageView;
    @FXML
    private ImageView vulpixImageView;
    @FXML
    private Pane mainGamePane;
    @FXML
    private ImageView pokemonImageView;
    @FXML
    private Label labelScore;
    private char gameType;
    private String namePokemonSelected;
    private Game game;
    
    private void setGameType(char gameType) {
    	this.gameType = gameType;
    }
    
    public void setGame(Game game) {
    	this.game = game;
    	if(labelScore != null) {
    		displayScore();
    	}
    }
    
    public void displayScore() {
    	labelScore.setText(""+game.getPlayerSelected().getScore());
    }
    
    @FXML
    private void pokemonSelected(MouseEvent event) {
    	ImageView image = (ImageView) event.getSource();
		if(image.equals(charmeleonImageView)) {
			namePokemonSelected = "Charmeleon";
    	}
    	if(image.equals(cyndaquilImageView)) {
    		namePokemonSelected = "Cyndaquil";
    	}
    	if(image.equals(lucarioImageView)) {
    		namePokemonSelected = "Lucario";
    	}
    	if(image.equals(quilavaImageView)) {
    		namePokemonSelected = "Quilava";
    	}
    	if(image.equals(totodileImageView)) {
    		namePokemonSelected = "Totodile";
    	}
    	if(image.equals(vulpixImageView)) {
    		namePokemonSelected = "Vulpix";
    	}
    	if(gameType == CATCH) {
    		try {
        		FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(Main.class.getResource("/view/ArenaCatch.fxml"));
        		Parent root = (Parent) loader.load();
        		Scene scene = new Scene(root);
        		GamesController controller = loader.getController(); 
        		controller.defineGameSelected(game);
        		controller.definePokemonSelected(namePokemonSelected);
        		controller.startAnimation(gameType);
        		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage.setOnCloseRequest(e -> controller.shutdown());
        		stage.setScene(scene);
        		stage.show();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    	else {
    		try {
        		FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(Main.class.getResource("/view/ArenaThrow.fxml"));
        		Parent root = (Parent) loader.load();
        		Scene scene = new Scene(root);
        		GamesController controller = loader.getController(); 
        		controller.defineGameSelected(game);
        		controller.definePokemonSelected(namePokemonSelected);
        		controller.startAnimation(gameType);
        		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage.setOnCloseRequest(e -> controller.shutdown());
        		stage.setScene(scene);
        		stage.show();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    }
	
    @FXML
    private void leaveButtonPressed(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Wait!");
    	alert.setHeaderText(null);
    	alert.setContentText("Do you want to save your progress?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		game.savePlayersInfo(game.getPlayerSelected());
        	try {
        		FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(Main.class.getResource("/view/Start.fxml"));
        		Parent root = (Parent) loader.load();
        		Scene scene = new Scene(root);
        		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage.setScene(scene);
        		stage.show();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
    	} else {
    		try {
        		FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(Main.class.getResource("/view/Start.fxml"));
        		Parent root = (Parent) loader.load();
        		Scene scene = new Scene(root);
        		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        		stage.setScene(scene);
        		stage.show();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
    	}
    	
    }

    @FXML
    private void throwButtonPressed(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("/view/SelectionMenu.fxml"));
    		Parent root = (Parent) loader.load();
    		Scene scene = new Scene(root);
    		SelectionController controller = (SelectionController)loader.getController();
    		controller.setGameType('T');
    		controller.setGame(game);
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		stage.setOnCloseRequest(e -> controller.shutdown());
    		stage.setScene(scene);
    		stage.show();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
   
    @FXML
    private void catchButtonPressed(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("/view/SelectionMenu.fxml"));
    		Parent root = (Parent) loader.load();
    		Scene scene = new Scene(root);
    		SelectionController controller = loader.getController();
    		controller.setGameType('C');
    		controller.setGame(game);
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		stage.setOnCloseRequest(e -> controller.shutdown());
    		stage.setScene(scene);
    		stage.show();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    private void leaderboardButtonPressed(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
			   loader.setLocation(Main.class.getResource("/view/Leaderboard.fxml"));
			   Parent root = (Parent) loader.load();
			   Scene scene = new Scene(root);
			   LeaderboardController controller = (LeaderboardController) loader.getController();
			   controller.setGame(new Game(game.getPlayerSelected()), true);
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			   stage.setScene(scene);
			   stage.setResizable(false);
			   stage.show();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    private void pokemonsButtonPressed(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
			   loader.setLocation(Main.class.getResource("/view/Pokemons.fxml"));
			   Parent root = (Parent) loader.load();
			   Scene scene = new Scene(root);
			   PokemonListController controller = (PokemonListController) loader.getController();
			   controller.setGame(new Game(game.getPlayerSelected()));
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			   stage.setScene(scene);
			   stage.setResizable(false);
			   stage.show();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void shutdown() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Wait!");
    	alert.setHeaderText(null);
    	alert.setContentText("Do you want to save your progress?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		game.savePlayersInfo(game.getPlayerSelected());
        	Platform.exit();
    	} else {
    		Platform.exit();
    	}
    }
    
    @FXML
    private void saveButtonPressed(ActionEvent event) {
    	game.savePlayersInfo(game.getPlayerSelected());
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Saving");
    	alert.setHeaderText(null);
    	alert.setContentText("Your progress has been saved! c: ");
    	alert.showAndWait();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
