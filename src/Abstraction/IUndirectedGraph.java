package Abstraction;

import java.util.List;

public interface IUndirectedGraph<A extends AbstractNode> extends IGraph {

	/**
	 * @return the number of edges in the graph
 	 */
	int getNbEdges();

	/**
	 * @return true i there is an edge between x and y
	 */
	boolean isEdge(A x, A y);

	/**
	 * Removes edge (x,y) if there exists one
     */
	void removeEdge(A x, A y);

	/**
	 * Adds edge (x,y), requires nodes x and y already exist
     */
	void addEdge(A x, A y);

	/**
	 * @return a List of nodes representing the neighbors of node x
	 */
	List<A> getNeighbors(A x);

	@Override
	default List<?> getNeighbors(Object x) {
		return this.getNeighbors((A) x);
	};

}
