package main;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {

	/**
	 * Compares two points by polar angle (between 0 and 2pi) with respect to
	 * this point.
	 */
	public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();

	private final double x; // x coordinate
	private final double y; // y coordinate

	public Point2D(double x, double y) {
		if (Double.isInfinite(x) || Double.isInfinite(y))
			throw new IllegalArgumentException("Coordinates must be finite");
		if (Double.isNaN(x) || Double.isNaN(y))
			throw new IllegalArgumentException("Coordinates cannot be NaN");
		this.x = x;
		this.y = y;
	}

	/**
	 * Is a->b->c a counterclockwise turn?
	 * 
	 * @param a
	 *            first point
	 * @param b
	 *            second point
	 * @param c
	 *            third point
	 * @return { -1, 0, +1 } if a->b->c is a { clockwise, collinear;
	 *         counterclocwise } turn.
	 */
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (area2 < 0)
			return -1;
		else if (area2 > 0)
			return +1;
		else
			return 0;
	}

	/**
	 * Compares this point to that point by y-coordinate, breaking ties by
	 * x-coordinate.
	 * 
	 * @param that
	 *            the other point
	 * @return { a negative integer, zero, a positive integer } if this point is
	 *         { less than, equal to, greater than } that point
	 */
	public int compareTo(Point2D that) {
		if (this.y < that.y)
			return -1;
		if (this.y > that.y)
			return +1;
		if (this.x < that.x)
			return -1;
		if (this.x > that.x)
			return +1;
		return 0;
	}

	// compare other points relative to polar angle (between 0 and 2pi) they
	// make with this Point
	private class PolarOrder implements Comparator<Point2D> {
		public int compare(Point2D q1, Point2D q2) {
			double dx1 = q1.x - x;
			double dy1 = q1.y - y;
			double dx2 = q2.x - x;
			double dy2 = q2.y - y;

			if (dy1 >= 0 && dy2 < 0)
				return -1; // q1 above; q2 below
			else if (dy2 >= 0 && dy1 < 0)
				return +1; // q1 below; q2 above
			else if (dy1 == 0 && dy2 == 0) { // 3-collinear and horizontal
				if (dx1 >= 0 && dx2 < 0)
					return -1;
				else if (dx2 >= 0 && dx1 < 0)
					return +1;
				else
					return 0;
			} else
				return -ccw(Point2D.this, q1, q2); // both above or below

			// Note: ccw() recomputes dx1, dy1, dx2, and dy2
		}
	}

	/**
	 * Does this point equal y?
	 * 
	 * @param other
	 *            the other point
	 * @return true if this point equals the other point; false otherwise
	 */
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (other.getClass() != this.getClass())
			return false;
		Point2D that = (Point2D) other;
		return this.x == that.x && this.y == that.y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
