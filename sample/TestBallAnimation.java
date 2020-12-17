package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
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
    private Pane parent;

    private final int width = 800;
    private final int height = 800;
    private final double ball_radius = 16.5;

    private Game game;
    private final Ball ball;
    private ArrayList<Obstacles> generated_obstacles;
    private ArrayList<Point> star_positions;
    private ArrayList<Point> switch_positions;

    private double ballSpeed = 0;
    private double compSpeed = 0;
    private boolean gameStarted;

    private ArrayList<GUIObstacleNode> created_obstacles;
    private ArrayList<GUIStar> created_stars;
    private ArrayList<GUISwitch> created_switches;

    ImageView highStar;
    Label starsCollected;

    private int flag = 0;

    public TestBallAnimation(Stage stage) throws IOException {
        this.stage = stage;
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e-> {
            try {
                run(gc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseClicked(e-> gameStarted = true);
        parent = new Pane(canvas);
        scene = new Scene(parent);

        //putting star image, label
        highStar = createStar(100,50,100);
        starsCollected = new Label("0",highStar);
        starsCollected.setFont(new Font(20));
        starsCollected.setTextFill(Color.WHITE);

        parent.getChildren().add(starsCollected);

        game = new Game();
        ball = game.getBall();

        //generate the required obstacles
        for(int i = 0;i<10;i++)
            game.generateObstacles();

        //now,populating our arrays
        generated_obstacles = game.getGenerated_obstacles();
        star_positions = game.getStar_positions();
        switch_positions = game.getSwitch_positions();
        created_obstacles = new ArrayList<>();
        created_stars = new ArrayList<>();
        created_switches = new ArrayList<>();

        //making the required obstacles
        for(Obstacles o:generated_obstacles){
            Group obs = null;
            if(o instanceof CircleObs)
                obs = ObstacleCreator.createRing(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter()/2);
            else if(o instanceof Square)
                obs = ObstacleCreator.createSquare(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());
            else if(o instanceof Cross)
                obs = ObstacleCreator.createCross(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());

            Rotate rotate = new Rotate();
            Translate translate = new Translate();
            rotate.setPivotX(o.getPosition().getX());
            rotate.setPivotY(o.getPosition().getY());
            try {
                obs.getTransforms().add(rotate);
                obs.getTransforms().add(translate);
            }
            catch (NullPointerException e){
                continue;
            }
            GUIObstacleNode obstacle = new GUIObstacleNode();
            obstacle.obstacle = obs; obstacle.angle = o.getOffset_angle(); obstacle.rate = o.getSpeed();
            created_obstacles.add(obstacle);
        }
        //adding all the obstacles to the parent
        for (GUIObstacleNode o:created_obstacles) {
            parent.getChildren().add(o.obstacle);
        }

        //adding the color switches to their positions
        for(Point c:switch_positions){
            Group color_switch = createColorSwitch(c.getX(),c.getY(),14);
            color_switch.getTransforms().add(new Translate());
            created_switches.add(new GUISwitch(color_switch,c));
            parent.getChildren().add(color_switch);
        }
        //adding all the stars at their positions
        for (Point s:star_positions){
            ImageView star = createStar(s.getX(),s.getY(),30);
            star.getTransforms().add(new Translate());
            parent.getChildren().add(star);
            created_stars.add(new GUIStar(star,s));
        }

        timeline.play();
    }
    public void run(GraphicsContext gc) throws IOException {

        //setting background screen as black
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        if(gameStarted){
            //checking for the input
            scene.setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.W) {
                    ballSpeed = 8.5;
                }
            });

            //updating the ball position
            ball.setPosition(new Point(ball.getPosition().getX(), ball.getPosition().getY() - ballSpeed));
            if(ball.getPosition().getY()<=400)
                infiniteScroll();

            //updating ball speed for the next instant (gravity)
            ballSpeed -= 1;

            //manageObstacles();

            //rotating the obstacles
            for(GUIObstacleNode o:created_obstacles){
                Transform transform = o.obstacle.getTransforms().get(0);
                if(transform instanceof Rotate){
                    o.angle+=o.rate;
                    ((Rotate)transform).setAngle(o.angle);
                }
            }


            drawBall(gc);
            //now to check collision
            if(checkCollision()){
                gameStarted = false;
            }
            //check for collision with color switch...if yes, change the color of the ball
            if(checkColorSwitch()){
                int randColor = 0;
                int current_color = ball.getColor();
                Random random = new Random();
                do{
                    randColor = random.nextInt(4);
                }while(randColor == current_color);
                ball.setColor(randColor);
            }
            //check for collision with star
            GUIStar s = collectStar();
            if(s!=null){
                //System.out.println("+1S");
                parent.getChildren().remove(s.image);   //removing the star from view
                created_stars.remove(0);    //remove that star instance from the list
                star_positions.remove(0);
                game.setStars(game.getStars()+1);
                starsCollected.setText(String.valueOf(game.getStars()));
                flag++;
                for (ObstacleNode obs:game.getSaved_obstacles()) {
                    if(obs.getStar().equals(s.position)){
                        for (Obstacles o: obs.getObstacles())
                            o.setPassed(true);
                        break;
                    }
                }
            }
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

    private void infiniteScroll(){
        double excess = 400-ball.getPosition().getY();
        //dragging the ball downwards
        //ball.setPosition(new Point(ball.getPosition().getX(),ball.getPosition().getY()+excess));
        //dragging obstacles downwards
        for(GUIObstacleNode o:created_obstacles ){
            //now moving the obstacles downwards
            TranslateTransition transitDown = new TranslateTransition(Duration.millis(500),o.obstacle);
            transitDown.setByY(excess);
            transitDown.setCycleCount(1);
            transitDown.setInterpolator(Interpolator.EASE_BOTH);
            transitDown.play();
        }
        for (Obstacles o:generated_obstacles) {
            o.setPosition(new Point(o.getPosition().getX(),o.getPosition().getY()+excess));
        }
        for(GUIStar star:created_stars) {
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setDuration(Duration.millis(500));
            translateTransition1.setNode(star.image);
            translateTransition1.setByY(excess);
            //Setting the cycle count for the transition
            translateTransition1.setCycleCount(1);
            translateTransition1.setAutoReverse(false);
            translateTransition1.play();

            TranslateTransition translateTransition2 = new TranslateTransition();
            translateTransition2.setDuration(Duration.millis(500));
            translateTransition2.setNode(star.rect);
            translateTransition2.setByY(excess);
            //Setting the cycle count for the transition
            translateTransition2.setCycleCount(1);
            translateTransition2.setAutoReverse(false);
            translateTransition2.play();

            star.position.setY(star.position.getY()+excess);
        }
        for (Point p:star_positions) {
            p.setY(p.getY()+excess);
        }
        for(GUISwitch cswitch:created_switches)
        {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(500));
            translateTransition.setNode(cswitch.colorSwitch);
            translateTransition.setByY(excess);
            //Setting the cycle count for the transition
            translateTransition.setCycleCount(1);
            translateTransition.setAutoReverse(false);
            translateTransition.play();
        }
        for (Point p:switch_positions) {
            p.setY(p.getY()+excess);
        }

    }
    private void manageObstacles() throws IOException {
        if (flag == 3) {
            ObstacleNode obst = game.getSaved_obstacles().get(0);
            int i = 0;
            while (i < obst.getObstacles().size()) {
                generated_obstacles.remove(0);
                created_obstacles.remove(0);
                i++;
            }
            game.getSaved_obstacles().remove(0);
            //now, generate one more obstacle
            game.generateObstacles();

            Obstacles o = generated_obstacles.get(generated_obstacles.size()-1);
            Group obs = null;
            if(o instanceof CircleObs)
                obs = ObstacleCreator.createRing(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter()/2);
            else if(o instanceof Square)
                obs = ObstacleCreator.createSquare(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());
            else if(o instanceof Cross)
                obs = ObstacleCreator.createCross(o.getPosition().getX(),o.getPosition().getY(),o.getSizeParameter());

            Rotate rotate = new Rotate();
            Translate translate = new Translate();
            rotate.setPivotX(o.getPosition().getX());
            rotate.setPivotY(o.getPosition().getY());
            try {
                obs.getTransforms().add(rotate);
                obs.getTransforms().add(translate);
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            GUIObstacleNode obstacle = new GUIObstacleNode();
            obstacle.obstacle = obs; obstacle.angle = o.getOffset_angle(); obstacle.rate = o.getSpeed();
            created_obstacles.add(obstacle);
            parent.getChildren().add(obs);

            Point c = switch_positions.get(switch_positions.size()-1);
            Group color_switch = createColorSwitch(c.getX(),c.getY(),14);
            color_switch.getTransforms().add(new Translate());
            created_switches.add(new GUISwitch(color_switch,c));
            parent.getChildren().add(color_switch);

            Point s = star_positions.get(star_positions.size()-1);
            ImageView star = createStar(s.getX(),s.getY(),30);
            star.getTransforms().add(new Translate());
            parent.getChildren().add(star);
            created_stars.add(new GUIStar(star,s));

            flag = 0;
        }
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

    private GUIStar collectStar(){
        Rectangle rect;
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

        GUIStar s = created_stars.get(0);
        rect = s.rect;

        if(Shape.intersect(rect,b).getBoundsInLocal().getWidth()!=-1)
            return s;

        return null;
    }
    private boolean checkColorSwitch(){
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
        GUISwitch cswitch = created_switches.get(0);
        for(int i = 0; i<cswitch.colorSwitch.getChildren().size();i++){
            Node comp = cswitch.colorSwitch.getChildren().get(i);
            Arc arc;
            if(comp instanceof Arc){
                arc = (Arc)comp;
                if(Shape.intersect(arc,b).getBoundsInLocal().getWidth()!=-1){
                    parent.getChildren().remove(cswitch.colorSwitch);
                    switch_positions.remove(0);
                    created_switches.remove(0);
                    return true;
                }
            }
        }
        return false;
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

    public ImageView createStar(double centerX,double centerY,double size) throws IOException {

        //String path = "M"+centerX+" "+(centerY+)+"z";

        FileInputStream inputStream = new FileInputStream("C:\\Users\\Manu\\IdeaProjects\\ColorSwitchGUI\\src\\sample\\images\\star.png");
        Image img = new Image(inputStream);
        ImageView star = new ImageView(img);
        star.setX(centerX-size/2);star.setY(centerY-size/2);
        star.setFitWidth(size);star.setFitHeight(size);
        inputStream.close();
        return star;
    }
}
