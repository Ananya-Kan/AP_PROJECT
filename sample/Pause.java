package sample;
//import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class Pause {
    Stage stage;
    public Scene scene;

    public Pause(Stage pstage) throws FileNotFoundException {
        stage = pstage;
        Group vbox = new Group();
        scene = new Scene(vbox, 700, 500, Color.BLACK);


        FileInputStream stream1 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\pau.png");
        Image image1 = new Image(stream1);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(360);
        imageView1.setY(80);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(300);
        imageView1.setPreserveRatio(true);

        Button btn=new Button("RESUME");
        btn.setLayoutX(250);
        btn.setLayoutY(300);
        btn.setPrefWidth(250); btn.setPrefHeight(50); btn.setStyle("-fx-background-color: LightSkyBlue");
        btn.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));
        Button btn1=new Button("SAVE & EXIT");
        btn1.setLayoutX(250);
        btn1.setLayoutY(400);
        btn1.setPrefWidth(250); btn1.setPrefHeight(50);
        btn1.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16)); btn1.setStyle("-fx-background-color: LightSkyBlue");

        FileInputStream stream = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\p.gif");
        Image image = new Image(stream);
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(20);
        imageView.setFitHeight(220);
        imageView.setFitWidth(230);
        imageView.setPreserveRatio(false);

        vbox.getChildren().addAll(btn,btn1,imageView,imageView1);
    }
}