package sample;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class Load {
    Stage stage;
    Scene scene;

    public Load(Stage stage) throws IOException{
        this.stage = stage;
        Pane root3 = new Pane();
        root3.setStyle("-fx-background-color: #000");
        scene = new Scene(root3,700,700, Color.BLACK);

        //Adding title
        Label title = new Label("LOAD  SAVED  GAMES");
        title.setTextFill(Color.WHITE);title.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,40));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setLayoutX(150);title.setLayoutY(52);
        title.setPrefSize(500,73);

        Text text = new Text();

        text.setX(300);
        text.setY(320);
        text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD, FontPosture.REGULAR,30));
        text.setFill(Color.WHITE);// setting colour of the text to blue
        //text.setStroke(Color.BLACK); // setting the stroke for the text
        text.setStrokeWidth(1); // setting stroke width to 2
        text.setText("\n"+"EMPTY."+"\n"+"  PLAY"+"\n"+"  THE"+"\n"+"GAME!!");

        //Setting the gif file
        FileInputStream file = new FileInputStream("C:\\Users\\Ananya\\IdeaProjects\\Colour_Switch\\src\\sample\\load.gif");
        Image image = new Image(file);
        ImageView imageView = new ImageView(image);
        imageView.setX(82);imageView.setY(150);
        imageView.setFitWidth(550);imageView.setFitHeight(500);
        file.close();

        root3.getChildren().addAll(title,imageView,text);
    }
}