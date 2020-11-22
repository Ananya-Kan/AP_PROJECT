package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainMenu {
    public Stage stage;
    public Scene scene;

    public MainMenu(Stage stage) throws IOException,FileNotFoundException {
        this.stage = stage;
        Pane root1 = new Pane();
        root1.setStyle("-fx-background-color: #000");

        //Adding all the images
        FileInputStream inputStream1 = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\help.png");
        Image img1 = new Image(inputStream1);
        ImageView helpIcon = new ImageView(img1);
        helpIcon.setX(21); helpIcon.setY(14);
        helpIcon.setFitWidth(91);helpIcon.setFitHeight(84);
        inputStream1.close();

        FileInputStream inputStream2 = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\settings.png");
        Image img2 = new Image(inputStream2);
        ImageView settingsIcon = new ImageView(img2);
        settingsIcon.setX(586); settingsIcon.setY(14);
        settingsIcon.setFitWidth(91);settingsIcon.setFitHeight(94);
        inputStream2.close();

        FileInputStream inputStream3 = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\Color_Switch.png");
        Image img3 = new Image(inputStream3);
        ImageView mainIcon = new ImageView(img3);
        mainIcon.setX(250); mainIcon.setY(52);
        mainIcon.setFitWidth(200);mainIcon.setFitHeight(84);
        inputStream3.close();

        FileInputStream inputStream4 = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\starboy.png");
        Image img4 = new Image(inputStream4);
        ImageView starIcon = new ImageView(img4);
        starIcon.setX(0); starIcon.setY(536);
        starIcon.setFitWidth(150);starIcon.setFitHeight(150);
        inputStream4.close();

        FileInputStream inputStream5 = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\exit.jpg");
        Image img5 = new Image(inputStream5);
        ImageView exitIcon = new ImageView(img5);
        exitIcon.setX(521); exitIcon.setY(583);
        exitIcon.setFitWidth(158);exitIcon.setFitHeight(72);
        inputStream5.close();

        FileInputStream inputStream6 = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\play.jpg");
        Image img6 = new Image(inputStream6);
        ImageView playIcon = new ImageView(img6);
        playIcon.setX(320); playIcon.setY(297);
        playIcon.setFitWidth(60);playIcon.setFitHeight(60);
        inputStream6.close();

        //Adding the buttons
        Button btn1 = new Button();
        Button btn2 = new Button();

        btn1.setText("NEW GAME");btn2.setText("LOAD SAVED GAMES");
        btn1.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,14)); btn2.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,14));
        btn1.setTextFill(Color.BLACK);btn2.setTextFill(Color.BLACK);
        btn1.setTextAlignment(TextAlignment.CENTER);btn2.setTextAlignment(TextAlignment.CENTER);
        btn1.setPrefHeight(43);btn1.setPrefWidth(200);
        btn2.setPrefHeight(43);btn2.setPrefWidth(200);
        btn1.setLayoutX(250);btn1.setLayoutY(500);
        btn2.setLayoutX(250);btn2.setLayoutY(565);

        //Adding the score label
        Label score = new Label("0");
        score.setFont(new Font(18));score.setTextFill(Color.WHITE);score.setTextAlignment(TextAlignment.CENTER);
        score.setLayoutX(56);score.setLayoutY(600);
        score.setPrefSize(40,37);

        //Now,code to add the rotating rings
        Group ring1 = createRing(350,327,152);
        Group ring2 = createRing(350,327,132);
        Group ring3 = createRing(350,327,112);

        Timeline timeline1 = rotatingObstacle(ring1,350.0,327.0,5,360d);
        Timeline timeline2 = rotatingObstacle(ring2,350.0,327.0,5.5,360d);
        Timeline timeline3 = rotatingObstacle(ring3,350.0,327.0,6,360d);
        timeline1.play();
        timeline2.play();
        timeline3.play();
        root1.getChildren().addAll(helpIcon,settingsIcon,mainIcon,starIcon,exitIcon,playIcon,ring1,ring2,ring3,btn1,btn2,score);

        scene = new Scene(root1,700,700,Color.BLACK);
        stage.setScene(this.scene);
        stage.show();
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
        Group ring = new Group(arc1,arc2,arc3,arc4);
        return ring;
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
