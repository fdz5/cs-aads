package test.graph;

import main.graph.Graph;
import main.graph.MatrixGraph;
import main.graph.example.LabeledNode;
import main.graph.example.WeightEdge;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;

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

    @Before
    public void init() {
        g = new MatrixGraph();
        n1 = new LabeledNode("Node A");
        n2 = new LabeledNode("Node B");
        n3 = new LabeledNode("Node C");
        n4 = new LabeledNode("Node D");

        e1 = new WeightEdge(1);
        e2 = new WeightEdge(2);
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
        g.deleteEdge(n1, n2);

        Assert.assertEquals(1, g.countEdges());
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
    public void findNeighboringNodesTest() {
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);

        g.addEdge(e1, n1, n2);
        g.addEdge(e2, n2, n3);

        Assert.assertNotNull(g.getNeighboringNodes(n1));
        Assert.assertEquals(1, g.getNeighboringNodes(n1).size());

        g.addEdge(e1, n1, n3);
        Assert.assertEquals(2, g.getNeighboringNodes(n1).size());

        g.deleteEdge(n1, n3);
        Assert.assertEquals(true, g.getNeighboringNodes(n1).contains(n2));
    }

}
