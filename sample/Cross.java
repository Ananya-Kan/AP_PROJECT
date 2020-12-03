package sample;

public class Cross extends Obstacles {
    private double armLength;

    public Cross(Point position, double speed, double armLength) {
        super(position, speed);
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
