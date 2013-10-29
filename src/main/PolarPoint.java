package main;

import java.awt.geom.Point2D;

/**
 * @author fdziedzic
 *         Date 29.10.13
 *         Time 00:05
 */
public class PolarPoint {

    private final double r;
    private final double fi;
    private final Point2D point;

    public PolarPoint(double r, double fi, Point2D point) {
        this.r = r;
        this.fi = fi;
        this.point = point;
    }

    public double getR() {
        return r;
    }

    public double getFi() {
        return fi;
    }

    public Point2D getPoint() {
        return point;
    }

}
