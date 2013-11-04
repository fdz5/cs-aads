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
        Assert.assertNotNull(convexHull);
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
        Assert.assertNotNull(convexHull);
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
        Assert.assertNotNull(convexHull);
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
        result.add(new MyPoint(-7, -7));
        result.add(new MyPoint(-9, 0));
        result.add(new MyPoint(-7, 7));
        result.add(new MyPoint(0, 9));
        result.add(new MyPoint(7, 7));
        result.add(new MyPoint(9, 0));
        result.add(new MyPoint(7, -7));

        List<PolarPoint> convexHull = ch.convexHull(ps);
        printPointList(convexHull);
        
        Assert.assertNotNull(convexHull);
        Assert.assertEquals(8, convexHull.size());
//        TODO change MyPoint in result to PolarPoint
//        Assert.assertEquals(result, convexHull);
        
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
        Assert.assertNotNull(convexHull);
        Assert.assertEquals(4, convexHull.size());
        printPointList(convexHull);
    }
    
	@Test
	public void convexHullTestFinal() {
		List<MyPoint> ps = new ArrayList<>();
		int[][] input = new int[][] {
				{ 0, 14, 56 }, 
				{ 1, 46, 89 }, 
				{ 2, 99, 8 }, 
				{ 3, 77, 32 }, 
				{ 4, 60, 82 }, 
				{ 5, 16, 98 }, 
				{ 6, 66, 69 }, 
				{ 7, 81, 22 }, 
				{ 8, 77, 18 }, 
				{ 9, 77, 23 }, 
				{ 10, 39, 7 }, 
				{ 11, 69, 95 }, 
				{ 12, 15, 78 }, 
				{ 13, 70, 9 }, 
				{ 14, 36, 20 }, 
				{ 15, 6, 27 }, 
				{ 16, 44, 68 }, 
				{ 17, 49, 48 }, 
				{ 18, 35, 26 }, 
				{ 19, 54, 98 }, 
				{ 20, 31, 2 }, 
				{ 21, 63, 92 }, 
				{ 22, 37, 22 }, 
				{ 23, 3, 3 }, 
				{ 24, 43, 81 },
		};		
		for (int[] x : input) {
			ps.add(new MyPoint(x[0], x[1], x[2]));
		}

		List<PolarPoint> convexHull = ch.convexHull(ps);
		Assert.assertNotNull(convexHull);
		Assert.assertEquals(7, convexHull.size());
		printPointList(convexHull);
	}
    
	private void printPointList(List<PolarPoint> convexHull) {
		for (PolarPoint p : convexHull) {
        	System.out.println("Point (" + ((MyPoint) p.getPoint()).getIndex() + ", " + p.getPoint().getX() + ", " + p.getPoint().getY());
        }
	}

}
