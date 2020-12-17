package sample;

public class Cross extends Obstacles {
    private double armLength;

    public Cross(Point position, double speed, double offset_angle, double armLength) {
        super(position, speed, offset_angle);
        this.armLength = armLength;
    }

    @Override
    public void setSizeParameter(double size_parameter) {
        armLength = size_parameter;
    }

    @Override
    public double getSizeParameter() {
        return armLength;
    }
}
