package main.geometry;

import java.util.List;

public interface ConvexHull {
	
	public List<PolarPoint> convexHull(List<MyPoint> ps);

    public List<PolarPoint> toPolar(List<MyPoint> ps, MyPoint p0);

}
