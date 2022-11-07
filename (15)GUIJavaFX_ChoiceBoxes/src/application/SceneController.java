package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SceneController implements Initializable {
	
	// A ChoiceBox is JavaFX's version of a Dropdown Menu
	
	@FXML
	private Label myLabel;
	@FXML
	private ChoiceBox<String> myChoiceBox;
	
	private String[] food = {"pizza", "sushi", "ramen"};

	// Because SceneBuilder doesn't allow us to populate our ChoiceBox directly into our XML,
	// we will need to do it here.
	// When we need to initialize a controller after its root element has already been processed,
	// we must do so with the Initializable interface. 
	// Since we still need to populate our ChoiceBox with data and link a method to the ChoiceBox, 
	// we will use it here.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myChoiceBox.getItems().addAll(food);
		myChoiceBox.setOnAction(this::getFood); // this::getFood is a method operator linking the getFood method to
		                                        // the element (myChoiceBox)
	}
	
	public void getFood(ActionEvent event) {
		String myFood = myChoiceBox.getValue();
		myLabel.setText(myFood);
	}
	
	

}
