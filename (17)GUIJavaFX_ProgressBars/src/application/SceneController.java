package application;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class SceneController implements Initializable {
	
	@FXML
	private ProgressBar myProgressBar;
	@FXML
	private Button myButton;
	@FXML
	private Label myLabel;
	
	// While ProgressBar in JavaFX works with double values (from 0 to 1),
	// in this case we want to display into myLabel integer values between 0 and 100.
	// However rounding double values previously increased by the user result in rounding errors.
	// The BigDecimal class gives its user complete control over rounding behavior
	BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myProgressBar.setStyle("-fx-accent: red;");  // CSS injection 
		
	}
	
	public void increaseProgress() {
		if(progress.doubleValue() < 1) {
			progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 0.1));
			myProgressBar.setProgress(progress.doubleValue());
			myLabel.setText(Integer.toString( (int)Math.round(progress.doubleValue() * 100)) + "%");
		}
	}
	
	

}
