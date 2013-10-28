package main;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Main {
	
	public static void main(String[] args) {
		Point2D a = new Point(0, 0);
		Point2D b = new Point(5, 5);
		Point2D c = new Point(10, 10);
		
		PointFinder pf = new SimplePointFinder();
		System.out.println(pf.findPoint(a, b, c));
	}

}
