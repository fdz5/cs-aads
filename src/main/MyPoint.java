package main;

import java.awt.*;

/**
 * @author fdziedzic
 *         Date 29.10.13
 *         Time 00:05
 */
public class MyPoint extends Point {

    public MyPoint(int x, int y) {
        super(x, y);
    }

    public PolarPoint toPolart(MyPoint p0) {
        double x = getX() - p0.getX();
        double y = getY() - p0.getY();
        double r = Math.sqrt(x * x + y * y);
        double fi = computeAngle(x, y, r);
        return new PolarPoint(r, fi, this);
    }

    private double computeAngle(double x, double y, double r) {
        return fixFi(Math.asin(y / r), x, y);
    }

    private double fixFi(double fi, double x, double y) {
        if (x < 0 && y > 0) return Math.PI - fi;
        return fi;
    }

}