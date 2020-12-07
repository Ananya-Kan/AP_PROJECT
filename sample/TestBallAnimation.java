package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import java.util.ArrayList;
import java.util.Random;


public class TestBallAnimation {
    public Stage stage;
    public Scene scene;

    private final int width = 800;
    private final int height = 800;
    private final double ball_radius = 15;
    private Canvas canvas = new Canvas(width,height);
    private Pane parent;
    private ParallelTransition pt = new ParallelTransition();
    private final Ball ball = new Ball(new Point(400,725),0);
    private double ballSpeed = 0;
    private boolean gameStarted;
    private ArrayList<Group> testObstacleArray = new ArrayList<>();
    private int[] randObstacleGenerator = new int[10];
    private Group ring;
    private Group colorSwitch;
    private double angle;
    private boolean collisionDetected = false;

    public TestBallAnimation(Stage stage){
        this.stage = stage;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e->run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(e-> gameStarted = true);
        parent = new Pane(canvas);
        scene = new Scene(parent);

        //sample obstacle
        ring = createRing(400,470,100);
        Rotate rotatingRing = new Rotate();
        rotatingRing.setPivotX(400);
        rotatingRing.setPivotY(470);
        ring.getTransforms().add(rotatingRing);

        //sample colorSwitch
        colorSwitch = createColorSwitch(400,330,15);

        parent.getChildren().addAll(ring,colorSwitch);

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
                    ballSpeed = 1.5;
                }
            });
            //updating the ball position
            ball.setPosition(new Point(ball.getPosition().getX(),ball.getPosition().getY() - ballSpeed));
            //rotating/translating the obstacles
            Transform transform = ring.getTransforms().get(0);
            if(transform instanceof Rotate){
                angle+=1;
                ((Rotate)transform).setAngle(angle);
            }
            //updating ball speed for the next instant (gravity)
            ballSpeed-=0.02;
            drawBall(gc,ball);
            //now to check collision
            collisionDetected = checkCollision(ring,ball);
            if(collisionDetected){
                gameStarted = false;
            }
            //check for collision with color switch...if yes, change the color of the ball
            colorChange(colorSwitch,ball);
        }
    }

    private void drawBall(GraphicsContext gc, Ball ball){
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

    private boolean checkCollision(Group ring, Ball ball){
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
        for(int i = 0;i<4;i++){
            Node comp = ring.getChildren().get(i);
            Arc arc;
            if(comp instanceof Arc){
                arc = (Arc)comp;
                Shape s = Shape.intersect(b,arc);
                if(b.getFill() != arc.getStroke()){
                    return s.getBoundsInLocal().getWidth() != -1;
                }
            }
        }
        return false;
    }

    private void colorChange(Group colorSwitch, Ball ball){
        int randColor = 0;
        Random random = new Random();
        if(ball.getPosition().getX()==400 && ball.getPosition().getY()==330){
            do{
                randColor = random.nextInt(4);
            }while(randColor == ball.getColor());
            ball.setColor(randColor);
        }
    }

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

    private Group createRing(double centerX, double centerY, double radius){
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();
        //properties of the arcs
        arc1.setFill(Color.TRANSPARENT); arc1.setStroke(Color.RED); arc1.setStrokeWidth(20);
        arc2.setFill(Color.TRANSPARENT); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(20);
        arc3.setFill(Color.TRANSPARENT); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(20);
        arc4.setFill(Color.TRANSPARENT); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(20);

        //Setting the dimensions of the arcs (centre,radius,length,start angle)
        arc1.setCenterX(centerX);arc3.setCenterX(centerX); arc2.setCenterX(centerX); arc4.setCenterX(centerX);
        arc1.setCenterY(centerY); arc2.setCenterY(centerY); arc3.setCenterY(centerY); arc4.setCenterY(centerY);
        arc1.setRadiusX(radius); arc2.setRadiusX(radius); arc3.setRadiusX(radius); arc4.setRadiusX(radius);
        arc1.setRadiusY(radius); arc2.setRadiusY(radius);arc3.setRadiusY(radius);arc4.setRadiusY(radius);
        arc1.setStartAngle(0.0f); arc2.setStartAngle(90.0f); arc3.setStartAngle(180.0f); arc4.setStartAngle(270.0f);
        arc1.setLength(90.0f); arc2.setLength(90.0f); arc3.setLength(90.0f); arc4.setLength(90.0f);

        //Setting the type of the arc
        arc1.setType(ArcType.OPEN);arc2.setType(ArcType.OPEN);arc3.setType(ArcType.OPEN);arc4.setType(ArcType.OPEN);

        //Creating a Group object
        return new Group(arc1,arc2,arc3,arc4);
    }

    private Group createCross(double centerX, double centerY, double armLength){
        Line arm1 = new Line();
        Line arm2 = new Line();
        Line arm3 = new Line();
        Line arm4 = new Line();
        //Styling the arms
        arm1.setStroke(Color.RED);arm1.setStrokeWidth(10);arm1.setStrokeType(StrokeType.CENTERED);arm1.setStrokeLineCap(StrokeLineCap.ROUND);
        arm2.setStroke(Color.DEEPPINK);arm2.setStrokeWidth(10);arm2.setStrokeType(StrokeType.CENTERED);arm2.setStrokeLineCap(StrokeLineCap.ROUND);
        arm3.setStroke(Color.YELLOW);arm3.setStrokeWidth(10);arm3.setStrokeType(StrokeType.CENTERED);arm3.setStrokeLineCap(StrokeLineCap.ROUND);
        arm4.setStroke(Color.GREEN);arm4.setStrokeWidth(10);arm4.setStrokeType(StrokeType.CENTERED);arm4.setStrokeLineCap(StrokeLineCap.ROUND);

        //positioning the arms
        arm1.setStartX(centerX);arm1.setStartY(centerY);arm1.setEndX(centerX);arm1.setEndY(centerY+armLength);
        arm2.setStartX(centerX);arm2.setStartY(centerY);arm2.setEndX(centerX);arm2.setEndY(centerY-armLength);
        arm3.setStartX(centerX);arm3.setStartY(centerY);arm3.setEndX(centerX-armLength);arm3.setEndY(centerY);
        arm4.setStartX(centerX);arm4.setStartY(centerY);arm4.setEndX(centerX+armLength);arm4.setEndY(centerY);

        return new Group(arm1,arm2,arm3,arm4);
    }

    private Group createSquare(double centerX, double centerY, double sideLength){
        Line side1 = new Line();
        Line side2 = new Line();
        Line side3 = new Line();
        Line side4 = new Line();

        side1.setStroke(Color.RED);side1.setStrokeWidth(10);side1.setStrokeType(StrokeType.CENTERED);side1.setStrokeLineCap(StrokeLineCap.ROUND);
        side2.setStroke(Color.DEEPPINK);side2.setStrokeWidth(10);side2.setStrokeType(StrokeType.CENTERED);side2.setStrokeLineCap(StrokeLineCap.ROUND);
        side3.setStroke(Color.YELLOW);side3.setStrokeWidth(10);side3.setStrokeType(StrokeType.CENTERED);side3.setStrokeLineCap(StrokeLineCap.ROUND);
        side4.setStroke(Color.GREEN);side4.setStrokeWidth(10);side4.setStrokeType(StrokeType.CENTERED);side4.setStrokeLineCap(StrokeLineCap.ROUND);

        side1.setStartX(centerX-sideLength/2);side1.setStartY(centerY+sideLength/2);side1.setEndX(centerX-sideLength/2);side1.setEndY(centerY-sideLength/2);
        side2.setStartX(centerX-sideLength/2);side2.setStartY(centerY-sideLength/2);side2.setEndX(centerX+sideLength/2);side2.setEndY(centerY-sideLength/2);
        side3.setStartX(centerX+sideLength/2);side3.setStartY(centerY-sideLength/2);side3.setEndX(centerX+sideLength/2);side3.setEndY(centerY+sideLength/2);
        side4.setStartX(centerX+sideLength/2);side4.setStartY(centerY+sideLength/2);side4.setEndX(centerX-sideLength/2);side4.setEndY(centerY+sideLength/2);

        return new Group(side1,side2,side3,side4);
    }

    private Group createTriangle(double centerX, double centerY, double sideLength){
        Line side1 = new Line();
        Line side2 = new Line();
        Line side3 = new Line();

        side1.setStroke(Color.RED);side1.setStrokeWidth(10);side1.setStrokeType(StrokeType.CENTERED);side1.setStrokeLineCap(StrokeLineCap.ROUND);
        side2.setStroke(Color.DEEPPINK);side2.setStrokeWidth(10);side2.setStrokeType(StrokeType.CENTERED);side2.setStrokeLineCap(StrokeLineCap.ROUND);
        side3.setStroke(Color.YELLOW);side3.setStrokeWidth(10);side3.setStrokeType(StrokeType.CENTERED);side3.setStrokeLineCap(StrokeLineCap.ROUND);

        double dist1 = sideLength/(Math.sqrt(3)*2);
        double dist2 = sideLength/(Math.sqrt(3));
        side1.setStartX(centerX-sideLength/2);side1.setStartY(centerY+dist1);side1.setEndX(centerX+sideLength/2);side1.setEndY(centerY+dist1);
        side2.setRotate(60);
        side2.setStartX(centerX-sideLength/2);side2.setStartY(centerY+dist1);side2.setEndX(centerX);side2.setEndY(centerY-dist2);
        side3.setRotate(300);
        side3.setStartX(centerX);side3.setStartY(centerY-dist2);side3.setEndX(centerX+sideLength/2);side3.setEndY(centerY+dist1);

        return new Group(side1,side2,side3);
    }

    public Timeline rotatingObstacle(Group obs, double pivotX, double pivotY, double timePeriod, double angle){
        Rotate rotatingObj = new Rotate(angle,pivotX,pivotY);
        obs.getTransforms().add(rotatingObj);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,new KeyValue(rotatingObj.angleProperty(),0d)),
                new KeyFrame(Duration.millis(timePeriod),new KeyValue(rotatingObj.angleProperty(),angle)));
        timeline.setCycleCount(Animation.INDEFINITE);

        return timeline;
    }
}
