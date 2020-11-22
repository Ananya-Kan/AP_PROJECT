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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class LoadGames {
    Stage stage;
    Scene scene;

    public LoadGames(Stage stage) throws IOException{
        this.stage = stage;
        Pane root3 = new Pane();
        root3.setStyle("-fx-background-color: #000");
        scene = new Scene(root3,700,700, Color.BLACK);

        //Adding title
        Label title = new Label("LOAD SAVED GAMES");
        title.setTextFill(Color.WHITE);title.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD,36));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setLayoutX(172);title.setLayoutY(52);
        title.setPrefSize(413,73);

        //Setting the gif file
        FileInputStream file = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\giphy.gif");
        Image image = new Image(file);
        ImageView imageView = new ImageView(image);
        imageView.setX(82);imageView.setY(150);
        imageView.setFitWidth(500);imageView.setFitHeight(450);
        file.close();

        root3.getChildren().addAll(title,imageView);
    }
}
