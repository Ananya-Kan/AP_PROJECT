package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controllers.ObstacleController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        //ObstacleController obstacleController = new ObstacleController();

        //Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        //primaryStage.setHeight(700);primaryStage.setWidth(700);

        MainMenu menu = new MainMenu(primaryStage);
        GamePlay game = new GamePlay(primaryStage);
        Scene mainPage = menu.scene;
        Scene gamePlay = game.scene;

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(mainPage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

