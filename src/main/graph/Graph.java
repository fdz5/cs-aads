package main.graph;

import java.util.List;

public interface Graph<N, E> {

	// Dodaj węzeł
	// Usuń węzeł
	// Dodaj krawędź
	// Usuń krawędź
	// Podaj węzły sąsiednie do węzła
	// Podaj krawędzie incydentne do węzła
	// Znajdź węzeł
	// Znajdź krawędź
	// Podaj końce krawędzi
	// Podaj ilość węzłów
	// Podaj ilość krawędzi
	// Czy węzły są sąsiednie

	public void addNode(N n);

	public void deleteNode(N n);

	public void addEdge(E e, N n1, N n2);

	public void deleteEdge(E e);

	public List<N> getNeighbourVertices(N n);

	public List<E> getNeighbourEdges(N n);

	public boolean findNode(N n);

	public boolean findEdge(E e);

	public List<N> findEnds(E n);

	public int countNodes();

	public int countVertices();

	public boolean areNeighbours(N n1, N n2);

}
