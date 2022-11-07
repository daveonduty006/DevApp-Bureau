package application;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class SceneController {
	
	@FXML
	private DatePicker myDatePicker;
	@FXML
	private Label myLabel;
	
	public void getDate(ActionEvent event) {
		LocalDate myDate= myDatePicker.getValue();
		//String myFormattedDate= myDate.format(DateTimeFormatter.ofPattern("MM-d-uuuu")); // MMM for name of the month
		myLabel.setText(myDate.toString());
	}

}