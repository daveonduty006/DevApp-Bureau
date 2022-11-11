package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class SceneController implements Initializable {
	
	@FXML
	private TreeView<String> myTreeView;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TreeItem<String> rootItem = new TreeItem<>("Files", new ImageView(new Image("folderIcon.png")));
		
		TreeItem<String> branchItem1 = new TreeItem<>("Pictures", new ImageView(new Image("pictureIcon.png")));
		TreeItem<String> branchItem2 = new TreeItem<>("Videos", new ImageView(new Image("videoIcon.png")));
		TreeItem<String> branchItem3 = new TreeItem<>("Music", new ImageView(new Image("musicIcon.png")));
		
		TreeItem<String> leafItem1 = new TreeItem<>("picture1");
		TreeItem<String> leafItem2 = new TreeItem<>("picture2");
		TreeItem<String> leafItem3 = new TreeItem<>("video1");
		TreeItem<String> leafItem4 = new TreeItem<>("video2");
		TreeItem<String> leafItem5 = new TreeItem<>("music1");
		TreeItem<String> leafItem6 = new TreeItem<>("music2");
		
		branchItem1.getChildren().addAll(leafItem1, leafItem2);
		branchItem2.getChildren().addAll(leafItem3, leafItem4);
		branchItem3.getChildren().addAll(leafItem5, leafItem6);
		rootItem.getChildren().addAll(branchItem1, branchItem2, branchItem3);
		//myTreeView.setShowRoot(false); // hide the rootItem selectable
		myTreeView.setRoot(rootItem);
		
	}
	
	public void selectItem() {
		TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
		if(item != null) {
			System.out.println(item);
		}
	}

}
