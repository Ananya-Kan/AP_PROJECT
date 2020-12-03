package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.EventHandler;


public class TestBallAnimation {
    public Stage stage;
    public Scene scene;

    private final int width = 800;
    private final int height = 800;
    private final double ball_radius = 12;
    private Canvas canvas = new Canvas(width,height);
    private final Ball ball = new Ball(new Point(400,700),0);
    private double ballSpeed = 0;
    private boolean gameStarted;

    public TestBallAnimation(Stage stage){
        this.stage = stage;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e->run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(e-> gameStarted = true);
        scene = new Scene(new StackPane(canvas));
        timeline.play();
    }
    public void run(GraphicsContext gc){
        //setting background screen as black
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        if(gameStarted){
            //checking for the input
            scene.setOnKeyPressed(e->{
                if(e.getCode()==KeyCode.W){
                    ballSpeed = 0.75;
                }
            });
            //updating the ball position
            ball.setPosition(new Point(ball.getPosition().getX(),ball.getPosition().getY() - ballSpeed));
            //updating ball speed for the next instant (gravity)
            ballSpeed-=0.01;
            //drawBall(gc,ball);
            switch(ball.getColor()){
                case 0: gc.setFill(Color.RED);
                    break;
                case 1: gc.setFill(Color.DEEPPINK);
                    break;
                case 2: gc.setFill(Color.YELLOW);
                    break;
                case 3: gc.setFill(Color.GREEN);
                    break;
            }
            gc.fillOval(ball.getPosition().getX(),ball.getPosition().getY(),ball_radius,ball_radius);

        }
    }

    private void drawBall(GraphicsContext gc, Ball ball){
        switch(ball.getColor()){
            case 1: gc.setFill(Color.RED);
                    break;
            case 2: gc.setFill(Color.DEEPPINK);
                    break;
            case 3: gc.setFill(Color.YELLOW);
                    break;
            case 4: gc.setFill(Color.GREEN);
                    break;
        }
        gc.fillOval(ball.getPosition().getX(),ball.getPosition().getY(),ball_radius,ball_radius);
    }
}
