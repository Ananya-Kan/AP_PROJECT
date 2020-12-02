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
public class Help {
    Stage stage;
    public Scene scene;

    public Help(Stage primarystage) throws FileNotFoundException {
        stage=primarystage;
        Group vbox=new Group();
        scene=new Scene(vbox, 700,500,Color.BLACK);
        FileInputStream inputstream = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\how.png");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        imageView.setX(130);
        imageView.setY(30);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        FileInputStream inputstream1 = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\HTP.png");
        Image image1 = new Image(inputstream1);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(400);
        imageView1.setY(60);
        imageView1.setFitHeight(200);
        imageView1.setFitWidth(300);
        imageView1.setPreserveRatio(true);

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
        btn3.setPrefWidth(70); btn3.setPrefHeight(50);


        Text text = new Text();

        text.setX(100);
        text.setY(20);
        text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,18));
        text.setFill(Color.WHITE);// setting colour of the text to blue
        //text.setStroke(Color.BLACK); // setting the stroke for the text
        text.setStrokeWidth(1); // setting stroke width to 2
        text.setText("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+
                "\n"+
                "Play Color Switch game : tap the BUTTON carefully"+"\n"+" to control the ball through each obstacle and "+"\n"+" your ball will switch color with some power-ups."+"\n"+"You must follow the color pattern on each obstacle"+"\n"+"to cross it, but be careful not to pass through the"+"\n"+" wrong color, or you’ll have to start again.\n"+
                "You can also use earned stars to revive yourself"+"\n"+" when dead and continue the game.");
        vbox.getChildren().add(imageView);
        vbox.getChildren().add(imageView1);
        vbox.getChildren().add(text);
        vbox.getChildren().add(btn3);


    }
}