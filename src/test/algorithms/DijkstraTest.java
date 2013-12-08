package test.algorithms;

import junit.framework.Assert;
import main.graph.Graph;
import main.graph.ListGraph;
import main.graph.MatrixGraph;
import main.graph.algorithms.Dijkstra;
import main.graph.example.IntegerWeightedEdge;
import main.graph.example.LabeledNode;
import org.junit.Before;
import org.junit.Test;

/**
 * User: filip
 * Date: 08.12.13
 * Time: 18:55
 */
public class DijkstraTest {

    private Dijkstra dijkstra;
    private Graph g;

    private LabeledNode n1;
    private LabeledNode n2;
    private LabeledNode n3;
    private LabeledNode n4;
    private LabeledNode n5;
    private LabeledNode n6;

    private IntegerWeightedEdge e1;
    private IntegerWeightedEdge e2;
    private IntegerWeightedEdge e3;
    private IntegerWeightedEdge e4;
    private IntegerWeightedEdge e5;
    private IntegerWeightedEdge e6;
    private IntegerWeightedEdge e7;
    private IntegerWeightedEdge e8;
    private IntegerWeightedEdge e9;
    private IntegerWeightedEdge e10;
    private IntegerWeightedEdge e11;
    private IntegerWeightedEdge e12;

    @Before
    public void init() {
        g = new ListGraph();
        n1 = new LabeledNode("Node 1");
        n2 = new LabeledNode("Node 2");
        n3 = new LabeledNode("Node 3");
        n4 = new LabeledNode("Node 4");
        n5 = new LabeledNode("Node 5");
        n6 = new LabeledNode("Node 6");

        e1 = new IntegerWeightedEdge(14);
        e2 = new IntegerWeightedEdge(7);
        e3 = new IntegerWeightedEdge(15);
        e4 = new IntegerWeightedEdge(6);
        e5 = new IntegerWeightedEdge(9);
        e6 = new IntegerWeightedEdge(2);
        e7 = new IntegerWeightedEdge(8);
        e8 = new IntegerWeightedEdge(10);
        e9 = new IntegerWeightedEdge(11);
        e10 = new IntegerWeightedEdge(5);
        e11 = new IntegerWeightedEdge(3);
        e12 = new IntegerWeightedEdge(12);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);

        g.addEdge(e1, n1, n6);
        g.addEdge(e5, n1, n3);
        g.addEdge(e2, n1, n2);

        g.addEdge(e8, n2, n3);
        g.addEdge(e3, n2, n4);

        g.addEdge(e6, n3, n6);
        g.addEdge(e9, n3, n4);

        g.addEdge(e7, n6, n5);


        g.addEdge(e4, n4, n5);

        dijkstra = new Dijkstra(g, n1, n5);
    }

    @Test
    public void dijkstraTest() {
        Assert.assertEquals(19, dijkstra.run());
    }

    @Test
    public void dijkstraTest2() {
        g.addEdge(e12, n1, n5);
        dijkstra = new Dijkstra(g, n1, n5);
        Assert.assertEquals(12, dijkstra.run());
    }

    @Test
    public void multipleEdgesTest() {
        g.addEdge(e11, n6, n5);
        g.addEdge(e10, n6, n5);
        dijkstra = new Dijkstra(g, n1, n5);
        Assert.assertEquals(14, dijkstra.run());
    }

}
