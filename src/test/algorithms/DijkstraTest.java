package test.algorithms;

import junit.framework.Assert;
import main.graph.Graph;
import main.graph.ListGraph;
import main.graph.MatrixGraph;
import main.graph.algorithms.Dijkstra;
import main.graph.example.DoubleWeightedEdge;
import main.graph.example.LabeledNode;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * User: filip
 * Date: 08.12.13
 * Time: 18:55
 */
public class DijkstraTest {

    private Dijkstra dijkstra;
    private Graph<LabeledNode, DoubleWeightedEdge> g;

    private LabeledNode n1;
    private LabeledNode n2;
    private LabeledNode n3;
    private LabeledNode n4;
    private LabeledNode n5;
    private LabeledNode n6;

    private DoubleWeightedEdge e1;
    private DoubleWeightedEdge e2;
    private DoubleWeightedEdge e3;
    private DoubleWeightedEdge e4;
    private DoubleWeightedEdge e5;
    private DoubleWeightedEdge e6;
    private DoubleWeightedEdge e7;
    private DoubleWeightedEdge e8;
    private DoubleWeightedEdge e9;
    private DoubleWeightedEdge e10;
    private DoubleWeightedEdge e11;
    private DoubleWeightedEdge e12;

    @Before
    public void init() {
        g = new ListGraph();
        n1 = new LabeledNode("Node 1");
        n2 = new LabeledNode("Node 2");
        n3 = new LabeledNode("Node 3");
        n4 = new LabeledNode("Node 4");
        n5 = new LabeledNode("Node 5");
        n6 = new LabeledNode("Node 6");

        e1 = new DoubleWeightedEdge(14.0);
        e2 = new DoubleWeightedEdge(7.0);
        e3 = new DoubleWeightedEdge(15.0);
        e4 = new DoubleWeightedEdge(6.0);
        e5 = new DoubleWeightedEdge(9.0);
        e6 = new DoubleWeightedEdge(2.0);
        e7 = new DoubleWeightedEdge(8.0);
        e8 = new DoubleWeightedEdge(10.0);
        e9 = new DoubleWeightedEdge(11.0);
        e10 = new DoubleWeightedEdge(5.0);
        e11 = new DoubleWeightedEdge(3.0);
        e12 = new DoubleWeightedEdge(12.0);

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

        dijkstra = new Dijkstra(g);
    }

    @Test
    public void dijkstraTest() {
        Assert.assertEquals(19.0, dijkstra.init(n1, n5));
    }

    @Test
    public void dijkstraTest2() {
        g.addEdge(e12, n1, n5);
        dijkstra = new Dijkstra(g);
        Assert.assertEquals(12.0, dijkstra.init(n1, n5));
    }

    @Test
    public void multipleEdgesTest() {
        g.addEdge(e11, n6, n5);
        g.addEdge(e10, n6, n5);
        dijkstra = new Dijkstra(g);
        Assert.assertEquals(14.0, dijkstra.init(n1, n5));
    }

    @Test
    public void read() {
        Graph<LabeledNode, DoubleWeightedEdge> g = new MatrixGraph<>();
        Map<Integer, LabeledNode> nodeMap = new HashMap<>();
        for (int i=0; i<100; i++) {
            LabeledNode node = new LabeledNode(String.valueOf(i));
            g.addNode(node);
            nodeMap.put(i, node);
        }
        Set<Integer> nodes = new HashSet<>();
        try {
            Scanner sc = new Scanner(new FileInputStream("graf1.txt"));
            while (sc.hasNext()) {
                int i = sc.nextInt();
                nodes.add(i);
                int j = sc.nextInt();
                nodes.add(j);
                double w = Double.parseDouble(sc.next());
                g.addEdge(new DoubleWeightedEdge(w), nodeMap.get(i), nodeMap.get(j));
//                System.out.println(i + " " + j + " " + w);
            }
            Assert.assertEquals(100, g.countNodes());
            System.out.println("Numer of edges: " + g.countEdges());
            Dijkstra<LabeledNode> dijkstra = new Dijkstra<LabeledNode>(g);
            dijkstra.init(nodeMap.get(1), nodeMap.get(99));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
