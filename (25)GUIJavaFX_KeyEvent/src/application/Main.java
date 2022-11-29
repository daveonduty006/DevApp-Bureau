package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
			Parent root = loader.load();
			SceneController controller = loader.getController();
			Scene scene = new Scene(root);
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() { // Anonymous event handler
				@Override
				public void handle(KeyEvent event) {
					switch(event.getCode()) {
						case W:
							controller.moveUp();
							break;
						case S:
							controller.moveDown();
							break;
						case A:
							controller.moveLeft();
							break;
						case D:
							controller.moveRight();
							break;
						default:
							break;
					}
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
