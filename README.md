# AP_PROJECT
// THIS CODE IS FOR MAKING MANY SCENES AND LINKING THEM TOGETHER
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("My First JavaFX GUI");

//Scene 1
        Label label1= new Label("This is the first scene");
        Button button1= new Button("Go to scene 2");
        button1.setOnAction(e -> primaryStage.setScene(scene2));
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1= new Scene(layout1, 300, 250);

//Scene 2
        Label label2= new Label("This is the second scene");
        Button button2= new Button("Go to scene 1");
        button2.setOnAction(e -> primaryStage.setScene(scene1));
        VBox layout2= new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2= new Scene(layout2,300,250);


        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
// THIS CODE IS FOR MAKING OBSTACLES
package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing an arc
        Arc arc = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();

        //Setting the properties of the arc
        arc.setFill(Color.WHITE); arc.setStroke(Color.RED); arc.setStrokeWidth(20); //arc.setStrokeType(StrokeType.INSIDE);
        arc2.setFill(Color.WHITE); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(20);// arc2.setStrokeType(StrokeType.INSIDE);
        arc3.setFill(Color.WHITE); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(20); //arc3.setStrokeType(StrokeType.INSIDE);
        arc4.setFill(Color.WHITE); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(20);// arc4.setStrokeType(StrokeType.INSIDE);
//        arc2.setFill(Color.GREY);
//        arc3.setFill(Color.GREY);
//        arc4.setFill(Color.GREY);
        arc.setCenterX(300.0f);
        arc3.setCenterX(300.0f);
        arc2.setCenterX(300.0f);
        arc4.setCenterX(300.0f);
        arc.setCenterY(150.0f);
        arc2.setCenterY(150.0f);
        arc3.setCenterY(150.0f);
        arc4.setCenterY(150.0f);
        arc.setRadiusX(90.0f);
        arc2.setRadiusX(90.0f);
        arc3.setRadiusX(90.0f);
        arc4.setRadiusX(90.0f);
        arc.setRadiusY(90.0f); arc2.setRadiusY(90.0f);arc3.setRadiusY(90.0f);arc4.setRadiusY(90.0f);
        arc.setStartAngle(0.0f); arc2.setStartAngle(90.0f); arc3.setStartAngle(180.0f); arc4.setStartAngle(270.0f);
        arc.setLength(90.0f); arc2.setLength(90.0f); arc3.setLength(90.0f); arc4.setLength(90.0f);

        //Setting the type of the arc
        arc.setType(ArcType.OPEN);arc2.setType(ArcType.OPEN);arc3.setType(ArcType.OPEN);arc4.setType(ArcType.OPEN);

        //Creating a Group object
        Group root = new Group(arc,arc2,arc3,arc4);//Group root2 = new Group(arc2);Group root3 = new Group(arc3);Group root4 = new Group(arc4);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);



        //Setting title to the Stage
        stage.setTitle("Drawing an Arc");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
