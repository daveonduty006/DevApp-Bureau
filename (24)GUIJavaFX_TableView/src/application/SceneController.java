package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class SceneController implements Initializable {
	
	@FXML
	private TableView<User> tblView;
	@FXML
	private TableColumn<User,String> tblColName;
	@FXML
	private TableColumn<User,Integer> tblColAge;
	@FXML
	private TableColumn<User,String> tblColAnimal;
	
	ObservableList<User> list = FXCollections.observableArrayList(
			new User("Daniel",20,"Dog"),
			new User("Anna",21,"Cat"),
			new User("Mark",22,"Bird")
	);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tblColName.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
		tblColAge.setCellValueFactory(new PropertyValueFactory<User,Integer>("age"));
		tblColAnimal.setCellValueFactory(new PropertyValueFactory<User,String>("animal"));
		
		tblView.setItems(list);
	}

}
