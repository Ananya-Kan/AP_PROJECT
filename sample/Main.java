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
    public void start(Stage primaryStage) throws Exception{
        // TODO Auto-generated method stub
        //ObstacleController obstacleController = new ObstacleController();

        //Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));

        Pane root2 = new Pane();
        Group ring = createRing(150d,150d,120d);
        Group cross = createCross(110,150,50);

        Timeline timeline1 = rotatingObstacle(ring,150.0,150.0,5,360d);
        Timeline timeline2 = rotatingObstacle(cross,110,150,3,360);
        timeline1.play();
        timeline2.play();

        root2.getChildren().addAll(ring,cross);
        Scene gamePlay = new Scene(root2);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(gamePlay);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Group createRing(double centerX, double centerY,double radius){
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();
        //properties of the arcs
        arc1.setFill(Color.WHITE); arc1.setStroke(Color.RED); arc1.setStrokeWidth(20);
        arc2.setFill(Color.WHITE); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(20);
        arc3.setFill(Color.WHITE); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(20);
        arc4.setFill(Color.WHITE); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(20);

        //Setting the dimensions of the arcs (centre,radius,length,start angle)
        arc1.setCenterX(centerX);arc3.setCenterX(centerX); arc2.setCenterX(centerX); arc4.setCenterX(centerX);
        arc1.setCenterY(centerY); arc2.setCenterY(centerY); arc3.setCenterY(centerY); arc4.setCenterY(centerY);
        arc1.setRadiusX(radius); arc2.setRadiusX(radius); arc3.setRadiusX(radius); arc4.setRadiusX(radius);
        arc1.setRadiusY(radius); arc2.setRadiusY(radius);arc3.setRadiusY(radius);arc4.setRadiusY(radius);
        arc1.setStartAngle(0.0f); arc2.setStartAngle(90.0f); arc3.setStartAngle(180.0f); arc4.setStartAngle(270.0f);
        arc1.setLength(90.0f); arc2.setLength(90.0f); arc3.setLength(90.0f); arc4.setLength(90.0f);

        //Setting the type of the arc
        arc1.setType(ArcType.OPEN);arc2.setType(ArcType.OPEN);arc3.setType(ArcType.OPEN);arc4.setType(ArcType.OPEN);

        //Creating a Group object
        Group root = new Group(arc1,arc2,arc3,arc4);
        return root;
    }

    public Group createCross(double centerX, double centerY, double armLength){
        Line arm1 = new Line();
        Line arm2 = new Line();
        Line arm3 = new Line();
        Line arm4 = new Line();
        //Styling the arms
        arm1.setStroke(Color.RED);arm1.setStrokeWidth(10);arm1.setStrokeType(StrokeType.CENTERED);arm1.setStrokeLineCap(StrokeLineCap.ROUND);
        arm2.setStroke(Color.DEEPPINK);arm2.setStrokeWidth(10);arm2.setStrokeType(StrokeType.CENTERED);arm2.setStrokeLineCap(StrokeLineCap.ROUND);
        arm3.setStroke(Color.YELLOW);arm3.setStrokeWidth(10);arm3.setStrokeType(StrokeType.CENTERED);arm3.setStrokeLineCap(StrokeLineCap.ROUND);
        arm4.setStroke(Color.GREEN);arm4.setStrokeWidth(10);arm4.setStrokeType(StrokeType.CENTERED);arm4.setStrokeLineCap(StrokeLineCap.ROUND);

        //positioning the arms
        arm1.setStartX(centerX);arm1.setStartY(centerY);arm1.setEndX(centerX);arm1.setEndY(centerY+armLength);
        arm2.setStartX(centerX);arm2.setStartY(centerY);arm2.setEndX(centerX);arm2.setEndY(centerY-armLength);
        arm3.setStartX(centerX);arm3.setStartY(centerY);arm3.setEndX(centerX-armLength);arm3.setEndY(centerY);
        arm4.setStartX(centerX);arm4.setStartY(centerY);arm4.setEndX(centerX+armLength);arm4.setEndY(centerY);

        Group cross = new Group(arm1,arm2,arm3,arm4);
        return cross;
    }

    public Timeline rotatingObstacle(Group obs, double pivotX, double pivotY, double timePeriod, double angle){
        Rotate rotatingObj = new Rotate(angle,pivotX,pivotY);
        obs.getTransforms().add(rotatingObj);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(rotatingObj.angleProperty(),0d)),
                new KeyFrame(Duration.seconds(timePeriod),new KeyValue(rotatingObj.angleProperty(),angle)));
        timeline.setCycleCount(Animation.INDEFINITE);

        return timeline;
    }


}
