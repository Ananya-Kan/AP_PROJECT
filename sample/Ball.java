package sample;

public class Ball {
    private Point position;
    private int color;

    public Ball(Point position,int color){
        this.position = position;
        this.color = color;
    }

    public void setPosition(Point position) { this.position = position; }
    public void setColor(int color) { this.color = color; }
    public Point getPosition() { return position; }
    public int getColor() { return color; }
}
