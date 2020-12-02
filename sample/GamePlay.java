package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;

public class GamePlay {
    public Stage stage;
    public Scene scene;

    public GamePlay(Stage stage) throws IOException{
        this.stage = stage;
        Pane root2 = new Pane();
        root2.setStyle("-fx-background-color: #000");
        scene = new Scene(root2,800,800, Color.BLACK);

        //creating the ball
        Circle ball = new Circle();
        //Setting the dimensions of the ball
        ball.setCenterX(400);ball.setCenterY(754); ball.setRadius(12);
        //Setting the colour
        ball.setFill(Color.RED);
        //Now we have to make the ball translate
        Timeline timeline1 = translateBall(ball,0,60,1);
        timeline1.play();

        //Now, setting the obstacles
        Group ring = createRing(400,570,80 );
        Timeline timeline2 = rotatingObstacle(ring,400.0,570.0,3,360d);
        timeline2.play();

        Group cross = createCross(350,350,50);
        Timeline timeline3 = rotatingObstacle(cross,350,350,5,360);
        timeline3.play();

        Group ringComp = createRing(400,130,120);
        Group crossComp = createCross(360,130,50);
        Timeline timeline4 = rotatingObstacle(ringComp,400.0,130.0,3,360d);
        Timeline timeline5 = rotatingObstacle(crossComp,360,130,5,360);
        timeline4.play();
        timeline5.play();

        //putting the label of the no.of stars collected
        //loading the star image
        FileInputStream inputStream1 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\images\\starboy.png");
        Image img1 = new Image(inputStream1);
        ImageView starIcon = new ImageView(img1);
        starIcon.setX(600); starIcon.setY(30);
        starIcon.setFitWidth(100);starIcon.setFitHeight(100);
        inputStream1.close();
        //declaring the label
        Label starsCollected = new Label("0",starIcon);
        starsCollected.setFont(new Font(20));
        starsCollected.setTextFill(Color.WHITE);

        //adding the pause button
        FileInputStream inputStream2 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\images\\pause.jpg");
        Image img2 = new Image(inputStream2);
        ImageView pauseIcon = new ImageView(img2);
        pauseIcon.setX(718); pauseIcon.setY(18);
        pauseIcon.setFitWidth(60);pauseIcon.setFitHeight(60);
        inputStream2.close();

        //Adding color switches
        Group colorSwitch = createColorSwitch(400,450,15);
        //creating stars
        ImageView star = createStar(380,550,40,40);

        root2.getChildren().addAll(ball,ring,cross,ringComp,crossComp,starsCollected,pauseIcon,colorSwitch,star);
    }

    public Group createColorSwitch(double centerX, double centerY, double radius){
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();
        //properties of the arcs
        arc1.setFill(Color.RED); arc1.setStroke(Color.RED); arc1.setStrokeWidth(1);
        arc2.setFill(Color.DEEPPINK); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(1);
        arc3.setFill(Color.YELLOW); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(1);
        arc4.setFill(Color.GREEN); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(1);
        //Setting the dimensions of the arcs (centre,radius,length,start angle)
        arc1.setCenterX(centerX);arc3.setCenterX(centerX); arc2.setCenterX(centerX); arc4.setCenterX(centerX);
        arc1.setCenterY(centerY); arc2.setCenterY(centerY); arc3.setCenterY(centerY); arc4.setCenterY(centerY);
        arc1.setRadiusX(radius); arc2.setRadiusX(radius); arc3.setRadiusX(radius); arc4.setRadiusX(radius);
        arc1.setRadiusY(radius); arc2.setRadiusY(radius);arc3.setRadiusY(radius);arc4.setRadiusY(radius);
        arc1.setStartAngle(0.0f); arc2.setStartAngle(90.0f); arc3.setStartAngle(180.0f); arc4.setStartAngle(270.0f);
        arc1.setLength(90.0f); arc2.setLength(90.0f); arc3.setLength(90.0f); arc4.setLength(90.0f);
        //Setting the type of the arc
        arc1.setType(ArcType.ROUND);arc2.setType(ArcType.ROUND);arc3.setType(ArcType.ROUND);arc4.setType(ArcType.ROUND);

        Group colorSwitch = new Group(arc1,arc2,arc3,arc4);
        return colorSwitch;
    }

    public Group createRing(double centerX, double centerY, double radius){
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();
        //properties of the arcs
        arc1.setFill(Color.TRANSPARENT); arc1.setStroke(Color.RED); arc1.setStrokeWidth(20);
        arc2.setFill(Color.TRANSPARENT); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(20);
        arc3.setFill(Color.TRANSPARENT); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(20);
        arc4.setFill(Color.TRANSPARENT); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(20);

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

    public Timeline translateBall(Circle ball,double XDistance, double YDistance, double timePeriod){
        Translate translate = new Translate(XDistance,YDistance);
        ball.getTransforms().add(translate);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(translate.yProperty(),0d),new KeyValue(translate.xProperty(),0d)),
                new KeyFrame(Duration.seconds(timePeriod),new KeyValue(translate.yProperty(),-YDistance),new KeyValue(translate.xProperty(),XDistance)),
                new KeyFrame(Duration.seconds(2*timePeriod),new KeyValue(translate.yProperty(),0),new KeyValue(translate.xProperty(),0)));
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }

    public Timeline rotatingObstacle(Group obs, double pivotX, double pivotY, double timePeriod, double angle){
        Rotate rotatingObj = new Rotate(angle,pivotX,pivotY);
        obs.getTransforms().add(rotatingObj);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(rotatingObj.angleProperty(),0d)),
                new KeyFrame(Duration.seconds(timePeriod),new KeyValue(rotatingObj.angleProperty(),angle)));
        timeline.setCycleCount(Animation.INDEFINITE);

        return timeline;
    }

    public ImageView createStar(double posX,double posY,double width,double height) throws IOException{
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\images\\star.png");
        Image img = new Image(inputStream);
        ImageView star = new ImageView(img);
        star.setX(posX);star.setY(posY);
        star.setFitWidth(width);star.setFitHeight(height);
        inputStream.close();
        return star;
    }
}