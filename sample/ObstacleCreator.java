package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ObstacleCreator {
    public static Group createRing(double centerX, double centerY, double radius){
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();
        Arc arc3 = new Arc();
        Arc arc4 = new Arc();
        //properties of the arcs
        arc1.setFill(Color.TRANSPARENT); arc1.setStroke(Color.RED); arc1.setStrokeWidth(20);
        arc2.setFill(Color.TRANSPARENT); arc2.setStroke(Color.DEEPPINK); arc2.setStrokeWidth(20);
        arc3.setFill(Color.TRANSPARENT); arc3.setStroke(Color.YELLOW); arc3.setStrokeWidth(20);
        arc4.setFill(Color.TRANSPARENT); arc4.setStroke(Color.GREEN); arc4.setStrokeWidth(20);

        //Setting the dimensions of the arcs (centre,radius,length,start angle)
        arc1.setCenterX(centerX);arc3.setCenterX(centerX); arc2.setCenterX(centerX); arc4.setCenterX(centerX);
        arc1.setCenterY(centerY); arc2.setCenterY(centerY); arc3.setCenterY(centerY); arc4.setCenterY(centerY);
        arc1.setRadiusX(radius); arc2.setRadiusX(radius); arc3.setRadiusX(radius); arc4.setRadiusX(radius);
        arc1.setRadiusY(radius); arc2.setRadiusY(radius);arc3.setRadiusY(radius);arc4.setRadiusY(radius);
        arc1.setStartAngle(0.0f); arc2.setStartAngle(90.0f); arc3.setStartAngle(180.0f); arc4.setStartAngle(270.0f);
        arc1.setLength(90.0f); arc2.setLength(90.0f); arc3.setLength(90.0f); arc4.setLength(90.0f);

        //Setting the type of the arc
        arc1.setType(ArcType.OPEN);arc2.setType(ArcType.OPEN);arc3.setType(ArcType.OPEN);arc4.setType(ArcType.OPEN);

        //Creating a Group object
        return new Group(arc1,arc2,arc3,arc4);
    }

    public static Group createCross(double centerX, double centerY, double armLength){
        Line arm1 = new Line();
        Line arm2 = new Line();
        Line arm3 = new Line();
        Line arm4 = new Line();
        //Styling the arms
        arm1.setStroke(Color.RED);arm1.setStrokeWidth(10);arm1.setStrokeType(StrokeType.CENTERED);arm1.setStrokeLineCap(StrokeLineCap.ROUND);
        arm2.setStroke(Color.DEEPPINK);arm2.setStrokeWidth(10);arm2.setStrokeType(StrokeType.CENTERED);arm2.setStrokeLineCap(StrokeLineCap.ROUND);
        arm3.setStroke(Color.YELLOW);arm3.setStrokeWidth(10);arm3.setStrokeType(StrokeType.CENTERED);arm3.setStrokeLineCap(StrokeLineCap.ROUND);
        arm4.setStroke(Color.GREEN);arm4.setStrokeWidth(10);arm4.setStrokeType(StrokeType.CENTERED);arm4.setStrokeLineCap(StrokeLineCap.ROUND);

        //positioning the arms
        arm1.setStartX(centerX);arm1.setStartY(centerY);arm1.setEndX(centerX);arm1.setEndY(centerY+armLength);
        arm2.setStartX(centerX);arm2.setStartY(centerY);arm2.setEndX(centerX);arm2.setEndY(centerY-armLength);
        arm3.setStartX(centerX);arm3.setStartY(centerY);arm3.setEndX(centerX-armLength);arm3.setEndY(centerY);
        arm4.setStartX(centerX);arm4.setStartY(centerY);arm4.setEndX(centerX+armLength);arm4.setEndY(centerY);

        return new Group(arm1,arm2,arm3,arm4);
    }

    public static Group createSquare(double centerX, double centerY, double sideLength){
        Line side1 = new Line();
        Line side2 = new Line();
        Line side3 = new Line();
        Line side4 = new Line();

        side1.setStroke(Color.RED);side1.setStrokeWidth(10);side1.setStrokeType(StrokeType.CENTERED);side1.setStrokeLineCap(StrokeLineCap.ROUND);
        side2.setStroke(Color.DEEPPINK);side2.setStrokeWidth(10);side2.setStrokeType(StrokeType.CENTERED);side2.setStrokeLineCap(StrokeLineCap.ROUND);
        side3.setStroke(Color.YELLOW);side3.setStrokeWidth(10);side3.setStrokeType(StrokeType.CENTERED);side3.setStrokeLineCap(StrokeLineCap.ROUND);
        side4.setStroke(Color.GREEN);side4.setStrokeWidth(10);side4.setStrokeType(StrokeType.CENTERED);side4.setStrokeLineCap(StrokeLineCap.ROUND);

        side1.setStartX(centerX-sideLength/2);side1.setStartY(centerY+sideLength/2);side1.setEndX(centerX-sideLength/2);side1.setEndY(centerY-sideLength/2);
        side2.setStartX(centerX-sideLength/2);side2.setStartY(centerY-sideLength/2);side2.setEndX(centerX+sideLength/2);side2.setEndY(centerY-sideLength/2);
        side3.setStartX(centerX+sideLength/2);side3.setStartY(centerY-sideLength/2);side3.setEndX(centerX+sideLength/2);side3.setEndY(centerY+sideLength/2);
        side4.setStartX(centerX+sideLength/2);side4.setStartY(centerY+sideLength/2);side4.setEndX(centerX-sideLength/2);side4.setEndY(centerY+sideLength/2);

        return new Group(side1,side2,side3,side4);
    }

    public static Group createTriangle(double centerX, double centerY, double sideLength){
        Line side1 = new Line();
        Line side2 = new Line();
        Line side3 = new Line();

        side1.setStroke(Color.RED);side1.setStrokeWidth(10);side1.setStrokeType(StrokeType.CENTERED);side1.setStrokeLineCap(StrokeLineCap.ROUND);
        side2.setStroke(Color.DEEPPINK);side2.setStrokeWidth(10);side2.setStrokeType(StrokeType.CENTERED);side2.setStrokeLineCap(StrokeLineCap.ROUND);
        side3.setStroke(Color.YELLOW);side3.setStrokeWidth(10);side3.setStrokeType(StrokeType.CENTERED);side3.setStrokeLineCap(StrokeLineCap.ROUND);

        double dist1 = sideLength/(Math.sqrt(3)*2);
        double dist2 = sideLength/(Math.sqrt(3));
        side1.setStartX(centerX-sideLength/2);side1.setStartY(centerY+dist1);side1.setEndX(centerX+sideLength/2);side1.setEndY(centerY+dist1);
        side2.setRotate(60);
        side2.setStartX(centerX-sideLength/2);side2.setStartY(centerY+dist1);side2.setEndX(centerX);side2.setEndY(centerY-dist2);
        side3.setRotate(300);
        side3.setStartX(centerX);side3.setStartY(centerY-dist2);side3.setEndX(centerX+sideLength/2);side3.setEndY(centerY+dist1);

        return new Group(side1,side2,side3);
    }
}
