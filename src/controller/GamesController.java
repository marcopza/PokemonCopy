package controller;

/**
*GamesController class of the controller package, this class controls both the Catch and Throw scenarios.
*@author Marco Pérez.
*@version 16.09.2018
*/

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Game;
import model.Pokemon;
import view.Main;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;

public class GamesController implements Initializable{

	public static final String POKEBALL = "file:images/Pokeball.png";
    @FXML
    private ImageView catchImage;
    @FXML
    private ImageView throwImage;
    private Pokemon pokemonSelected;
    private TranslateTransition transition;
    private Game game; 
    private long begin;
    private long end;
    
    public void defineGameSelected(Game game) {
    	this.game = game;
    }
    
    public void definePokemonSelected(String namePokemonSelected) {
    	game.setPokemonSelected(namePokemonSelected);
    }
    
    public void startAnimation( char gameType) {
    	Random rand = new Random();
    	int velocidad = rand.nextInt(2501) + 1500;
    	if(gameType == 'C') {
    		catchImage.setImage(game.getPokemonSelected().getImage());
    		transition = new TranslateTransition(Duration.millis(velocidad), catchImage);
        	transition.setToX(477);
        	transition.play();
        	begin = System.currentTimeMillis();
        	transition.setOnFinished(event -> {
        		transition = new TranslateTransition(Duration.millis(100), catchImage);
        		transition.setToX(532);
        		transition.play();
        		pokemonSelected.setCatchable(false);
        		transition.setOnFinished(finalMovement ->{
        			try {
            			FXMLLoader loader = new FXMLLoader();
            			loader.setLocation(Main.class.getResource("/view/Menu.fxml"));
            			Parent root = (Parent) loader.load();
            			SelectionController controller = (SelectionController) loader.getController();
            			controller.setGame(new Game(game.getPlayerSelected()));
            			Scene scene = new Scene(root);
            			Stage stage = (Stage) catchImage.getScene().getWindow();
            			stage.setScene(scene);
            			stage.show();
            		}
            		catch(Exception e) {
            			e.printStackTrace();
            		}
        			});
        		});
    	}
    	else {
    		throwImage.setImage(new Image(POKEBALL));
    	}
    }
    
    @FXML
    private void imageCatchClicked(MouseEvent event) {
    			transition.stop();
    			catchImage.setImage(new Image(POKEBALL));
    			end = System.currentTimeMillis();
    			game.setPlayerScoreCatch((long)transition.getDuration().toMillis(), end-begin);
    			TextInputDialog dialog = new TextInputDialog();
    			dialog.setTitle("You've caught the pokemon!");
    			dialog.setHeaderText(null);
    			dialog.setContentText("Enter your name:");
    			Optional<String> name = dialog.showAndWait();
    			PrintWriter writer;
				try {
					FileWriter fw = new FileWriter("Names/names.txt", true);
					writer = new PrintWriter(fw);
					writer.println(name.get());
	    			writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("/view/Menu.fxml"));
					Parent root = (Parent) loader.load();
					SelectionController controller = (SelectionController) loader.getController();
        			controller.setGame(new Game(game.getPlayerSelected()));
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				}
				catch(Exception e) {
					e.printStackTrace();
    		}
    }
    
    @FXML
    private void imageThrowClicked(MouseEvent event) {
    	if(transition == null) {
    		throwImage.setImage(game.getPokemonSelected().getImage());
        	Random rand = new Random();
        	int velocidad = rand.nextInt(2501) + 1500;
           	transition = new TranslateTransition(Duration.millis(velocidad), throwImage);
            game.setPlayerScoreThrow((int) transition.getDuration().toMillis());
           	transition.setToX(470);
           	transition.play();
           	transition.setOnFinished(onFinished -> {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Your pokemon has finished!");
        		alert.setHeaderText(null);
        		alert.setContentText("Your pokemon ran: "+ transition.getToX() + " meters!");
        		alert.show();
        		try {
    				FXMLLoader loader = new FXMLLoader();
    				loader.setLocation(Main.class.getResource("/view/Menu.fxml"));
    				Parent root = (Parent) loader.load();
    				SelectionController controller = (SelectionController) loader.getController();
        			controller.setGame(new Game(game.getPlayerSelected()));
    				Scene scene = new Scene(root);
    				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    				stage.setScene(scene);
    				stage.show();
    			}
    			catch(Exception e) {
    				e.printStackTrace();
    			}
        	});
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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}
