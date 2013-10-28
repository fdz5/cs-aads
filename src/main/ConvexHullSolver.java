package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class ConvexHullSolver implements ConvexHull {

	private Stack<Point2D> stack;

	@Override
	public List<Point2D> convexHull(List<Point2D> ps) {
		stack = new Stack<>();
		Point2D p0 = findStartPoint(ps);
		List<Point2D> woStPoint = new ArrayList<>();
		Collections.copy(woStPoint, ps);
		woStPoint.remove(p0);
		List<Point2D> psPolar = polarSort(woStPoint, p0);
		
		
		return null;
	}

	private Point2D findStartPoint(List<Point2D> ps) {
		Point2D first = null;
		for (Point2D p : ps) {
			if (first != null) {
				if (p.getY() < first.getY())
					first = p;
				else if (p.getY() == first.getY() && p.getX() < first.getX())
					first = p;
			}
		}
		return first;
	}

	private List<Point2D> polarSort(List<Point2D> ps, Point2D p0) {
		Collections.sort(ps, p0.POLAR_ORDER);
		return ps;
	}
	
}
