package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.beans.EventHandler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class TestBallAnimation {
    public Stage stage;
    public Scene scene;

    private final int width = 800;
    private final int height = 800;
    private final double ball_radius = 16.5;
    private ParallelTransition pt = new ParallelTransition();

    private Game game;
    private final Ball ball;
    private ArrayList<Obstacles> generated_obstacles;
    private ArrayList<Point> star_positions;

    private double ballSpeed = 0;
    private boolean gameStarted;

    private ArrayList<GUIObstacleNode> created_obstacles;
    //private Group ring;
    private Group colorSwitch;
    private boolean collisionDetected = false;

    public TestBallAnimation(Stage stage){
        this.stage = stage;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e->run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(e-> gameStarted = true);
        Pane parent = new Pane(canvas);
        scene = new Scene(parent);

        game = new Game();
        ball = game.getBall();

        //generate the required obstacles
        for(int i = 0;i<10;i++)
            game.generateObstacles();

        //now,populating our arrays
        generated_obstacles = game.getGenerated_obstacles();
        star_positions = game.getStar_positions();
        created_obstacles = new ArrayList<>();

        //making the required obstacles
        for(Obstacles o:generated_obstacles){
            Group obs = null;
            if(o instanceof CircleObs)
                obs = ObstacleCreator.createRing(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter()/2);
            else if(o instanceof Square)
                obs = ObstacleCreator.createSquare(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());
            else if(o instanceof Triangle)
                obs = ObstacleCreator.createTriangle(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());
            else if(o instanceof Cross)
                obs = ObstacleCreator.createCross(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());

            Rotate rotate = new Rotate();
            rotate.setPivotX(o.getPosition().getX());
            rotate.setPivotY(o.getPosition().getY());
            try {
                obs.getTransforms().add(rotate);
            }
            catch (NullPointerException e){
                continue;
            }
            GUIObstacleNode obstacle = new GUIObstacleNode();
            obstacle.obstacle = obs; obstacle.angle = 0; obstacle.rate = o.getSpeed();
            created_obstacles.add(obstacle);
        }

        //sample obstacle
//        ring = createRing(400,470,120);
//        Rotate rotatingRing = new Rotate();
//        rotatingRing.setPivotX(400);
//        rotatingRing.setPivotY(470);
//        ring.getTransforms().add(rotatingRing);

        //sample colorSwitch
        colorSwitch = createColorSwitch(400,300,14);

        //adding all the obstacles to the parent
        for (GUIObstacleNode o:created_obstacles) {
            parent.getChildren().add(o.obstacle);
        }
        //adding all the stars at their positions
        parent.getChildren().add(colorSwitch);
        //timeline.play();
        pt.getChildren().addAll(timeline);
        pt.play();
    }
    public void run(GraphicsContext gc){
        //setting background screen as black
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        if(gameStarted){
            //checking for the input
            scene.setOnKeyPressed(e->{
                if(e.getCode()==KeyCode.W){
                    ballSpeed = 8.5;
                }
            });
            //updating the ball position
            ball.setPosition(new Point(ball.getPosition().getX(),ball.getPosition().getY() - ballSpeed));
            //rotating/translating the obstacles
            for(GUIObstacleNode o:created_obstacles){
                Transform transform = o.obstacle.getTransforms().get(0);
                if(transform instanceof Rotate){
                    o.angle+=o.rate;
                    ((Rotate)transform).setAngle(o.angle);
                }
            }


            //updating ball speed for the next instant (gravity)
            ballSpeed-=1;
            drawBall(gc);
            //now to check collision
            collisionDetected = checkCollision();
            if(collisionDetected){
                gameStarted = false;
            }
            //check for collision with color switch...if yes, change the color of the ball
            colorChange();
        }
    }

    private void drawBall(GraphicsContext gc){
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
        gc.fillOval(ball.getPosition().getX()-5.75,ball.getPosition().getY(),ball_radius,ball_radius);
    }

    private boolean checkCollision(){
        Circle b = new Circle(ball.getPosition().getX(),ball.getPosition().getY(),ball_radius);
        switch(ball.getColor()){
            case 0: b.setFill(Color.RED);
                break;
            case 1: b.setFill(Color.DEEPPINK);
                break;
            case 2: b.setFill(Color.YELLOW);
                break;
            case 3: b.setFill(Color.GREEN);
                break;
        }
        for (GUIObstacleNode o : created_obstacles) {
            Group obs = o.obstacle;
            //for ring obstacle
            if(obs.getChildren().get(0) instanceof Arc) {
                for (int i = 0; i < 4; i++) {
                    Node comp = obs.getChildren().get(i);
                    Arc arc;
                    if (comp instanceof Arc) {
                        arc = (Arc) comp;
                        Shape s = Shape.intersect(b, arc);
                        if (b.getFill() != arc.getStroke()) {
                            if(s.getBoundsInLocal().getWidth() != -1)
                                return true;
                        }
                    }
                }
            }
            //for square,triangle,cross obstacles
            else if(obs.getChildren().get(0) instanceof Line){
                for (int i =0;i<obs.getChildren().size();i++){
                    Node comp = obs.getChildren().get(i);
                    Line line;
                    if (comp instanceof Line) {
                        line = (Line) comp;
                        Shape s = Shape.intersect(b, line);
                        if (b.getFill() != line.getStroke()) {
                            if(s.getBoundsInLocal().getWidth() != -1)
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void colorChange(){
        int randColor = 0;
        int current_color = ball.getColor();
        Random random = new Random();
        if(ball.getPosition().getX()==400 && ball.getPosition().getY()==300){
            do{
                randColor = random.nextInt(4);
            }while(randColor == current_color);
            ball.setColor(randColor);
        }
    }
//    private void colorChange(){
//        int randColor = 0;
//        int current_color = ball.getColor();
//        Random random = new Random();
//        if(ball.getPosition().getX()==400 && ball.getPosition().getY()==300){
//            randColor=random.nextInt(4);
//            while(current_color==randColor)
//                randColor=random.nextInt(4);
//            ball.setColor(randColor);
//
//        }
//    }

    public Group createColorSwitch(double centerX, double centerY, double radius){
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();
        //properties of the arcs
        arc1.setFill(Color.RED); arc1.setStroke(Color.RED); arc1.setStrokeWidth(1);
        arc2.setFill(Color.DEEPPINK); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(1);
        arc3.setFill(Color.YELLOW); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(1);
        arc4.setFill(Color.GREEN); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(1);
        //Setting the dimensions of the arcs (centre,radius,length,start angle)
        arc1.setCenterX(centerX);arc3.setCenterX(centerX); arc2.setCenterX(centerX); arc4.setCenterX(centerX);
        arc1.setCenterY(centerY); arc2.setCenterY(centerY); arc3.setCenterY(centerY); arc4.setCenterY(centerY);
        arc1.setRadiusX(radius); arc2.setRadiusX(radius); arc3.setRadiusX(radius); arc4.setRadiusX(radius);
        arc1.setRadiusY(radius); arc2.setRadiusY(radius);arc3.setRadiusY(radius);arc4.setRadiusY(radius);
        arc1.setStartAngle(0.0f); arc2.setStartAngle(90.0f); arc3.setStartAngle(180.0f); arc4.setStartAngle(270.0f);
        arc1.setLength(90.0f); arc2.setLength(90.0f); arc3.setLength(90.0f); arc4.setLength(90.0f);
        //Setting the type of the arc
        arc1.setType(ArcType.ROUND);arc2.setType(ArcType.ROUND);arc3.setType(ArcType.ROUND);arc4.setType(ArcType.ROUND);

        return new Group(arc1,arc2,arc3,arc4);
    }

    public ImageView createStar(double centerX,double centerY,double size) throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\star.png");
        Image img = new Image(inputStream);
        ImageView star = new ImageView(img);
        star.setX(centerX-size/2);star.setY(centerY-size/2);
        star.setFitWidth(size);star.setFitHeight(size);
        inputStream.close();
        return star;
    }
}
