package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class SceneController implements Initializable {
	@FXML
	private ImageView myImage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		// translate 
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(myImage);
		
		translate.setDuration(Duration.millis(1000));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		
		translate.setByX(250);
		translate.setByY(-250);
		
		translate.setAutoReverse(true);
		
		translate.play();
		*/
		
		// rotate
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(myImage);
		
		rotate.setDuration(Duration.millis(500));
		rotate.setCycleCount(RotateTransition.INDEFINITE);
		
		rotate.setInterpolator(Interpolator.LINEAR);
		
		rotate.setByAngle(-360);
		rotate.setAxis(Rotate.Z_AXIS);
		
		rotate.play();
		
		/*
		// fade
		FadeTransition fade = new FadeTransition();
		fade.setNode(myImage);
		
		fade.setDuration(Duration.millis(1000));
		fade.setCycleCount(RotateTransition.INDEFINITE);
		
		fade.setInterpolator(Interpolator.LINEAR);
		
		fade.setFromValue(1);
		fade.setToValue(0);
		
		fade.play();
		*/
		/*
		// scale
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(myImage);
		
		scale.setDuration(Duration.millis(1000));
		scale.setCycleCount(RotateTransition.INDEFINITE);
		
		scale.setInterpolator(Interpolator.LINEAR);
		
		scale.setByX(2.0);
		scale.setByY(2.0);
		scale.setAutoReverse(true);
		
		scale.play();
		*/
	}

}
