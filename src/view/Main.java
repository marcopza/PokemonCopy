package view;

/**
 * Main class of the project. 
 * @author Marco Pérez. A00351452.
 * @version 19.09.2018
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Start.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
