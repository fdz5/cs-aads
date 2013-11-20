package main;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

public interface PointFinder {
	
	public int findPoint(Point2D a, Point2D b, Point2D c);
	
	public List<Set<Point2D>> separatePoints(Set<Point2D> pointSet, Point2D a, Point2D b);

	public boolean isOnLine(Point2D a, Point2D b, Point2D c);
	
	public int areCrossed(Point2D a, Point2D b, Point2D c, Point2D d);
	
	public int countCrossings(Set<Point2D> pointSet);
	
	public int findColinearNum(Set<Point2D> ps, Segment s);
	
	public Set<Point2D> parseCsv(String f);

    public Set<Segment> makeSegment(Set<Point2D> ps);
	
}
