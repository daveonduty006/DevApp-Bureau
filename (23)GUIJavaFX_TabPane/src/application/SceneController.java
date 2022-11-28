package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

import javafx.scene.control.Tab;

import javafx.scene.control.TabPane;

public class SceneController implements Initializable {
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab tab1;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private TextField txtFld1;
	@FXML
	private TextField txtFld2;
	@FXML
	private Button btnSum;
	@FXML
	private Tab tab2;
	@FXML
	private TextArea txtArea;
	@FXML
	private Button btnReturn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void btnSumHandler(ActionEvent event) {
		// Convert a String value to a double value
		double num1 = Double.parseDouble(txtFld1.getText());
		double num2 = Double.parseDouble(txtFld2.getText());
		// Calculate the sum of num1 and num2
		double sum = num1 + num2;
		// Convert back to a String value from a double value into tab2's TextArea
		txtArea.appendText(String.valueOf(sum));
		// We can select a tab by using the index of the tab
		// The second tab has an index of 1 (0 for tab1, 1 for tab2)
		// As an alternative we can use its fxid (tab2)
		tabPane.getSelectionModel().select(tab2);
	}

	@FXML
	public void btnReturnHandler(ActionEvent event) {
		// Return to tab1 from tab2
		tabPane.getSelectionModel().select(tab1);
	}

}
