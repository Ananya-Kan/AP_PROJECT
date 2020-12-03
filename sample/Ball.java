package sample;

public class Ball {
    private Point position;
    private String color;

    public Ball(Point position,String color){
        this.position = position;
        this.color = color;
    }

    public void setPosition(Point position) { this.position = position; }
    public void setColor(String color) { this.color = color; }
    public Point getPosition() { return position; }
    public String getColor() { return color; }
}
