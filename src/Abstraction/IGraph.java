package Abstraction;

import java.util.List;

/**
 * The interface Graph.
 */
public interface IGraph {
	/**
	 * Gets nb nodes.
	 *
	 * @return the number of nodes in the graph (referred to as the order of the graph)
	 */
	int getNbNodes();

	/**
	 * To adjacency matrix int [ ] [ ].
	 *
	 * @return the adjacency matrix representation int[][] of the graph
	 */
	int[][] toAdjacencyMatrix();

	/**
	 * Gets nodes.
	 *
	 * @return nodes nodes
	 */
	List<?> getNodes();

	/**
	 * @return a List of nodes representing the neighbors of node x
	 */
	List<?> getNeighbors(Object x);

}
