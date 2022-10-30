package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Scene2Controller {

	@FXML
	Label usernameLbl;
	
	public void displayUsername(String username) {
		usernameLbl.setText("Hello: "+username);
	}
}
