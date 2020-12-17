package sample;

public class CircleObs extends Obstacles {

    private double diameter;
    public CircleObs(Point position, double speed, double offset_angle,double diameter) {
        super(position, speed, offset_angle);
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
