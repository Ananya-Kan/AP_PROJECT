package sample.controllers;

import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ObstacleController{
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setRotate()
//    }

    public void setRotate(Group g,int angle, int rate) {
        RotateTransition rotateTransition = new RotateTransition(Duration.INDEFINITE,g);
        //rotateTransition.setAutoReverse(reverse);
        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(rate);
        rotateTransition.play();
    }
}
