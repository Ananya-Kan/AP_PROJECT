package sample;

public class Triangle extends Obstacles {
    private double sideLength;
    public Triangle(Point position, double speed, double sideLength) {
        super(position, speed);
        this.sideLength = sideLength;
    }

    @Override
    public void setSizeParameter(double size_parameter) {
        this.sideLength = size_parameter;
    }

    @Override
    public double getSizeParameter() {
        return sideLength;
    }
}
