package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application{
    @Override
    public void start(Stage pstage) throws Exception {
        // TODO Auto-generated method stub

        pstage.setTitle("Color Switch ");

        Load page1 = new Load(pstage);
        Hit page2=new Hit(pstage);
        Pause page3= new Pause(pstage);
        Settings page4=new Settings(pstage);
        Help page5=new Help(pstage);
        Hscore page6=new Hscore(pstage);
        GamePlay page7=new GamePlay(pstage);
        MainMenu page8=new MainMenu(pstage);

        pstage.setScene(page2.scene);
        pstage.show();
    }
    public static void main(String[] args) {
        launch(args);

    }
}
