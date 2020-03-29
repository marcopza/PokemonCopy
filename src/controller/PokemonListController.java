package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Exception.PokemonNotFoundException;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;
import model.Pokemon;
import view.Main;

public class PokemonListController implements Initializable{

	@FXML
    private TextField nameSelectionTextField;
    @FXML
    private Pane infoPane;
    private Game game;
    private ListView lv;

    public void setGame(Game game) {
    	this.game = game;
    	lv = new ListView();
    	infoPane.getChildren().add(lv);
		lv.setPrefWidth(infoPane.getPrefWidth());
		lv.setPrefHeight(infoPane.getPrefHeight());
		for(Pokemon pokemon : (ArrayList<Pokemon>)game.organizePokemons()) {
			lv.getItems().add(pokemon.toString());
		}
    }
    
    @FXML
    private void backButtonPressed(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(Main.class.getResource("/view/Menu.fxml"));
		    Parent root = (Parent) loader.load();
		    Scene scene = new Scene(root);
		    SelectionController controller = (SelectionController) loader.getController();
		    controller.setGame(game);
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
    private void mouseClickedSelect(MouseEvent event) {
    	if(!nameSelectionTextField.getText().equals("Nicnkame...")) {
			setGame(game);
		}
		nameSelectionTextField.setText("");
    }

    @FXML
    private void searchButtonPressed(ActionEvent event) {
    	lv.getItems().clear();
    	try {
    		lv.getItems().add(game.getPokemonByName(nameSelectionTextField.getText()));
    	}catch(PokemonNotFoundException e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Welcome!");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			setGame(game);
			}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

}
