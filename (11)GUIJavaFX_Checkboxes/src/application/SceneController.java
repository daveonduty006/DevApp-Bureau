package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneController {
	
	@FXML
	private CheckBox myCheckBox;
	@FXML
	private Label myLabel;
	@FXML
	private ImageView myImageView;
	
	Image myImage1 = new Image(getClass().getResourceAsStream("lampOff.png"));
	Image myImage2 = new Image(getClass().getResourceAsStream("lampOn.png"));
	
	public void change(ActionEvent event) {
		if(myCheckBox.isSelected()) {
			myLabel.setText("ON");
			myImageView.setImage(myImage2);
		}
		else {
			myLabel.setText("OFF");
			myImageView.setImage(myImage1);
		}
	}

}
