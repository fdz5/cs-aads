package test.geometry;

import junit.framework.Assert;
import main.geometry.MyPoint;
import main.geometry.PolarPoint;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @author fdziedzic
 *         Date 29.10.13
 *         Time 13:05
 */
public class MyPointTest {

    @Test
    public void toPolarTest1() {
        MyPoint p = new MyPoint(1,1);
        PolarPoint pp = p.toPolart(new MyPoint(0, 0));
        DecimalFormat df = new DecimalFormat("####0.00");
        Assert.assertEquals("1,41", df.format(pp.getR()));
        Assert.assertEquals("0,79", df.format(pp.getFi()));
    }

    @Test
    public void toPolarTest2() {

        MyPoint p = new MyPoint(-5,-5);
        PolarPoint pp = p.toPolart(new MyPoint(0, 0));
        DecimalFormat df = new DecimalFormat("####0.00");
        Assert.assertEquals("7,07", df.format(pp.getR()));
        Assert.assertEquals("-0,79", df.format(pp.getFi()));
    }

    @Test
    public void toPolarTest3() {
        MyPoint p = new MyPoint(10,-5);
        PolarPoint pp = p.toPolart(new MyPoint(0, 0));
        DecimalFormat df = new DecimalFormat("####0.00");
        Assert.assertEquals("11,18", df.format(pp.getR()));
        Assert.assertEquals("-0,46", df.format(pp.getFi()));
    }

    @Test
    public void toPolarTest4() {
        MyPoint p = new MyPoint(7,7);
        PolarPoint pp = p.toPolart(new MyPoint(0, -9));
        DecimalFormat df = new DecimalFormat("####0.00");
        Assert.assertEquals("17,46", df.format(pp.getR()));
        Assert.assertEquals("1,16", df.format(pp.getFi()));
    }

}
