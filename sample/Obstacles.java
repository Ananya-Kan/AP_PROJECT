package sample;

public abstract class Obstacles {
    private static int no_of_obstacles;
    private final int id;
    private Point position;
    private double speed;
    private double offset_angle;
    private boolean passed;

    {//static initialization block
        id = ++no_of_obstacles;
    }

    public Obstacles(Point position, double speed, double offset_angle){
        this.position=position;
        this.speed=speed;
        this.offset_angle = offset_angle;
        passed = false;
    }
    //Getters,Setters
    public int getId() { return id; }
    public void setPosition(Point position) { this.position = position; }
    public void setSpeed(double speed) { this.speed = speed; }
    public Point getPosition() { return position; }
    public double getSpeed() { return speed; }
    public void setOffset_angle(double offset_angle) { this.offset_angle = offset_angle; }
    public double getOffset_angle() { return offset_angle; }
    public void setPassed(boolean passed) { this.passed = passed; }
    public boolean isPassed() { return passed; }

    //abstract methods
    public abstract void setSizeParameter(double size_parameter);
    public abstract double getSizeParameter();
}