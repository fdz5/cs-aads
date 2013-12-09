package test.graph;

import main.graph.Graph;
import main.graph.ListGraph;
import main.graph.MatrixGraph;
import main.graph.example.DoubleWeightedEdge;
import main.graph.example.LabeledNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * User: filip
 * Date: 01.12.13
 * Time: 23:11
 */
public class ListGraphTest {

    private Graph<LabeledNode, DoubleWeightedEdge> g = new ListGraph<>();
    private LabeledNode n1;
    private LabeledNode n2;
    private LabeledNode n3;
    private LabeledNode n4;

    private DoubleWeightedEdge e1;
    private DoubleWeightedEdge e2;
    private DoubleWeightedEdge e3;
    private DoubleWeightedEdge e4;
    private DoubleWeightedEdge e5;

    @Before
    public void init() {
        g = new MatrixGraph<>();
        n1 = new LabeledNode("Node A");
        n2 = new LabeledNode("Node B");
        n3 = new LabeledNode("Node C");
        n4 = new LabeledNode("Node D");

        e1 = new DoubleWeightedEdge(1.0);
        e2 = new DoubleWeightedEdge(2.0);
        e3 = new DoubleWeightedEdge(3.0);
        e4 = new DoubleWeightedEdge(4.0);
        e5 = new DoubleWeightedEdge(5.0);
    }

    @Test
    public void countNodesTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        Assert.assertEquals(4, g.countNodes());
    }

    @Test
    public void countEdgesTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.addEdge(e1, n1, n2);
        g.addEdge(e2, n2, n3);
        Assert.assertEquals(2, g.countEdges());

        g.addEdge(e5, n1, n2);
        Assert.assertEquals(3, g.countEdges());
    }

    @Test
    public void deleteEdgeTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.addEdge(e1, n1, n2);
        g.addEdge(e2, n2, n3);
        g.deleteEdge(e1);

        Assert.assertEquals(1, g.countEdges());
        Assert.assertEquals(false, g.containsEdge(e1));
    }

    @Test
    public void findNodeTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Assert.assertEquals(true, g.containsNose(n1));
        Assert.assertEquals(false, g.containsNose(n4));
    }

    @Test
    public void deleteNode() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.deleteNode(n1);
        g.deleteNode(n2);

        Assert.assertEquals(false, g.containsNose(n1));
        Assert.assertEquals(true, g.containsNose(n3));
    }

    @Test
    public void findNeighboringNodesTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.addEdge(e1, n1, n2);
        g.addEdge(e2, n2, n3);

        Assert.assertNotNull(g.getNeighboringNodes(n1));
        Assert.assertEquals(1, g.getNeighboringNodes(n1).size());

        g.addEdge(e3, n1, n3);
        Assert.assertEquals(2, g.getNeighboringNodes(n1).size());

        g.deleteEdge(e1);
        Assert.assertEquals(true, g.getNeighboringNodes(n1).contains(n3));
        Assert.assertEquals(false, g.getNeighboringNodes(n1).contains(n1));
        Assert.assertEquals(false, g.getNeighboringNodes(n1).contains(n2));
    }

    @Test
    public void findEndsTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.addEdge(e1, n1, n2);
        g.addEdge(e2, n2, n3);

        List<LabeledNode> ends = g.findEnds(e1);
        Assert.assertNotNull(ends);
        Assert.assertEquals(n1, ends.get(0));
        Assert.assertEquals(n2, ends.get(1));

        g.deleteEdge(e1);

        ends = g.findEnds(e2);
        Assert.assertNotNull(ends);
        Assert.assertEquals(n2, ends.get(0));
        Assert.assertEquals(n3, ends.get(1));
    }

    @Test
    public void areNeighboursTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.addEdge(e1, n1, n2);
        g.addEdge(e2, n1, n3);
        g.addEdge(e3, n3, n1);
        g.addEdge(e4, n4, n1);

        Assert.assertEquals(true, g.areNeighbours(n1, n2));
        Assert.assertEquals(true, g.areNeighbours(n4, n1));
        Assert.assertEquals(true, g.areNeighbours(n1, n4));

        g.deleteEdge(e1);
        Assert.assertEquals(false, g.areNeighbours(n1, n2));
        g.deleteEdge(e2);
        Assert.assertEquals(true, g.areNeighbours(n1, n3));
    }

//    @Test
//    public void loadTest() {
//        List<LabeledNode> nodes = new ArrayList<>();
//        List<DoubleWeightedEdge> edges = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            LabeledNode n = new LabeledNode(Integer.toString(i));
//            DoubleWeightedEdge e = new DoubleWeightedEdge(i);
//            g.addNode(n);
//            nodes.add(n);
//            edges.add(e);
//        }
//        for (int i = 0; i < 99; i++) {
//            LabeledNode n1 = nodes.get(i);
//            LabeledNode n2 = nodes.get(i + 1);
//            DoubleWeightedEdge e = edges.get(i);
//            g.addEdge(e, n1, n2);
//        }
//        Assert.assertEquals(true, g.containsEdge(edges.get(30)));
//        Assert.assertEquals(true, g.containsEdge(edges.get(10)));
//        Assert.assertEquals(true, g.containsEdge(edges.get(50)));
//        g.deleteEdge(edges.get(10));
//        Assert.assertEquals(false, g.containsEdge(edges.get(10)));
//        Assert.assertEquals(true, g.areNeighbours(nodes.get(12), nodes.get(13)));
//        Assert.assertEquals(false, g.areNeighbours(nodes.get(10), nodes.get(11)));
//    }

}
