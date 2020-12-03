package sample;

public class CircleObs extends Obstacles {

    private double diameter;
    public CircleObs(Point position, double speed, double diameter) {
        super(position, speed);
        this.diameter = diameter;
    }

    @Override
    public void setSizeParameter(double size_parameter) {
        diameter = size_parameter;
    }

    @Override
    public double getSizeParameter() {
        return diameter;
    }
}
