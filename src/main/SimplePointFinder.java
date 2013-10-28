package main;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SimplePointFinder implements PointFinder {
	
	private Set<Segment> segments;

	@Override
	public int findPoint(Point2D a, Point2D b, Point2D c) {
		double d = a.getX() * (b.getY() - c.getY()) - a.getY()
				* (b.getX() - c.getX()) + b.getX() * c.getY() - b.getY()
				* c.getX();
		
		if (d < 0)
			return -1;
		if (d == 0)
			return 0;
		return 1;
	}

	@Override
	public List<Set<Point2D>> separatePoints(Set<Point2D> pointSet, Point2D a,
			Point2D b) {
		List<Set<Point2D>> separatedSets = new ArrayList<>(3);
		Set<Point2D> leftSet = new HashSet<>();
		Set<Point2D> rightSet = new HashSet<>();
		Set<Point2D> onPathSet = new HashSet<>();
		
		Iterator<Point2D> iter = pointSet.iterator();
		while (iter.hasNext()) {
			Point2D p = iter.next();
			int res = findPoint(a, b, p);
			if (res == -1)
				rightSet.add(p);
			else if (res == 0)
				onPathSet.add(p);
			else
				leftSet.add(p);
		}
		
		separatedSets.add(leftSet);
		separatedSets.add(rightSet);
		separatedSets.add(onPathSet);
		return separatedSets;
	}

	@Override
	public int areCrossed(Point2D a, Point2D b, Point2D c, Point2D d) {
		if (isOnLine(a, b, c) || isOnLine(a, b, d))
			return 0;
		int posA = findPoint(c, d, a);
		int posB = findPoint(c, d, b);
		int posC = findPoint(a, b, c);
		int posD = findPoint(a, b, d);
		if (posC != posD && posA != posB)
			return 1;
		return -1;	
	}
	
	public boolean isOnLine(Point2D a, Point2D b, Point2D c) {
		int pos = findPoint(a, b, c);
		if (pos != 0)
			return false;
		if ((Math.min(a.getX(), b.getX()) <= c.getX()) && 
			(c.getX() <= Math.max(a.getX(), b.getX())) && 
			(Math.min(a.getY(), b.getY()) <= c.getY()) && 
			(c.getY() <= Math.max(a.getY(), b.getY())))
			return true;
		return false;
	}

	@Override
	public int countCrossings(Set<Point2D> pointSet) {
		int count = 0;
		segments = new HashSet();
		for (Point2D p : pointSet) {
			for (Point2D inP : pointSet) {
				if (!p.equals(inP)) {
					segments.add(new Segment(p, inP));
				}
			}
		}
		for (Segment s : segments) {
			for (Segment inS : segments) {
				if (!s.equals(inS)) {
					if (areCrossed(s.getStart(), s.getEnd(), inS.getStart(), inS.getEnd()) == 1)
						++count;
				}
			}
		}
		return count;
	}

}
