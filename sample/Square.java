package sample;

public class Square extends Obstacles {
    private double sideLength;
    public Square(Point position, double speed, double sideLength) {
        super(position, speed);
        this.sideLength = sideLength;
    }

    @Override
    public void setSizeParameter(double size_parameter) {
        sideLength = size_parameter;
    }

    @Override
    public double getSizeParameter() {
        return sideLength;
    }
}
