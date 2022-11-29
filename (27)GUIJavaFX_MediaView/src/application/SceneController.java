package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class SceneController implements Initializable {
	@FXML
	private MediaView myMediaView;
	@FXML
	private Button btnPlay;
	@FXML
	private Button btnPause;
	@FXML
	private Button btnReset;
	
	private File file;
	private Media media;
	private MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		file = new File("mariokart.mp4");
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		myMediaView.setMediaPlayer(mediaPlayer);
	}

	@FXML
	public void btnPlayHandler(ActionEvent event) {
		mediaPlayer.play();
	}
	
	@FXML
	public void btnPauseHandler(ActionEvent event) {
		mediaPlayer.pause();
	}
	
	@FXML
	public void btnResetHandler(ActionEvent event) {
		if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
			mediaPlayer.seek(Duration.seconds(0.0));	
		}
	}
	
}
