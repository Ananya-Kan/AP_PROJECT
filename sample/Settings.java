package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Settings {
    Stage stage;
    public Scene scene;

    public Settings(Stage secondarystage) throws FileNotFoundException {
        stage = secondarystage;
        Group vbox = new Group();
        scene = new Scene(vbox, 700, 500, Color.BLACK);
        FileInputStream inputstream4 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\sicon.png");
        Image image11 = new Image(inputstream4);
        ImageView imageView11 = new ImageView(image11);
        imageView11.setX(180);
        imageView11.setY(20);
        imageView11.setFitHeight(300);
        imageView11.setFitWidth(400);
        imageView11.setPreserveRatio(true);

        FileInputStream inputstream2 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\soundimage.png");
        Image image1 = new Image(inputstream2);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(380);
        imageView1.setY(130);
        imageView1.setFitHeight(100);
        imageView1.setFitWidth(200);
        imageView1.setPreserveRatio(true);

        FileInputStream inputstream3 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\sound.gif");
        Image image3 = new Image(inputstream3);
        ImageView imageView3 = new ImageView(image3);
        imageView3.setX(120);
        imageView3.setY(150);
        imageView3.setFitHeight(180);
        imageView3.setFitWidth(350);
        imageView3.setPreserveRatio(true);

        FileInputStream inputstream44 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\user.png");
        Image image44 = new Image(inputstream44);
        ImageView imageView44 = new ImageView(image44);
        imageView44.setX(520);
        imageView44.setY(300);
        imageView44.setFitHeight(140);
        imageView44.setFitWidth(230);
        imageView44.setPreserveRatio(true);

        Button btn=new Button("ON");
        btn.setLayoutX(400);
        btn.setLayoutY(200);
        btn.setPrefWidth(70); btn.setPrefHeight(50);
        btn.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));
        Button btn1=new Button("OFF");
        btn1.setLayoutX(500);
        btn1.setLayoutY(200);
        btn1.setPrefWidth(70); btn1.setPrefHeight(50);
        btn1.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));
        Button btn2=new Button("SET  USERNAME");
        btn2.setLayoutX(50);
        btn2.setLayoutY(350);
        btn2.setPrefWidth(400); btn2.setPrefHeight(65);
        btn2.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,18));

        FileInputStream inputstream34 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\back.png");
        Image image34 = new Image(inputstream34);
        ImageView imageView34 = new ImageView(image34);
        imageView34.setX(5);
        imageView34.setY(0);
        imageView34.setFitHeight(60);
        imageView34.setFitWidth(70);
        imageView34.setPreserveRatio(true);

        Button btn3=new Button("",imageView34);
        btn3.setLayoutX(0);
        btn3.setLayoutY(0);
        btn3.setPrefWidth(90); btn3.setPrefHeight(50);

        vbox.getChildren().add(imageView1);
        vbox.getChildren().add(imageView3);
        vbox.getChildren().add(imageView11);
        vbox.getChildren().add(imageView44);
        vbox.getChildren().add(btn);
        vbox.getChildren().add(btn1);
        vbox.getChildren().add(btn2);
        vbox.getChildren().add(btn3);
    }
}
