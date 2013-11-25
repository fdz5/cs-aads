package main.graph;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph<N, E> implements Graph<N, E> {
	
	private List<List<AbstractNode>> graph;
	private List<N> nodes;
	private List<E> edges;

	public MatrixGraph() {
		graph = new ArrayList<List<AbstractNode>>();
	}

	@Override
	public void addNode(N n) {
		nodes.add(n);
	}

	@Override
	public void deleteNode(N n) {
		nodes.remove(n);
	}

	@Override
	public void addEdge(E e, N n1, N n2) {
		if (!nodes.contains(n1) || !nodes.contains(n2))
			throw new IllegalArgumentException("Nodes do not exist in the graph");
		//TODO check duplicates
		edges.add(e);
		
		nodes.add(n1);
		nodes.add(n2);
		
		AbstractEdge<N, E> ae = new AbstractEdge<>(e, n1, n2);
		AbstractNode<N, E> an1 = new AbstractNode<N, E>(n1);
		AbstractNode<N, E> an2 = new AbstractNode<N, E>(n2);
		
		List<AbstractNode> col = new ArrayList<>();
		col.add(an1);
		col.add(an2);
		graph.add(col);
		graph.add(col);
	}

	@Override
	public void deleteEdge(E e) {
		edges.add(e);
	}

	@Override
	public List<N> getNeighbourVertices(N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> getNeighbourEdges(N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findNode(N n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findEdge(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<N> findEnds(E n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean areNeighbours(N n1, N n2) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
