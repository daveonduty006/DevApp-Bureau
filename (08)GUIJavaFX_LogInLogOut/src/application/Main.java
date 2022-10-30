package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();  // prevent the logout function to be called if user press Cancel
				logout(primaryStage);
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// we are copying our logout method from our Controller class to allow logout 
	// to be called if X is pressed (without the stage instantiation here)
	public void logout(Stage stage) {
		
		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("You successfully logged out!");
			stage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
