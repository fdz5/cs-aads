package main;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SimplePointFinder implements PointFinder {
	
	private Set<Segment> segments;

	@Override
	public int findPoint(Point2D a, Point2D b, Point2D c) {
		double d = a.getX() * (b.getY() - c.getY())
                - a.getY() * (b.getX() - c.getX())
                + b.getX() * c.getY()
                - b.getY() * c.getX();

		if (d < 0.01 && d > -0.01) {
			return 0;
		}
			
		if (d < 0)
			return -1;
		return 1;
	}

    private Point2D round(Point2D p) {
        return ((MyPoint) p).round(100);
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
		List<Segment> sgs = new ArrayList<>(makeSegment(pointSet));
		for (int i = 0; i < sgs.size(); i++) {
			for (int j=i; j<sgs.size(); j++) {
                Segment out = sgs.get(i);
                Segment in = sgs.get(j);
					if (areCrossed(out.getStart(), out.getEnd(), in.getStart(), in.getEnd()) == 1)
						++count;
			}
		}
		return count;
	}

	@Override
	public int findColinearNum(Set<Point2D> ps, Segment s) {
		int num = 0;
        for (Point2D p : ps) {
            if (findPoint(round(s.getStart()), round(s.getEnd()), round(p)) == 0)       {
                num++;
            }
        }
		return num;
	}

    @Override
	public Set<Segment> makeSegment(Set<Point2D> ps) {
		Set<Segment> segments = new HashSet<>();
        List<Point2D> psl = new ArrayList<>(ps);
		for (int i=0; i<ps.size(); i++) {
			for (int j=i; j<ps.size(); j++) {
				if (!psl.get(i).equals(psl.get(j)))
					segments.add(new Segment(psl.get(i), psl.get(j)));
			}
		}
		return segments;
	}

	@Override
	public Set<Point2D> parseCsv(String f) {
        Set<Point2D> ps = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			while (line != null) {
				int idx = Integer.parseInt(line.split(";")[0].trim());
				float x = Float.parseFloat(line.split(";")[1].replace(",", ".").trim());
				float y = Float.parseFloat(line.split(";")[2].replace(",", ".").trim());
				Point2D p = new MyPoint(idx, x, y);
				ps.add(p);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.err.println("Error occured: " + e.getMessage());
		}
		return ps;
	}

}
