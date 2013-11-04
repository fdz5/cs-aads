package test;

import java.util.ArrayList;
import java.util.List;

import main.ConvexHull;
import main.ConvexHullSolver;
import main.MyPoint;
import main.PolarPoint;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fdziedzic
 *         Date 29.10.13
 *         Time 11:40
 */
public class ConvexHullSolverTest {

    private static ConvexHull ch;

    @Before
    public void setUp() throws Exception {
        ch = new ConvexHullSolver();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void toPolarTest() {
        List<MyPoint> ps = new ArrayList<>();
        ps.add(new MyPoint(7, 7));
        ps.add(new MyPoint(7, -7));
        ps.add(new MyPoint(-7, -7));
        ps.add(new MyPoint(-7, 7));
        ps.add(new MyPoint(9, 0));
        ps.add(new MyPoint(-9, 0));
        ps.add(new MyPoint(0, 9));
        ps.add(new MyPoint(0, -9));

        List<PolarPoint> polarPs = ch.toPolar(ps, new MyPoint(1, 1));
        Assert.assertEquals(ps.size(), polarPs.size());
    }

    @Test
    public void convexHullBasicTest() {
        List<MyPoint> ps = new ArrayList<>();
        ps.add(new MyPoint(7, 7));
        ps.add(new MyPoint(7, -7));
        ps.add(new MyPoint(-7, -7));
        ps.add(new MyPoint(-7, 7));

        List<PolarPoint> convexHull = ch.convexHull(ps);
        Assert.assertNotNull(ps);
        Assert.assertEquals(4, convexHull.size());
    }

    @Test
    public void convexHullTest1() {
        List<MyPoint> ps = new ArrayList<>();
        ps.add(new MyPoint(7, 7));
        ps.add(new MyPoint(7, -7));
        ps.add(new MyPoint(-7, -7));
        ps.add(new MyPoint(-7, 7));
        ps.add(new MyPoint(9, 0));
        ps.add(new MyPoint(-9, 0));
        ps.add(new MyPoint(0, 9));
        ps.add(new MyPoint(0, -9));

        List<PolarPoint> convexHull = ch.convexHull(ps);
        Assert.assertNotNull(ps);
        Assert.assertEquals(8, convexHull.size());
    }

    @Test
    public void convexHullTest2() {
        List<MyPoint> ps = new ArrayList<>();
        ps.add(new MyPoint(7, 7));
        ps.add(new MyPoint(7, -7));
        ps.add(new MyPoint(-7, -7));
        ps.add(new MyPoint(-7, 7));
        ps.add(new MyPoint(9, 0));
        ps.add(new MyPoint(-9, 0));
        ps.add(new MyPoint(0, 9));
        ps.add(new MyPoint(0, -9));
        ps.add(new MyPoint(0, 0));
        ps.add(new MyPoint(1, 2));
        ps.add(new MyPoint(-2, 1));
        ps.add(new MyPoint(-1, -1));
        ps.add(new MyPoint(3, 4));
        ps.add(new MyPoint(4, 3));
        ps.add(new MyPoint(-5, 4));
        ps.add(new MyPoint(6, 5));

        List<PolarPoint> convexHull = ch.convexHull(ps);
        Assert.assertNotNull(ps);
        Assert.assertEquals(8, convexHull.size());
        printPointList(convexHull);
    }
	
    @Test
    public void convexHullTest3() {
        List<MyPoint> ps = new ArrayList<>();
        ps.add(new MyPoint(7, 7));
        ps.add(new MyPoint(7, -7));
        ps.add(new MyPoint(-7, -7));
        ps.add(new MyPoint(-7, 7));
        ps.add(new MyPoint(9, 0));
        ps.add(new MyPoint(-9, 0));
        ps.add(new MyPoint(-9, 0));
        ps.add(new MyPoint(0, 9));
        ps.add(new MyPoint(0, -9));
        ps.add(new MyPoint(0, 0));
        ps.add(new MyPoint(1, 2));
        ps.add(new MyPoint(-2, 1));
        ps.add(new MyPoint(-1, -1));
        ps.add(new MyPoint(3, 4));
        ps.add(new MyPoint(4, 3));
        ps.add(new MyPoint(-5, 4));
        ps.add(new MyPoint(6, 5));
        
        List<MyPoint> result = new ArrayList<>();
        result.add(new MyPoint(0, -9));
        result.add(new MyPoint(7, -7));
        result.add(new MyPoint(9, 0));
        result.add(new MyPoint(7, 7));
        result.add(new MyPoint(0, 9));
        result.add(new MyPoint(-7, 7));
        result.add(new MyPoint(-9, 0));
        result.add(new MyPoint(-7, -7));

        List<PolarPoint> convexHull = ch.convexHull(ps);
        printPointList(convexHull);
        
        Assert.assertNotNull(ps);
        Assert.assertEquals(8, convexHull.size());
        Assert.assertEquals(result, convexHull);
        
    }
    
    @Test
    public void convexHullTest4() {
        List<MyPoint> ps = new ArrayList<>();
        ps.add(new MyPoint(0, 0));
        ps.add(new MyPoint(4, 0));
        ps.add(new MyPoint(4, 4));
        ps.add(new MyPoint(0, 4));
        ps.add(new MyPoint(2, 2));
        
        List<MyPoint> result = new ArrayList<>();
        result.add(new MyPoint(0, 0));
        result.add(new MyPoint(4, 0));
        result.add(new MyPoint(4, 4));
        result.add(new MyPoint(0, 4));

        List<PolarPoint> convexHull = ch.convexHull(ps);
        Assert.assertNotNull(ps);
        Assert.assertEquals(4, convexHull.size());
        printPointList(convexHull);
    }
    
	private void printPointList(List<PolarPoint> convexHull) {
		for (PolarPoint p : convexHull) {
        	System.out.println("Point (" + p.getPoint().getX() + ", " + p.getPoint().getY());
        }
	}

}
