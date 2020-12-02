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
public class Hscore {
    Stage stage;
    public Scene scene;

    public Hscore(Stage hstage) throws FileNotFoundException {
        stage = hstage;
        Group vbox = new Group();
        scene = new Scene(vbox, 500, 300, Color.BLACK);
        FileInputStream in = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\win.gif");
        Image image = new Image(in);
        ImageView imageView = new ImageView(image);
        imageView.setX(30);
        imageView.setY(50);
        imageView.setFitHeight(200);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Text text = new Text();

        text.setX(250);
        text.setY(20);
        text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,25));
        text.setFill(Color.WHITE);// setting colour of the text to blue
        //text.setStroke(Color.BLACK); // setting the stroke for the text
        text.setStrokeWidth(1); // setting stroke width to 2
        int d=0;
        text.setText("\n"+"\n"+"HIGH SCORE : "+d);

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
        btn3.setPrefWidth(50); btn3.setPrefHeight(30);

        Button btn4=new Button("RESET HIGHSCORE");
        btn4.setLayoutX(220);
        btn4.setLayoutY(150);
        btn4.setPrefWidth(250); btn4.setPrefHeight(40);
        btn4.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,16));
        btn4.setStyle("-fx-background-color: LightSkyBlue");

        vbox.getChildren().addAll(imageView,text,btn3,btn4);
    }
}
