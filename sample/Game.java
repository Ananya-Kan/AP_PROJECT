package sample;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static int no_of_games;
    private final int id;
    private final Ball ball;
    private int stars;
    private ArrayList<ObstacleNode> saved_obstacles;
    private ArrayList<Point> star_positions;
    private ArrayList<Obstacles> generated_obstacles;
    {//static initialization
        id = ++no_of_games;
    }

    public Game(){
        ball = new Ball(new Point(400,725),0);
        stars = 0;
        saved_obstacles = new ArrayList<>();
        star_positions = new ArrayList<>();
        generated_obstacles = new ArrayList<>();
    }

    public int getId() { return id; }
    public Ball getBall() { return ball; }
    public void setStars(int stars) { this.stars = stars; }
    public int getStars() { return stars; }
    public ArrayList<Obstacles> getGenerated_obstacles() { return generated_obstacles; }
    public ArrayList<Point> getStar_positions() { return star_positions; }
    public ArrayList<ObstacleNode> getSaved_obstacles() { return saved_obstacles; }

    private int calc_difficulty(){
        //helper function to calculate difficulty based on obstacles passed
        int passed_obstacles = 0;
        for(Obstacles o:generated_obstacles){
            if(o.isPassed())
                passed_obstacles++;
        }
        if(passed_obstacles>20)
            return 2;
        else if(passed_obstacles>10)
            return 1;
        else
            return 0;
    }
    public void generateObstacles(){
        if(generated_obstacles.size()==0){
            generated_obstacles.add(new CircleObs(new Point(400,520),4,200));
        }
        Random random = new Random();
        //first, to generate the type of obstacle
        int type = random.nextInt(9)+1;
        //also, determine the difficulty of the obstacle
        int difficulty = calc_difficulty();
        //values of speed for each difficulty
        double[] speed_vals = {4,5,5.5};
        //now using all the information to instantiate the obstacle and insert into our list
        Obstacles prev = generated_obstacles.get(generated_obstacles.size()-1);
        double space = prev.getPosition().getY() - 420;
        double speed = speed_vals[difficulty];
        ObstacleNode obs_node = null;
        Point star_pos = new Point(400,space);
        star_positions.add(star_pos);
        switch(type){
            case 1: Obstacles ins1 = new CircleObs(new Point(400,space),speed,200);
                    generated_obstacles.add(ins1);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 2: ins1 = new Square(new Point(400,space),speed,200);
                    generated_obstacles.add(ins1);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 3: ins1 = new Triangle(new Point(400,space),speed,240);
                    generated_obstacles.add(ins1);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 4: ins1 = new Cross(new Point(320,space),-speed, 80);
                    generated_obstacles.add(ins1);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 5: ins1 = new CircleObs(new Point(400,space),speed,260);
                    Obstacles ins2 = new Cross(new Point(360,space),speed, 50);
                    generated_obstacles.add(ins1);generated_obstacles.add(ins2);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);obs_node.addObstacle(ins2);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 6: ins1 = new CircleObs(new Point(400,space),speed,280);
                    ins2 = new Triangle(new Point(400,space),speed,200);
                    generated_obstacles.add(ins1);generated_obstacles.add(ins2);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);obs_node.addObstacle(ins2);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 7: ins1 = new CircleObs(new Point(400,space),speed,160);
                    ins2 = new CircleObs(new Point(400,space),speed,200);
                    Obstacles ins3 = new CircleObs(new Point(400,space),speed,240);
                    generated_obstacles.add(ins1);generated_obstacles.add(ins2);generated_obstacles.add(ins3);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);obs_node.addObstacle(ins2);obs_node.addObstacle(ins3);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 8: ins1 = new CircleObs(new Point(400,space),speed,240);
                    ins2 = new Square(new Point(400,space),speed,120);
                    generated_obstacles.add(ins1);generated_obstacles.add(ins2);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);obs_node.addObstacle(ins2);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
            case 9: ins1 = new Cross(new Point(338,space),speed,60);
                    ins2 = new Cross(new Point(465,space),-speed,60);
                    generated_obstacles.add(ins1);generated_obstacles.add(ins2);
                    obs_node = new ObstacleNode();
                    obs_node.addObstacle(ins1);obs_node.addObstacle(ins2);
                    obs_node.setStar(star_pos); saved_obstacles.add(obs_node);
                    break;
        }
    }

}
