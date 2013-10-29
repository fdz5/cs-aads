package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class ConvexHullSolver implements ConvexHull {

    private Stack<PolarPoint> stack;

    @Override
    public List<PolarPoint> convexHull(List<MyPoint> ps) {
        stack = new Stack<>();
        MyPoint p0 = findStartPoint(ps);
        ps.remove(p0);

        List<PolarPoint> polarPoints = toPolar(ps, p0);

        Collections.sort(polarPoints, new PolarPointComparator());
        Collections.reverse(polarPoints);
        uniqueFi(polarPoints);

        initStack(p0, polarPoints);
        convexStep(polarPoints);

        return stack.subList(0, stack.size());
    }

    private MyPoint findStartPoint(List<MyPoint> ps) {
        MyPoint p = ps.get(0);
        for (int i = 1; i < ps.size(); i++) {
            MyPoint curr = ps.get(i);
            if ((curr.getY() < p.getY()) || (curr.getY() == p.getY() && curr.getX() < p.getX())) p = ps.get(i);
        }
        return p;
    }

    @Override
    public List<PolarPoint> toPolar(List<MyPoint> ps, MyPoint p0) {
        List<PolarPoint> polars = new ArrayList<>();
        for (MyPoint p : ps) {
            polars.add(p.toPolart(p0));
        }
        return polars;
    }

    private void uniqueFi(List<PolarPoint> ps) {
        for (int i = 1; i < ps.size(); i++) {
            if (ps.get(i - 1).getFi() == ps.get(i).getFi()) ps.remove(i);
        }
    }

    private void initStack(MyPoint p0, List<PolarPoint> polarPoints) {
        stack.push(p0.toPolart(p0));
        stack.push(polarPoints.get(0));
        stack.push(polarPoints.get(1));
    }

    private void convexStep(List<PolarPoint> polarPoints) {
        PointFinder pf = new SimplePointFinder();
        for (int i = 2; i < polarPoints.size(); i++) {
            while (pf.findPoint(stack.peek().getPoint(), stack.get(stack.size() - 2).getPoint(), polarPoints.get(i).getPoint()) == -1) {
                stack.pop();
            }
            stack.push(polarPoints.get(i));
        }
    }

}
