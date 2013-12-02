package test.graph;

import main.graph.Graph;
import main.graph.MatrixGraph;
import main.graph.example.LabeledNode;
import main.graph.example.WeightEdge;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

/**
 * User: filip
 * Date: 01.12.13
 * Time: 23:11
 */
public class MatrixGraphTest {

    private Graph<LabeledNode, WeightEdge> g = new MatrixGraph();
    private LabeledNode n1;
    private LabeledNode n2;
    private LabeledNode n3;
    private LabeledNode n4;

    private WeightEdge e1;
    private WeightEdge e2;
    private WeightEdge e3;

    @Before
    public void init() {
        g = new MatrixGraph();
        n1 = new LabeledNode("Node A");
        n2 = new LabeledNode("Node B");
        n3 = new LabeledNode("Node C");
        n4 = new LabeledNode("Node D");

        e1 = new WeightEdge(1);
        e2 = new WeightEdge(2);
        e3 = new WeightEdge(3);
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
        Assert.assertEquals(false, g.findEdge(e1));
    }

    @Test
    public void findNodeTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);

        Assert.assertEquals(true, g.findNode(n1));
        Assert.assertEquals(false, g.findNode(n4));
    }

    @Test
    public void deleteNode() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.deleteNode(n1);
        g.deleteNode(n2);

        Assert.assertEquals(false, g.findNode(n1));
        Assert.assertEquals(true, g.findNode(n3));
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

}
