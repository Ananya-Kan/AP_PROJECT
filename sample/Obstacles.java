package sample;

public abstract class Obstacles {
    private static int no_of_obstacles;
    private final int id;
    private Point position;
    private double speed;

    {//static initialization block
        id = ++no_of_obstacles;
    }

    public Obstacles(Point position, double speed){
        this.position=position;
        this.speed=speed;
    }
    //Getters,Setters
    public int getId() { return id; }
    public void setPosition(Point position) { this.position = position; }
    public void setSpeed(double speed) { this.speed = speed; }
    public Point getPosition() { return position; }
    public double getSpeed() { return speed; }

    //abstract methods
    public abstract void setSizeParameter(double size_parameter);
    public abstract double getSizeParameter();
}