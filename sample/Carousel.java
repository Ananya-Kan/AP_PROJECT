package sample;

public class Carousel extends Obstacles {

    public Carousel(Point position, double speed) {
        super(position, speed);
    }

    @Override
    public void setSizeParameter(double size_parameter) {}

    @Override
    public double getSizeParameter() {
        return 0;
    }
}
