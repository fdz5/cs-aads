package main.geometry;

import java.util.Comparator;

/**
 * @author fdziedzic
 *         Date 29.10.13
 *         Time 00:38
 */
public class PolarPointComparator implements Comparator<PolarPoint> {

    @Override
    public int compare(PolarPoint o1, PolarPoint o2) {
        if (o1.getFi() > o2.getFi()) return 1;
        if (o1.getFi() < o2.getFi()) return -1;
        if (o1.getR() > o2.getR()) return 1;
        if (o1.getR() < o2.getR()) return -1;
        return 0;
    }

}