package sample;

import java.util.ArrayList;

public class ObstacleNode {
    private Point star;
    private ArrayList<Obstacles> obstacles;

    public ObstacleNode(){
        star = null;
        obstacles = new ArrayList<Obstacles>();
    }

    public void setStar(Point star) { this.star = star; }
    public Point getStar(){return star;}
    public void addObstacle(Obstacles obs){obstacles.add(obs);}
    public ArrayList<Obstacles> getObstacles() { return obstacles; }
}
