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
public class Hit {
    Stage stage;
    public Scene scene;

    public Hit(Stage hitstage) throws FileNotFoundException {
        stage = hitstage;
        Group vbox = new Group();
        scene = new Scene(vbox, 700, 500, Color.BLACK);
        FileInputStream in = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\oops.png");
        Image image = new Image(in);
        ImageView imageView = new ImageView(image);
        imageView.setX(280);
        imageView.setY(50);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        FileInputStream in1 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\stick.gif");
        Image image1 = new Image(in1);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(40);
        imageView1.setY(15);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(300);
        imageView1.setPreserveRatio(true);

        Button btn=new Button("CONTINUE WITH STARS");
        btn.setLayoutX(250);
        btn.setLayoutY(200);
        btn.setPrefWidth(250); btn.setPrefHeight(50);
        btn.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));
        Button btn1=new Button("EXIT");
        btn1.setLayoutX(250);
        btn1.setLayoutY(300);
        btn1.setPrefWidth(250); btn1.setPrefHeight(50);
        btn1.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));
        Button btn2=new Button("RESTART");
        btn2.setLayoutX(250);
        btn2.setLayoutY(400);
        btn2.setPrefWidth(250); btn2.setPrefHeight(50);
        btn2.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));

        vbox.getChildren().addAll(btn,btn1,btn2,imageView,imageView1);
    }
}